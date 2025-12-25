package br.com.controller;

import javax.swing.table.DefaultTableModel;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import br.com.model.DataLoader;
import br.com.security.Validator;

public class DataController {
	private DataController() {}

	public static DefaultTableModel loadDataFromTable(String tableName) {
		if(validate(tableName)) {
			return DataLoader.loadDataFromTable(tableName);
		}
		else {
			return new DefaultTableModel();
		}
	}

	public static DefaultTableModel loadDataFromSubTable(String tableName, String column) {
		if(validate(tableName) && validate(column)) {
			return DataLoader.loadDataFromSubTable(tableName, column);
		}
		else {
			return new DefaultTableModel();
		}
	}

	public static CategoryDataset loadDataFromGraphic(String tableName, String column) {
		if(validate(tableName) && validate(column)) {
			return DataLoader.loadDataFromGraphic(tableName, column);
		}
		else {
			return new DefaultCategoryDataset();
		}
	}

	public static String loadStatus(String tableName) {
		if(validate(tableName)) {
			return DataLoader.loadTotalStatus(tableName);
		}
		else {
			return "0";
		}
	}

	private static boolean validate(String text) {
		if(Validator.validateString(text)) {
			return true;
		}
		return false;
	}
}
