package edu.northeastern.cs5200;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://cs5200-spring2020-liuzc.cprhllcintzh.us-east-1.rds.amazonaws.com:3306/cs5200";
	private static final String USER = "nickyl";
	private static final String PASSWORD = "asdf1234";
	private static Connection dbConnection = null;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		if (dbConnection == null) {
			dbConnection = DriverManager.getConnection(URL, USER, PASSWORD);
			return dbConnection;
		} else {
			return dbConnection;
		}
	}

	public static void closeConnection() {
		try {
			if (dbConnection != null) {
				dbConnection.close();
				dbConnection = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
