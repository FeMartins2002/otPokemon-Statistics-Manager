package br.com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

public class CrudService {
	private CrudService() {}

	public static boolean add(String tableName, Map<String, String> formData) {
		String columns = String.join(", ", formData.keySet());
		String markers = String.join(", ", Collections.nCopies(formData.size(), "?"));
		String commandSQL = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + markers + ")";

		try (
				Connection conn = DriverManager.getConnection(Connector.getUrl());
				PreparedStatement ps = conn.prepareStatement(commandSQL)) 
		{
			int index = 1;
			for (String value : formData.values()) {
				ps.setString(index++, value);
			}
			ps.executeUpdate();
			return true;
		} 
		catch (SQLException error) {
			return false;
		}
	}

	public static boolean edit(String tableName, Map<String, String> formData) {
		Object id = formData.get("id");
		formData.remove("id");

		String setClause = String.join(", ", formData.keySet().stream().map(key -> key + " = ?").toArray(String[]::new));
		String commandSQL = "UPDATE " + tableName + " SET " + setClause + " WHERE id = ?";

		try (
				Connection conn = DriverManager.getConnection(Connector.getUrl());
				PreparedStatement ps = conn.prepareStatement(commandSQL)) 
		{
			int index = 1;
			for (Object value : formData.values()) {
				ps.setObject(index++, value);
			}
			ps.setObject(index, id);

			int affectedLines = ps.executeUpdate();
			return affectedLines > 0;
		} 
		catch (SQLException error) {
			return false;
		}
	}

	public static boolean delete(String tableName, int id) {
		String commandSQL = "DELETE FROM " + tableName + " WHERE id = " + id;

		try (
				Connection conn = DriverManager.getConnection(Connector.getUrl());
				PreparedStatement ps = conn.prepareStatement(commandSQL)) 
		{
			int affectedLines = ps.executeUpdate();
			return affectedLines > 0;
		} 
		catch (SQLException error) {
			return false;
		}
	}
}
