package com.excilys.computerdatabase.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

//public enum ConnectionDAO {
@Component
public class ConnectionDAO {
//	INSTANCE;
	private final static String PROPERTY_CONFIG = "config.properties";
//	private final static String PROPERTY_CHAINE_DB = "chaineConnect";
//	private final static String PROPERTY_USER_NAME = "adminDB";
//	private final static String PROPERTY_USER_PASSW = "passwDB";
//	private final static String PROPERTY_DRIVER_DB = "driver";
	
//	private final String url;
//	private final String user;
//	private final String passwd;
//	private final String driver;
//	public BoneCP connectionPool = null;
	private static ThreadLocal<DataSource> connectionThreadLocal = new ThreadLocal<DataSource>();
	private static Logger log = LoggerFactory.getLogger(ConnectionDAO.class);

	private static DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		ConnectionDAO.dataSource = dataSource;
	}
	
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
			
//			url = properties.getProperty(PROPERTY_CHAINE_DB);
//			user = properties.getProperty(PROPERTY_USER_NAME);
//			passwd = properties.getProperty(PROPERTY_USER_PASSW);
//			driver = properties.getProperty(PROPERTY_DRIVER_DB);
//			
//			try {
//				Class.forName(driver);
//			} catch (Exception e) {
//				throw new IllegalStateException(e.getMessage());
//			}			
//			try {
//				BoneCPConfig config = new BoneCPConfig();

//				config.setJdbcUrl(url);
//				config.setUsername(user);
//				config.setPassword(passwd);
//
//				config.setMinConnectionsPerPartition(5);
//				config.setMaxConnectionsPerPartition(10);
//				config.setPartitionCount(2);

//				connectionPool = new BoneCP(config);

//			} catch (SQLException e) {
//				e.printStackTrace();
//				throw new IllegalStateException(
//						"Erreur de configuration du pool de connexions.", e);
//			}
	}
	
	public void close(Connection cnx){
		try {
			cnx.close();
		} catch (SQLException e) {
			throw new IllegalStateException(e.getMessage());
		}
	}
	
	public DataSource getConnection() {
//		Connection connection = null;
		if (connectionThreadLocal.get() != null) {
			log.debug("get a connection from the threadlocal " + connectionThreadLocal.get().hashCode());
			return connectionThreadLocal.get();
		}
		try {
//			connection = dataSource.getConnection();
			connectionThreadLocal.set(dataSource);
//			connection.setAutoCommit(true);
			log.debug("put a connection to the threadlocal "
					+ connectionThreadLocal.get().hashCode());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return dataSource;
	}
	
//	public void initTransaction() {
//		Connection connection = null;
//		try {
//			connection = dataSource.getConnection();
//			connection.setAutoCommit(false);
//		} catch (Exception e) {
//			log.error(e.getMessage());
//		}
//		connectionThreadLocal.set(connection);
//		log.debug("put a connection for transaction to the threadlocal "
//				+ connectionThreadLocal.get().hashCode());
//	}
//
//	public void closeTransaction() {
//		Connection connection = connectionThreadLocal.get();
//		try {
//			connection.commit();
//			connection.close();
//		} catch (SQLException e) {
//			log.error(e.getMessage());
//		}
//		connectionThreadLocal.remove();
//	}
//
//	public void closeConnection() {
//		Connection connection = connectionThreadLocal.get();
//		boolean isAutocomit = false;
//		try {
//			isAutocomit = connection.getAutoCommit();
//			log.debug(isAutocomit + "");
//		} catch (SQLException e) {
//			log.error(e.getMessage());
//		}
//		if (isAutocomit) {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				log.error(e.getMessage());
//			}
//			connectionThreadLocal.remove();
//			log.debug("remove from the threadlocal "
//					+ connectionThreadLocal.hashCode());
//		}
//	}
//
//	public void rollback() {
//		try {
//			connectionThreadLocal.get().rollback();
//		} catch (SQLException e) {
//			log.error(e.getMessage());
//		}
//	}

	
	
}
