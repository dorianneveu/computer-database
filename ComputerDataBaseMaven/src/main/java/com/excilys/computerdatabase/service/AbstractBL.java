package com.excilys.computerdatabase.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.computerdatabase.persistence.ConnectionDAO;
//Design pattern Template
public abstract class AbstractBL<T> implements Service<T> {

	@Autowired
	private ConnectionDAO connectionDAO;
	public final void delete(T object) {
//		connectionDAO.initTransaction();
		try {
			deleteAbstract(object);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
//		connectionDAO.closeTransaction();
		
	}
	
	public final void update(T object) {
//		connectionDAO.initTransaction();
		try {
			updateAbstract(object);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
//		connectionDAO.closeTransaction();
	}
	
	public final void insert(T object) {
//		connectionDAO.initTransaction();
		try {
			insertAbstract(object);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
//		connectionDAO.closeTransaction();
	}
	
	public final T get(int id) {
		T t = null;
//		connectionDAO.initTransaction();
		try {
			t = getAbstract(id);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
//		connectionDAO.closeTransaction();
		return t;
	}
	
	public final List<T> getAll() {
		List<T> t = null;
//		connectionDAO.initTransaction();
		try {
			t = getAllAbstract();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
//		connectionDAO.closeTransaction();
		return t;
	}
	
	public abstract void updateAbstract(T object) throws SQLException;
	
	public abstract void deleteAbstract(T object) throws SQLException;

	public abstract void insertAbstract(T object) throws SQLException;

	public abstract T getAbstract(int id) throws SQLException;

	public abstract List<T> getAllAbstract() throws SQLException;
	
}
