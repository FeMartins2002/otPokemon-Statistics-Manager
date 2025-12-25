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

public class AuthenticationWindow extends JFrame implements ActionListener {
	private JLabel title, question;
	private JTextField response;
	private JButton sendResponse;
	private String key;

	public AuthenticationWindow(String key) {
		this.key = key;
		setTitle("Authentication");
		setSize(500, 250);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(21, 32, 43));
		getContentPane().setLayout(new GridBagLayout());
		setResizable(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.fill = GridBagConstraints.NONE;

		title = UIBuilder.createLabel(300, 20, "Security Question", Color.white, 18);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 20, 0);
		add(title, gbc);

		question = UIBuilder.createLabel(400, 20, "What is the administrator's favorite Pokémon ?", Color.white, 15);
		question.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridy++;
		gbc.insets = new Insets(0, 0, 10, 0);
		add(question, gbc);

		response = UIBuilder.createTextField(300, 40);
		response.setName("responseField");
		gbc.gridy++;
		add(response, gbc);

		sendResponse = UIBuilder.createButton(150, 40, new Color(59, 111, 146), "Send Response", 18);
		sendResponse.setName("sendResponseButton");
		sendResponse.addActionListener(this);
		gbc.gridy++;
		add(sendResponse, gbc);

		setVisible(true);
	}

	private void openWindow() {
		if(key.equals("response")) {
			new ChangeResponseWindow();
			dispose();
		}
		else if(key.equals("password")) {
			new ChangePasswordWindow();
			dispose();
		}
		else {
			dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent click) {
		if(click.getSource() == sendResponse) {
			if(response.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Fill in the field !", "Attention", JOptionPane.WARNING_MESSAGE);
				return;
			}
			else {
				if(AccessController.validateResponse(response.getText())) {
					openWindow();
				}
				else {
					JOptionPane.showMessageDialog(this, "Incorrect answer !", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
