package br.com.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.controller.AccessController;

public class ChangeResponseWindow extends JFrame implements ActionListener {
	private JLabel title;
	private JTextField newResponse;
	private JButton changeResponse;

	public ChangeResponseWindow() {
		setTitle("Change Response");
		setSize(500, 250);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(21, 32, 43));
		getContentPane().setLayout(new GridBagLayout());
		setResizable(false);

		GridBagConstraints gbc = new GridBagConstraints();

		title = UIBuilder.createLabel(300, 20, "Create a new response", Color.white, 18);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(0, 0, 20, 0);
		add(title, gbc);

		newResponse = UIBuilder.createTextField(300, 40);
		newResponse.setName("responseField");
		gbc.gridy++;
		add(newResponse, gbc);

		changeResponse = UIBuilder.createButton(160, 40, new Color(59, 111, 146), "Change Response", 18);
		changeResponse.setName("sendResponseButton");
		changeResponse.addActionListener(this);
		gbc.gridy++;
		add(changeResponse, gbc);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent click) {
		if(click.getSource() == changeResponse) {
			if(newResponse.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Fill in the field!", "Attention", JOptionPane.WARNING_MESSAGE);
				return;
			}
			else {
				if(AccessController.changeResponse(newResponse.getText())) {
					JOptionPane.showMessageDialog(this, "Response changed successfully !");
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(this, "An error occurred while trying to change the response !", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
