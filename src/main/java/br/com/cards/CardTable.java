package br.com.cards;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import br.com.controller.CrudController;
import br.com.controller.DataController;
import br.com.observer.TableSubject;
import br.com.utilities.TableFiltering;
import br.com.view.AddWindow;
import br.com.view.UIBuilder;
import br.com.view.EditWindow;

public class CardTable extends JPanel implements ActionListener {
	private JLabel searchLabel;
	private JTextField searchBar;
	private JButton addButton, editButton, deleteButton, refresh;
	private DefaultTableModel model;
	private JTable table = new JTable();
	private String tableName;
	private TableSubject subject;

	public CardTable(String tableName, TableSubject subject) {
		this.subject = subject;
		this.tableName = tableName;

		setBackground(new Color(21, 32, 43));
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		addButton = UIBuilder.createButton(100, 40, new Color(59, 111, 146), "Create", 18);
		addButton.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(0, 0, 10, 0);
		add(addButton, gbc);

		editButton = UIBuilder.createButton(100, 40, new Color(59, 111, 146), "Update", 18);
		editButton.addActionListener(this);
		gbc.gridx = 2;
		gbc.insets = new Insets(0, 10, 10, 0);
		add(editButton, gbc);

		deleteButton = UIBuilder.createButton(100, 40, new Color(59, 111, 146), "Delete", 18);
		deleteButton.addActionListener(this);
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(0, 0, 10, 0);
		add(deleteButton, gbc);

		refresh = UIBuilder.createButton(100, 40, new Color(59, 111, 146), "Refresh", 18);
		refresh.addActionListener(this);

		searchLabel = UIBuilder.createLabel(100, 40, "Search...", Color.white, 18);
		searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchLabel.setBackground(new Color(59, 111, 146));
		searchLabel.setOpaque(true);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(0, 0, 0, 0);
		add(searchLabel, gbc);

		searchBar = UIBuilder.createTextField(1, 40);
		searchBar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER; 
		add(searchBar, gbc);

		loadData();

		JScrollPane scroll = new JScrollPane(table);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(10, 0, 0, 0);
		add(scroll, gbc);
	}

	private void loadData() {
		setUpModel();
		setUpTable();
		setupSearchBar();
	}

	private void setUpModel() {
		model = DataController.loadDataFromTable(tableName);
	}

	private void setUpTable() {
		UIBuilder.formatTable(table, model);
	}

	private void setupSearchBar() {
		TableFiltering.SearchBarConfiguration(model, table, searchBar);
	}

	@Override
	public void actionPerformed(ActionEvent click) {

		if (click.getSource() == addButton) {
			new AddWindow(tableName, subject, refresh);
		}

		if (click.getSource() == deleteButton) {
			int selectedLine = table.getSelectedRow();

			if (selectedLine == -1) {
				JOptionPane.showMessageDialog(null, "Select a record to delete!", "Attention", JOptionPane.WARNING_MESSAGE);
				return;
			}

			if(CrudController.deleteRecord(table, tableName, selectedLine, subject)) {
				JOptionPane.showMessageDialog(null, "Record deleted!");
				model.removeRow(selectedLine);
			}
			else {
				JOptionPane.showMessageDialog(null, "Failed to delete the record!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (click.getSource() == editButton) {
			int selectedLine = table.getSelectedRow();

			if (selectedLine != -1) {
				int colCount = table.getColumnCount();
				String[] data = new String[colCount];

				for (int i = 0; i < colCount; i++) {
					data[i] = table.getValueAt(selectedLine, i).toString();
				}
				new EditWindow(tableName, data, subject, refresh);
			}	
		}

		if(click.getSource() == refresh) {
			loadData();
		}
	}
}
