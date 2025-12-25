package br.com.view;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import br.com.cards.CardGraphic;
import br.com.cards.CardSubTable;
import br.com.cards.CardTable;
import br.com.model.Database;
import br.com.observer.TableSubject;

public class DashboardPanel extends JPanel {
	private static JPanel mainTable, subTable, graphic;
	private static CardLayout cardLayout;
	private TableSubject subject;

	public DashboardPanel(TableSubject subject) {
		this.subject = subject;

		setBackground(null);
		setLayout(new GridBagLayout());
		cardLayout = new CardLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		CardTable tmTable = new CardTable(Database.TECHNICAL_MACHINE, this.subject);
		CardTable hlTable = new CardTable(Database.HORDE_LEADER, this.subject);
		CardTable mgTable = new CardTable(Database.MIGRATORYS, this.subject);
		CardTable shTable = new CardTable(Database.SHINYS, this.subject);

		initializeMainTable(tmTable, hlTable, mgTable, shTable);
		gbc = gbc(0, 0, 2, 0.1, 0.3, new Insets(0, 0, 0, 20));
		add(mainTable, gbc);

		CardSubTable tmSub = new CardSubTable(Database.TECHNICAL_MACHINE, "type");
		CardSubTable hlSub = new CardSubTable(Database.HORDE_LEADER, "pokeball");
		CardSubTable mgSub = new CardSubTable(Database.MIGRATORYS, "pokeball");
		CardSubTable shSub = new CardSubTable(Database.SHINYS, "name");

		this.subject.addObserver(tmSub);
		this.subject.addObserver(hlSub);
		this.subject.addObserver(mgSub);
		this.subject.addObserver(shSub);

		initializeSubTable(tmSub, hlSub, mgSub, shSub);
		gbc = gbc(1, 0, 1, 0.2, 0.1, new Insets(0, 0, 20, 0));
		add(subTable, gbc);

		CardGraphic tmGr = new CardGraphic(Database.TECHNICAL_MACHINE, "name");
		CardGraphic hlGr = new CardGraphic(Database.HORDE_LEADER, "nature");
		CardGraphic mgGr = new CardGraphic(Database.MIGRATORYS, "nature");
		CardGraphic shGr = new CardGraphic(Database.SHINYS, "location");

		this.subject.addObserver(tmGr);
		this.subject.addObserver(hlGr);
		this.subject.addObserver(mgGr);
		this.subject.addObserver(shGr);

		initializeCharts(tmGr, hlGr, mgGr, shGr);
		gbc = gbc(1, 1, 1, 0.2, 0.6, new Insets(0, 0, 0, 0));
		add(graphic, gbc);
	}

	private void initializeMainTable(CardTable card1, CardTable card2, CardTable card3, CardTable card4) {
		mainTable = new JPanel(cardLayout);

		mainTable.add(card1, "cardTableTm");
		mainTable.add(card2, "cardTableHl");
		mainTable.add(card3, "cardTableMg");
		mainTable.add(card4, "cardTableSh");
	}

	private void initializeSubTable(CardSubTable card1, CardSubTable card2, CardSubTable card3, CardSubTable card4) {
		subTable = new JPanel(cardLayout);

		subTable.add(card1, "cardSubTableTm");
		subTable.add(card2, "cardSubTableHl");
		subTable.add(card3, "cardSubTableMg");
		subTable.add(card4, "cardSubTableSh");
	}

	private void initializeCharts(CardGraphic card1, CardGraphic card2, CardGraphic card3, CardGraphic card4) {
		graphic = new JPanel(cardLayout);
		graphic.add(card1, "cardGraphicTm");
		graphic.add(card2, "cardGraphicHl");
		graphic.add(card3, "cardGraphicMg");
		graphic.add(card4, "cardGraphicSh");
	}

	private GridBagConstraints gbc(int gx, int gy, int gh, double wx, double wy, Insets insets) {
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = gx;
		gbc.gridy = gy;
		gbc.gridheight = gh;
		gbc.weightx = wx;
		gbc.weighty = wy;
		gbc.insets = insets;
		gbc.fill = GridBagConstraints.BOTH;

		return gbc;
	}

	public static void replacePanel(String table, String sub, String graphi) {
		cardLayout.show(mainTable, table);
		cardLayout.show(subTable, sub);
		cardLayout.show(graphic, graphi);
	}
}
