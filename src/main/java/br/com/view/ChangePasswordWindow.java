package br.com.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import br.com.controller.AccessController;

public class ChangePasswordWindow extends JFrame implements ActionListener {
	private JLabel title, newPassword, repeatNewPassword;
	private JPasswordField passwordField, repeatField;
	private JCheckBox showPassword;
	private JButton changePasswordButton;

	public ChangePasswordWindow() {
		setTitle("Change Password");
		setSize(500, 500);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(21, 32, 43));
		getContentPane().setLayout(new GridBagLayout());
		setResizable(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.fill = GridBagConstraints.NONE;

		title = UIBuilder.createLabel(300, 20, "Create a new password", Color.white, 18);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 0, 30, 0);
		add(title, gbc);

		newPassword = UIBuilder.createLabel(300, 20, "New Password", Color.white, 15);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 0, 2, 0);
		add(newPassword, gbc);

		passwordField = UIBuilder.createPasswordField(300, 40);
		passwordField.setName("passwordField");
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 0, 15, 0);
		add(passwordField, gbc);

		repeatNewPassword = UIBuilder.createLabel(300, 20, "Repeat New Password", Color.white, 15);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 0, 2, 0);
		add(repeatNewPassword, gbc);

		repeatField = UIBuilder.createPasswordField(300, 40);
		repeatField.setName("repeatField");
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 0, 0, 0);
		add(repeatField, gbc);

		showPassword = UIBuilder.createCheckBox("Show Password");
		showPassword.setName("showPassword");
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		add(showPassword, gbc);

		showPassword.addActionListener(e -> {
			if (showPassword.isSelected()) {
				passwordField.setEchoChar((char) 0);
				repeatField.setEchoChar((char) 0);
			} else {
				passwordField.setEchoChar('•');
				repeatField.setEchoChar('•');
			}
		});

		changePasswordButton = UIBuilder.createButton(300, 40, new Color(59, 111, 146), "Change Password", 18);
		changePasswordButton.setName("changePasswordButton");
		changePasswordButton.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(15, 0, 0, 0);
		add(changePasswordButton, gbc);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent click) {
		if(click.getSource() == changePasswordButton) {
			if(String.valueOf(passwordField.getPassword()).isEmpty() || String.valueOf(repeatField.getPassword()).isEmpty()) {
				JOptionPane.showMessageDialog(this, "Fill in all fields !", "Attention", JOptionPane.WARNING_MESSAGE);
				return;
			}
			else {
				if(String.valueOf(passwordField.getPassword()).equals(String.valueOf(repeatField.getPassword()))) {
					if(AccessController.changePassword(String.valueOf(passwordField.getPassword()))) {
						JOptionPane.showMessageDialog(this, "Password changed successfully !");
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(this, "An error occurred while trying to change the password !", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "Passwords do not match !", "Attention", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
}
