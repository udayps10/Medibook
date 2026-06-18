package com.medibook.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
	private static final String URL = "jdbc:mysql://localhost:3306/medibook?allowPublicKeyRetrieval=true&useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "your_password_here";

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("✅ DB Connected");
		} catch (Exception e) {
			System.out.println("❌ Failed to connect to database");
			e.printStackTrace();
		}
		return con;
	}
}
