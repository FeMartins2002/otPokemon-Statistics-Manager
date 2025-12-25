package br.com.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StatusBox extends JPanel {
	private JLabel label;
	private JLabel value;

	public StatusBox(String label, String value) {
		this.label = UIBuilder.createLabel(0, 0, label, Color.white, 20);
		this.value = UIBuilder.createLabel(0, 0, value, Color.white, 50);
		this.value.setHorizontalAlignment(SwingConstants.RIGHT);

		setLayout(new GridBagLayout());	
		setBackground(new Color(59, 111, 146));		
		setBorder(BorderFactory.createLineBorder(Color.white, 3));

		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(0, 10, 0, 10);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		add(this.label, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		add(this.value, gbc);
	}

	public String getValue() {
		return value.getText();
	}

	public void setValue(String value) {
		this.value.setText(value);
	}	
}
