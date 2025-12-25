package br.com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.com.observer.TableSubject;

public class HomeWindow extends JFrame {
	private JPanel menu, status, dashboard;
	private TableSubject subject = new TableSubject();

	public HomeWindow() {
		setTitle("Home");
		setSize(1500, 800);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(21, 32, 43));
		setResizable(true);

		GridBagConstraints gbc = new GridBagConstraints();
		getContentPane().setLayout(new GridBagLayout());

		menu = new MenuPanel();
		menu.setPreferredSize(new Dimension(180, 0));
		menu.setMinimumSize(new Dimension(180, 0));
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(20, 20, 20, 20);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.gridheight = 2;
		add(menu, gbc);

		status = new StatusPanel(subject);
		status.setPreferredSize(new Dimension(0, 130));
		status.setMinimumSize(new Dimension(0, 130));
		
		gbc.insets = new Insets(20, 0, 20, 20);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weighty = 0;
		gbc.weightx = 0.8;
		gbc.gridheight = 1;
		add(status, gbc);

		dashboard = new DashboardPanel(subject);
		
		gbc.insets = new Insets(0, 0, 20, 20);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weighty = 1;
		gbc.weightx = 0.8;
		add(dashboard, gbc);

		setVisible(true);
	}
}
