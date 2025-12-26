package br.com.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import br.com.model.Connector;
import br.com.utilities.Backup;

public class MenuPanel extends JPanel implements ActionListener {
	private JLabel tableLabel, configurationLabel;
	private JButton openDashTechnical, openDashMigratory, openDashLeaders, openDashShinys, backup, changePassword, chageAnswer, exit;
	private JFrame home = (JFrame) SwingUtilities.getWindowAncestor(this);

	public MenuPanel() {
		setBackground(new Color(255, 255, 255));
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

		tableLabel = UIBuilder.createLabel(150, 20, "Tables", Color.black, 16);
		tableLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(tableLabel);

		openDashTechnical = UIBuilder.createButton(150, 40, new Color(59, 111, 146), "Technical Machine", 15);
		openDashTechnical.addActionListener(this);
		add(openDashTechnical);

		openDashLeaders = UIBuilder.createButton(150, 40, new Color(59, 111, 146), "Horder Leader", 15);
		openDashLeaders.addActionListener(this);
		add(openDashLeaders);

		openDashMigratory = UIBuilder.createButton(150, 40, new Color(59, 111, 146), "Migratorys", 15);
		openDashMigratory.addActionListener(this);
		add(openDashMigratory);

		openDashShinys = UIBuilder.createButton(150, 40, new Color(59, 111, 146), "Shinys", 15);
		openDashShinys.addActionListener(this);
		add(openDashShinys);

		configurationLabel = UIBuilder.createLabel(150, 20, "Configurations", Color.black, 16);
		configurationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(configurationLabel);

		backup = UIBuilder.createButton(150, 40, new Color(59, 111, 146), "Backup", 15);
		backup.addActionListener(this);
		add(backup);

		changePassword = UIBuilder.createButton(150, 40, new Color(59, 111, 146), "Change Password", 15);
		changePassword.addActionListener(this);
		add(changePassword);

		chageAnswer = UIBuilder.createButton(150, 40, new Color(59, 111, 146), "Chage Answer", 15);
		chageAnswer.addActionListener(this);
		add(chageAnswer);

		exit = UIBuilder.createButton(150, 40, new Color(59, 111, 146), "Exit", 15);
		exit.addActionListener(this);
		add(exit);
	}

	@Override
	public void actionPerformed(ActionEvent click) {
		if(click.getSource() == openDashTechnical) {
			DashboardPanel.replacePanel("cardTableTm", "cardSubTableTm", "cardGraphicTm");
		}

		if(click.getSource() == openDashLeaders) {
			DashboardPanel.replacePanel("cardTableHl", "cardSubTableHl", "cardGraphicHl");
		}

		if(click.getSource() == openDashMigratory) {
			DashboardPanel.replacePanel("cardTableMg", "cardSubTableMg", "cardGraphicMg");
		}

		if(click.getSource() == openDashShinys) {
			DashboardPanel.replacePanel("cardTableSh", "cardSubTableSh", "cardGraphicSh");
		}

		if(click.getSource() == backup) {
			File destination = Backup.openSaveWindow(home);

			if (destination != null) {
				boolean success = Backup.saveCopyOfBank(Connector.getPathdb(), destination);

				if (success) {
					JOptionPane.showMessageDialog(home, 
							"Backup successfully performed on:\n" + destination.getAbsolutePath(), 
							"Completed", 
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(home, 
							"Error trying to backup database.\n" + Backup.getError(), 
							"Error", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		if(click.getSource() == changePassword) {
			new AuthenticationWindow("password");
		}

		if(click.getSource() == chageAnswer) {
			new AuthenticationWindow("response");
		}

		if(click.getSource() == exit) {
			SwingUtilities.getWindowAncestor(this).dispose();
			System.exit(0);
		}
	}
}
