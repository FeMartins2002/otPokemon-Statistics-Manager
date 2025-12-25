package br.com.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class UIBuilder {
	private UIBuilder() {}

	public static JLabel createLabel(int width, int height, String text, Color textColor, int fontSize) {
		JLabel label = new JLabel();

		label.setText(text);
		label.setForeground(textColor);
		label.setPreferredSize(new Dimension(width, height));
		label.setFont(new Font("Arial", Font.PLAIN, fontSize));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setOpaque(false);

		return label;
	}

	public static JTextField createTextField(int width, int height) {
		JTextField field = new JTextField();

		field.setBackground(Color.white);
		field.setPreferredSize(new Dimension(width, height));
		field.setFont(new Font("Arial", Font.PLAIN, 18));
		field.setHorizontalAlignment(SwingConstants.CENTER);
		field.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		return field;
	}

	public static JPasswordField createPasswordField(int width, int height) {
		JPasswordField field = new JPasswordField();

		field.setBackground(Color.white);
		field.setPreferredSize(new Dimension(width, height));
		field.setFont(new Font("Arial", Font.PLAIN, 18));
		field.setHorizontalAlignment(SwingConstants.CENTER);
		field.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		return field;
	}

	public static JFormattedTextField createDateField() {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter("##/##/####");
			mask.setPlaceholderCharacter(' ');
		} catch(Exception error) {
			error.printStackTrace();
		}

		JFormattedTextField field = new JFormattedTextField(mask);
		field.setPreferredSize(new Dimension(300, 40));
		field.setFont(new Font("Arial", Font.PLAIN, 18));
		field.setHorizontalAlignment(SwingConstants.CENTER);
		field.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		field.setBackground(Color.white);

		return field;
	}

	public static JButton createButton(int width, int height, Color color, String text, int sizeText) {
		JButton button = new JButton();

		button.setPreferredSize(new Dimension(width, height));
		button.setBackground(color);
		button.setText(text);
		button.setForeground(Color.white);
		button.setFont(new Font("Arial", Font.PLAIN, sizeText));
		button.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setOpaque(true);
		button.setFocusPainted(false);
		button.setContentAreaFilled(true);

		button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				button.setBackground(button.getBackground().darker());
			}
			public void mouseExited(MouseEvent evt) {
				button.setBackground(color);
			}
		});

		return button;
	}

	public static JCheckBox createCheckBox(String text) {
		JCheckBox checkbox = new JCheckBox(text);

		checkbox.setFont(new Font("Arial", Font.PLAIN, 14));
		checkbox.setForeground(Color.white);
		checkbox.setOpaque(false);

		return checkbox;
	}

	public static JComboBox createComboField(String[] options) {
		JComboBox combo = new JComboBox<>(options);

		combo.setPreferredSize(new Dimension(300, 40));
		combo.setFont(new Font("Arial", Font.PLAIN, 18));
		combo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		return combo;
	}
	
	public static void formatTable(JTable table, DefaultTableModel model) {
		table.setModel(model);
		table.setRowHeight(30);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i < model.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		table.getColumnModel().getColumn(0).setPreferredWidth(10);
	}
}
