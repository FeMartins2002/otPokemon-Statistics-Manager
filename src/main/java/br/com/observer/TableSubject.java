package br.com.observer;

import java.util.ArrayList;
import java.util.List;

public class TableSubject {
	private List<TableObserver> observers = new ArrayList<>();

	public void addObserver(TableObserver obs) {
		observers.add(obs);
	}

	public void notifyObservers(String tableName, String operation, String item) {
		for (TableObserver obs : observers) {
			obs.onTableEvent(tableName, operation, item);
		}
	}
}
