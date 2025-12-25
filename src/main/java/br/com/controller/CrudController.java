package br.com.controller;

import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import br.com.model.CrudService;
import br.com.model.Database;
import br.com.observer.TableSubject;
import br.com.security.Validator;

public class CrudController {
	private CrudController() {}

	public static boolean addRecord(String tableName, Map<String, String> formData) {
		if (!Validator.validateString(tableName) || !Validator.validateMap(formData)) {
			return false;
		}
		return CrudService.add(tableName, formData);
	}

	public static boolean edit(String tableName, Map<String, String> formData) {
		if (!Validator.validateString(tableName) || !Validator.validateMap(formData)) {
			return false;
		}
		return CrudService.edit(tableName, formData);
	}

	public static boolean deleteRecord(JTable table, String tableName, int line, TableSubject subject) {
		if (!Validator.validateString(tableName) || !Validator.validateNumber(line)) {
			return false;
		}

		try {
			int id = (int) table.getValueAt(line, 0);
			String item = table.getValueAt(line, Database.getTotalColumns(tableName)).toString();

			int confirm = JOptionPane.showConfirmDialog(null, "Do you really want to delete this record ?", "Confirmation", JOptionPane.YES_NO_OPTION);

			if (confirm == JOptionPane.YES_OPTION) {
				CrudService.delete(tableName, id);
				subject.notifyObservers(tableName, "delete", item);
			}
			return true;
		} 
		catch (Exception error) {
			JOptionPane.showMessageDialog(null, "Failed to delete the record!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}
