package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum ConnectionDAO {
	INSTANCE;
 
	private String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private String user = "admincdb";
	private String passwd = "qwerty1234";
	public Connection conn;
 
	private ConnectionDAO() {
//		if (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
//				conn = DriverManager.getConnection(url, user, passwd);
			} catch (Exception e) {
				throw new IllegalStateException(e.getMessage());
			}
//		}
	}
	
	public void init(){
		try {
			if (conn == null || conn.isClosed()) {
				try {
//				Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection(url, user, passwd);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e.getMessage());
		}
	}
	
	public void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			throw new IllegalStateException(e.getMessage());
		}
	}
}
