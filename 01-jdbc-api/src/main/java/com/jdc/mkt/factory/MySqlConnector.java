package com.jdc.mkt.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlConnector {

	private static final String URL = "jdbc:mysql://localhost:3306/testDb";
	private static final String USER = "root";
	private static final String PASSWORD = "admin";
	
	public static Connection getConnectionWith3String() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	public static Connection getConnectionWith1String() throws SQLException {
		return DriverManager.getConnection(URL+"?user=root&password=admin");
	}
	
	public static Connection getConnectionWithProps() throws SQLException {
		var props = new Properties();
		props.put("user", USER);
		props.put("password", PASSWORD);
		
		return DriverManager.getConnection(URL,props);
	}
}
