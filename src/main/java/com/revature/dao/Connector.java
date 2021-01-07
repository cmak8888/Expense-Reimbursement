package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	private static final String URL = "jdbc:postgresql://localhost/postgres";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin321";

	private static Connection conn;

	public static Connection getConnection() {

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
}
