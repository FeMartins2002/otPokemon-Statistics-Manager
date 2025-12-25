package br.com.view;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import br.com.controller.DataController;
import br.com.model.Database;
import br.com.observer.TableObserver;
import br.com.observer.TableSubject;

public class StatusPanel extends JPanel implements TableObserver {
	private StatusBox boxOne, boxTwo, boxThree, boxFour;
	private Map<String, StatusBox> subBoxes;
	private TableSubject subject;

	public StatusPanel(TableSubject subject) {
		this.subject = subject;
		setBackground(null);
		setLayout(new GridLayout(1, 4, 10, 0));

		boxOne = new StatusBox("Technical Machine", DataController.loadStatus(Database.TECHNICAL_MACHINE));
		boxTwo = new StatusBox("Horder Leader", DataController.loadStatus(Database.HORDE_LEADER));
		boxThree = new StatusBox("Migratory", DataController.loadStatus(Database.MIGRATORYS));
		boxFour = new StatusBox("Shiny", DataController.loadStatus(Database.SHINYS));

		subBoxes = new HashMap<>();
		subBoxes.put(Database.TECHNICAL_MACHINE, boxOne);
		subBoxes.put(Database.HORDE_LEADER, boxTwo);
		subBoxes.put(Database.MIGRATORYS, boxThree);
		subBoxes.put(Database.SHINYS, boxFour);

		this.subject.addObserver(this);

		add(boxOne);
		add(boxTwo);
		add(boxThree);
		add(boxFour);
	}

	@Override
	public void onTableEvent(String tableName, String operation, String boxName) {
		if (!subBoxes.containsKey(tableName)) {
			return;
		}
		StatusBox box = subBoxes.get(tableName);

		switch (operation) {

		case "add":
			int convertedAdd = Integer.parseInt(box.getValue());
			box.setValue(String.valueOf(convertedAdd + 1));
			break;

		case "delete":
			int convertedDel = Integer.parseInt(box.getValue());
			box.setValue(String.valueOf(convertedDel - 1));
			break;
		}
	}
}
