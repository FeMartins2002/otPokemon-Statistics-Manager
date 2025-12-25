package br.com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Access {
	private Access() {}

	public static boolean userValidate(String username, String password) {
		String commandSQL = "SELECT username, password FROM users LIMIT 1";

		try (
				Connection conn = DriverManager.getConnection(Connector.getUrl());
				PreparedStatement ps = conn.prepareStatement(commandSQL);
				ResultSet rs = ps.executeQuery())
		{
			String usernameValue = rs.getString("username");
			String passwordValue = rs.getString("password");
			return username.equals(usernameValue) && password.equals(passwordValue);
		} 
		catch (SQLException error) {
			return false;
		}
	}

	public static boolean validateResponse(String response) {
		String commandSQL = "SELECT response FROM users LIMIT 1";

		try (
				Connection conn = DriverManager.getConnection(Connector.getUrl());
				PreparedStatement ps = conn.prepareStatement(commandSQL);
				ResultSet rs = ps.executeQuery())
		{
			String responseValue = rs.getString("response");
			return response.equals(responseValue);
		} 
		catch (SQLException error) {
			return false;
		}
	}

	public static boolean changePassword(String password) {
		String commandSQL = "UPDATE users SET password = ?";

		try (
				Connection conn = DriverManager.getConnection(Connector.getUrl());
				PreparedStatement ps = conn.prepareStatement(commandSQL)) 
		{
			ps.setString(1, password);
			int affectedLines = ps.executeUpdate();
			return affectedLines > 0;
		} 
		catch (SQLException error) {
			return false;
		}
	}

	public static boolean changeResponse(String response) {
		String commandSQL = "UPDATE users SET response = ?";

		try (
				Connection conn = DriverManager.getConnection(Connector.getUrl());
				PreparedStatement ps = conn.prepareStatement(commandSQL)) 
		{
			ps.setString(1, response);
			int affectedLines = ps.executeUpdate();
			return affectedLines > 0;
		} 
		catch (SQLException error) {
			return false;
		}
	}
}
