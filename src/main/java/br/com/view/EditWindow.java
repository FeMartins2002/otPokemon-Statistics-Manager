package br.com.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import br.com.controller.CrudController;
import br.com.fields.Field;
import br.com.fields.FormField;
import br.com.fields.FormFieldFactory;
import br.com.fields.TableFieldProvider;
import br.com.model.Database;
import br.com.observer.TableSubject;
import br.com.security.Validator;

public class EditWindow extends JFrame implements ActionListener {
	private String tableName;
	private JButton editButton, refresh;
	private List<FormField> fields;
	private TableSubject subject;
	private String id;
	private String oldComboValue;
	private String newComboValue;

	public EditWindow(String tableName,String[] data,TableSubject subject,JButton refresh) {
		this.tableName = tableName;
		this.subject = subject;
		this.refresh = refresh;
		fields = new ArrayList<>();

		this.id = data[0];
		this.oldComboValue = String.valueOf(data[Database.getTotalColumns(tableName)]);

		setTitle("Edit");
		setSize(600, 680);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(21, 32, 43));
		setResizable(false);

		if (createFields(data)) {
			setVisible(true);
		}
	}

	private boolean createFields(String[] data) {
		List<Field> metadata = TableFieldProvider.getFields(tableName);
		if (metadata.isEmpty()) return false;

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.gridx = 0;
		int line = 0;
		int dataIndex = 1;

		JLabel title = UIBuilder.createLabel(300, 40, "Edit:", Color.white, 18);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridy = line++;
		add(title, gbc);

		gbc.insets = new Insets(0, 80, 3, 80);

		for (Field field : metadata) {
			JLabel label = UIBuilder.createLabel(200, 25,field.getName(),Color.white, 18);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridy = line++;
			add(label, gbc);
			FormField formField = FormFieldFactory.create(field);

			if (dataIndex < data.length) {
				formField.setValue(data[dataIndex]);
			}

			gbc.anchor = GridBagConstraints.CENTER;
			gbc.gridy = line++;
			fields.add(formField);
			add(formField.getComponent(), gbc);

			dataIndex++;
		}

		gbc.insets = new Insets(25, 80, 5, 80);
		editButton = UIBuilder.createButton(200, 40, new Color(59, 111, 146), "Edit", 18);
		editButton.addActionListener(this);
		gbc.gridy = line;
		add(editButton, gbc);

		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Map<String, String> formData = new HashMap<>();

		for (FormField field : fields) {
			formData.put(field.getName(), field.getValue());			
		}
		newComboValue = formData.get(Database.getNameColumn(tableName));
		formData.put("id", id);

		if (!Validator.validateMap(formData)) {
			JOptionPane.showMessageDialog(this, "Fill in all fields!", "Attention", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (CrudController.edit(tableName, formData)) {
			subject.notifyObservers(tableName, "delete", oldComboValue);
			subject.notifyObservers(tableName, "add", newComboValue);
			refresh.doClick();
			JOptionPane.showMessageDialog(this, "Record edited successfully!");
			dispose();

		} else {
			JOptionPane.showMessageDialog(this, "Failed to edit record!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
