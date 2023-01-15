package com.trainingprogram.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class JDBCUtils {

	private static String jdbcURL = "jdbc:mysql://localhost:3306/training_department";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "fuck";

	public static Connection getConnection() {
		Connection connection = null;
		try {
//			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
}
