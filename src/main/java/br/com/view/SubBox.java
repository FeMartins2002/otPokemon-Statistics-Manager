package br.com.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SubBox extends JPanel {
	private JLabel label;
	private JLabel value;

	public SubBox(String label, String value) {
		this.label = UIBuilder.createLabel(20, 24, label, Color.white, 19);
		this.label.setHorizontalAlignment(SwingConstants.CENTER);
		this.label.setBackground(new Color(59, 111, 146));
		this.label.setOpaque(true);

		this.value = UIBuilder.createLabel(20, 20, value, Color.black, 18);
		this.value.setHorizontalAlignment(SwingConstants.CENTER);

		setLayout(new GridBagLayout());	
		setBackground(Color.white);	
		setBorder(BorderFactory.createLineBorder(new Color(21, 32, 43), 5));

		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(3, 3, 0, 3);
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

	public JLabel getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value.setText(value);
	}
}
