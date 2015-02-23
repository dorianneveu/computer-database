package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public enum ConnectionDAO {
	INSTANCE;
 
	private String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private String user = "admincdb";
	private String passwd = "qwerty1234";
	public Connection conn;
 
	private ConnectionDAO() {
		if (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, passwd);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
