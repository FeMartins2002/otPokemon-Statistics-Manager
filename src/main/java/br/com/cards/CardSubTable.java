package br.com.cards;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import br.com.controller.DataController;
import br.com.observer.TableObserver;
import br.com.view.SubBox;

public class CardSubTable extends JPanel implements TableObserver {
	private DefaultTableModel model;
	private String tableName;
	private String column;
	private Map<String, SubBox> subBoxes;

	public CardSubTable(String tableName, String column) {
		this.tableName = tableName;
		this.column = column;

		setBackground(new Color(21, 32, 43));
		setLayout(new GridLayout(4, 5, 0, 0));

		model = loadData();
		subBoxes = new HashMap<>();

		for (int i = 0; i < model.getRowCount(); i++) {
			SubBox subBox = new SubBox(model.getValueAt(i, 0).toString(), model.getValueAt(i, 1).toString());
			subBoxes.put(model.getValueAt(i, 0).toString(), subBox);
			add(subBox);
		}
	}

	private DefaultTableModel loadData() {
		DefaultTableModel model = DataController.loadDataFromSubTable(tableName, column);
		return model;
	}

	private void updateBoxValue(SubBox box, int value) {
		int current = parseIntFromBox(box);
		box.setValue(String.valueOf(current + value));
	}

	private int parseIntFromBox(SubBox box) {
		try {
			return Integer.parseInt(box.getValue().getText());
		} 
		catch (NumberFormatException error) {
			return 0;
		}
	}

	@Override
	public void onTableEvent(String tableName, String operation, String type) {
		if (!subBoxes.containsKey(type)) {
			return;
		}
		SubBox box = subBoxes.get(type);
		switch (operation) {

		case "delete":
			updateBoxValue(box, -1);
			break;

		case "add":
			updateBoxValue(box, 1);
			break;
		}
	}
}
