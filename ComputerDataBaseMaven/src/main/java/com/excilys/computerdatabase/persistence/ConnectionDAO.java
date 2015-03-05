package com.excilys.computerdatabase.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public enum ConnectionDAO {
	INSTANCE;
	private final static String PROPERTY_CONFIG = "config.properties";
	private final static String PROPERTY_CHAINE_DB = "chaineConnect";
	private final static String PROPERTY_USER_NAME = "adminDB";
	private final static String PROPERTY_USER_PASSW = "passwDB";
	private final static String PROPERTY_DRIVER_DB = "driver";
	
	private final String url;
	private final String user;
	private final String passwd;
	private final String driver;
	public BoneCP connectionPool = null;
 
	private ConnectionDAO() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
			try {
					classLoader.getResourceAsStream(PROPERTY_CONFIG);
					properties.load(classLoader.getResourceAsStream(PROPERTY_CONFIG));
				} catch (IOException e) {
				System.out.println("Unable to load config file.");
				throw new Error("pas de fichier de config ?");
			}
			
			url = properties.getProperty(PROPERTY_CHAINE_DB);
			user = properties.getProperty(PROPERTY_USER_NAME);
			passwd = properties.getProperty(PROPERTY_USER_PASSW);
			driver = properties.getProperty(PROPERTY_DRIVER_DB);
			
			try {
				Class.forName(driver);
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
	
	public void close(Connection cnx){
		try {
			cnx.close();
		} catch (SQLException e) {
			throw new IllegalStateException(e.getMessage());
		}
	}
}
