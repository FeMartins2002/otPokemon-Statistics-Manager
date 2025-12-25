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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.controller.AccessController;
import br.com.controller.LoginAttemptController;

public class LoginWindow extends JFrame implements ActionListener {
	private LoginAttemptController attemptController = new LoginAttemptController(3);
	private JLabel title, userLabel, passwordLabel, blockingNotice;
	private JTextField userField;
	private JPasswordField passwordField;
	private JButton login, forgotPassword;
	private JCheckBox showPassword;

	public LoginWindow() {
		setTitle("Login");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(21, 32, 43));
		getContentPane().setLayout(new GridBagLayout());
		setResizable(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.fill = GridBagConstraints.NONE;

		title = UIBuilder.createLabel(300, 20, "Login", Color.white, 18);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 0, 30, 0);
		add(title, gbc);

		userLabel = UIBuilder.createLabel(300, 20, "User", Color.white, 15);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 0, 2, 0);
		add(userLabel, gbc);

		userField = UIBuilder.createTextField(300, 40);
		userField.setName("userField");
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 0, 15, 0);
		add(userField, gbc);

		passwordLabel = UIBuilder.createLabel(300, 20, "Password", Color.white, 15);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 0, 2, 0);
		add(passwordLabel, gbc);

		passwordField = UIBuilder.createPasswordField(300, 40);
		passwordField.setName("passwordField");
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 0, 0, 0);
		add(passwordField, gbc);

		showPassword = UIBuilder.createCheckBox("Show Password");
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		add(showPassword, gbc);

		showPassword.addActionListener(click -> {
			if (showPassword.isSelected()) {
				passwordField.setEchoChar((char) 0);
			} else {
				passwordField.setEchoChar('•');
			}
		});

		forgotPassword = UIBuilder.createButton(120, 20, null, "Forgot Password", 14);
		forgotPassword.setName("forgotPasswordButton");
		forgotPassword.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		add(forgotPassword, gbc);

		login = UIBuilder.createButton(300, 40, new Color(59, 111, 146), "Login", 18);
		login.addActionListener(this);
		login.setName("loginButton");
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(15, 0, 0, 0);
		add(login, gbc);

		blockingNotice = UIBuilder.createLabel(300, 40, "", Color.yellow, 18);
		blockingNotice.setHorizontalAlignment(SwingConstants.CENTER);
		blockingNotice.setName("blockingNoticeLabel");
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		add(blockingNotice, gbc);

		setVisible(true);
	}

	private void blockAccess() {
		userField.setEnabled(false);
		passwordField.setEnabled(false);
		forgotPassword.setEnabled(false);
		login.setEnabled(false);
		blockingNotice.setText("Blocked");
		blockingNotice.setForeground(Color.red);
	}

	@Override
	public void actionPerformed(ActionEvent click) {
		if(click.getSource() == login) {
			if (userField.getText().isEmpty() || String.valueOf(passwordField.getPassword()).isEmpty()) {
				JOptionPane.showMessageDialog(this, "Fill in all fields !", "Attention", JOptionPane.WARNING_MESSAGE);
				return;
			}
			else {
				if (AccessController.userValidation(userField.getText(), String.valueOf(passwordField.getPassword()))) {
					new HomeWindow();
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(this, "Incorrect username or password !", "Error", JOptionPane.ERROR_MESSAGE);

					if(!attemptController.registerFailedAttempt()) {
						blockAccess();
					}
					else {
						blockingNotice.setText("Remaining Attempts: " + attemptController.getRemainingAttempts());
					}
				}
			}
		}

		if(click.getSource() == forgotPassword) {
			new AuthenticationWindow("password");
		}
	}

	public static void main(String[] args) {
		new LoginWindow();
	}
}
