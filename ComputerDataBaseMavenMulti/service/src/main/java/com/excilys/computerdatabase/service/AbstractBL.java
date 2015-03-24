package com.excilys.computerdatabase.service;

import java.sql.SQLException;
import java.util.List;

//Design pattern Template
public abstract class AbstractBL<T> implements Service<T> {

	public final void delete(T object) {

//		Transaction tx = HibernateUtils.INSTANCE.sessionFactory.openSession().getTransaction();
		try {
			deleteAbstract(object);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
//		tx.commit();
	}
	
	public final void update(T object) {
		try {
			updateAbstract(object);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	public final void insert(T object) {
		try {
			insertAbstract(object);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	public final T get(int id) {
		T t = null;
		try {
			t = getAbstract(id);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return t;
	}
	
	public final List<T> getAll() {
		List<T> t = null;
		try {
			t = getAllAbstract();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return t;
	}
	
	public abstract void updateAbstract(T object) throws SQLException;
	
	public abstract void deleteAbstract(T object) throws SQLException;

	public abstract void insertAbstract(T object) throws SQLException;

	public abstract T getAbstract(int id) throws SQLException;

	public abstract List<T> getAllAbstract() throws SQLException;
	
}
