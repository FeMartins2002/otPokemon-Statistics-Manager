package br.com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class DataLoader {
	private DataLoader() {}

	public static DefaultTableModel loadDataFromTable(String tableName) {
		String commandSQL = "SELECT * FROM " + tableName; 

		Vector<String> columns = Database.getColumns(tableName);
		Vector<Vector<Object>> rows = new Vector<>(); 

		try(
				Connection conn = DriverManager.getConnection(Connector.getUrl()); 
				PreparedStatement ps = conn.prepareStatement(commandSQL); 
				ResultSet rs = ps.executeQuery()) 
		{
			while (rs.next()) { 
				Vector<Object> row = new Vector<>(); 

				for (String c : columns) { 
					row.add(rs.getObject(c)); 
				} 
				rows.add(row); 
			}
			return new DefaultTableModel(rows, columns); 
		} 
		catch (SQLException error) {  
			return new DefaultTableModel();
		}  
	}

	public static DefaultTableModel loadDataFromSubTable(String tableName, String column) {
		String commandSQL = "SELECT " + column + ", COUNT(*) AS total FROM " + tableName + " GROUP BY " + column + " LIMIT 20";

		Vector<String> columns = new Vector<>();
		columns.add(column);
		columns.add("total");
		Vector<Vector<Object>> rows = new Vector<>();

		try (
				Connection conn = DriverManager.getConnection(Connector.getUrl());
				PreparedStatement ps = conn.prepareStatement(commandSQL);
				ResultSet rs = ps.executeQuery())
		{
			while (rs.next()) {
				Vector<Object> row = new Vector<>();
				row.add(rs.getString(column));
				row.add(rs.getString("total"));
				rows.add(row);
			}
			return new DefaultTableModel(rows, columns);
		} 
		catch (SQLException error) {
			return new DefaultTableModel();
		}
	}

	public static CategoryDataset loadDataFromGraphic(String tableName, String column) {
		String commandSQL = "SELECT " + column + ", COUNT(*) AS total FROM " + tableName + " GROUP BY " + column + " ORDER BY total DESC LIMIT 5"; 

		try (
				Connection conn = DriverManager.getConnection(Connector.getUrl());
				PreparedStatement ps = conn.prepareStatement(commandSQL);
				ResultSet rs = ps.executeQuery()) 
		{
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			while (rs.next()) {
				dataset.setValue(rs.getInt("total"), "Item", rs.getString(column));
			}
			return dataset;
		} 
		catch (SQLException error) {
			return new DefaultCategoryDataset();
		}
	}

	public static String loadTotalStatus(String tableName) {
		String commandSQL = "SELECT COUNT(*) FROM " + tableName;
		String total = "0";

		try (
				Connection conn = DriverManager.getConnection(Connector.getUrl());
				PreparedStatement ps = conn.prepareStatement(commandSQL);
				ResultSet rs = ps.executeQuery())
		{
			if (rs.next()) {
				total = rs.getString(1);
			}
			return total;
		} catch (SQLException e) {
			return total;
		}
	}
}
