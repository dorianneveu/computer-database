package com.excilys.computerdatabase.persistence;

import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public enum ConnectionDAO {
	INSTANCE;
 
	private String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private String user = "admincdb";
	private String passwd = "qwerty1234";
//	public Connection conn;
	public BoneCP connectionPool = null;
 
	private ConnectionDAO() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (Exception e) {
				throw new IllegalStateException(e.getMessage());
			}			
			try {
				/*
				 * 
				 * Création d'une configuration de pool de connexions via l'objet
				 * 
				 * BoneCPConfig et les différents setters associés.
				 */
				BoneCPConfig config = new BoneCPConfig();

				/* Mise en place de l'URL, du nom et du mot de passe */

				config.setJdbcUrl(url);
				config.setUsername(user);
				config.setPassword(passwd);

				/* Paramétrage de la taille du pool */

				config.setMinConnectionsPerPartition(5);
				config.setMaxConnectionsPerPartition(10);
				config.setPartitionCount(2);

				/* Création du pool à partir de la configuration, via l'objet BoneCP */
				connectionPool = new BoneCP(config);

			} catch (SQLException e) {
				e.printStackTrace();
				throw new IllegalStateException(
						"Erreur de configuration du pool de connexions.", e);
			}
	}
	
//	public void init(){
//		try {
//			if (conn == null || conn.isClosed()) {
//				try {
//					conn = DriverManager.getConnection(url, user, passwd);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		} catch (SQLException e) {
//			throw new IllegalStateException(e.getMessage());
//		}
//		
//		
//		
//	}
//	
//	public void close(){
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			throw new IllegalStateException(e.getMessage());
//		}
//	}
}
