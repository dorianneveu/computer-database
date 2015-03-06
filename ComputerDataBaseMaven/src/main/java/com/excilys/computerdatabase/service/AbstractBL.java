package com.excilys.computerdatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdatabase.persistence.ConnectionDAO;
//Design pattern Template
public abstract class AbstractBL<T> implements Service<T> {

	public final void delete(T object) {
		ConnectionDAO.INSTANCE.initTransaction();
		try {
			deleteAbstract(object);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		ConnectionDAO.INSTANCE.closeTransaction();
		
	}
	
	public final void update(T object) {
		ConnectionDAO.INSTANCE.initTransaction();
		try {
			updateAbstract(object);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		ConnectionDAO.INSTANCE.closeTransaction();
	}
	
	public final void insert(T object) {
		ConnectionDAO.INSTANCE.initTransaction();
		try {
			insertAbstract(object);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		ConnectionDAO.INSTANCE.closeTransaction();
	}
	
	public final T get(int id) {
		T t = null;
		ConnectionDAO.INSTANCE.initTransaction();
		try {
			t = getAbstract(id);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		ConnectionDAO.INSTANCE.closeTransaction();
		return t;
	}
	
	public final List<T> getAll() {
		List<T> t = null;
		ConnectionDAO.INSTANCE.initTransaction();
		try {
			t = getAllAbstract();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		ConnectionDAO.INSTANCE.closeTransaction();
		return t;
	}
	
	public abstract void updateAbstract(T object) throws SQLException;
	
	public abstract void deleteAbstract(T object) throws SQLException;

	public abstract void insertAbstract(T object) throws SQLException;

	public abstract T getAbstract(int id) throws SQLException;

	public abstract List<T> getAllAbstract() throws SQLException;
	
	public final Connection getConnection() {
		Connection cnx = null;
		try {
			cnx = ConnectionDAO.INSTANCE.connectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnx;
	}
	
}
