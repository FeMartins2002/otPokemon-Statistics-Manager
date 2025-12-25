package br.com.observer;

public interface TableObserver {
	void onTableEvent(String tableName, String operation, String item);
}
