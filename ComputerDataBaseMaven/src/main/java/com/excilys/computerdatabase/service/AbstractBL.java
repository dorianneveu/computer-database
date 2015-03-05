package com.excilys.computerdatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdatabase.persistence.ConnectionDAO;
//Design pattern Template
public abstract class AbstractBL<T> implements Service<T> {

	public final void delete(T object) {
		Connection cnx = null;
		try {
			cnx = ConnectionDAO.INSTANCE.connectionPool.getConnection();
			cnx.setAutoCommit(false);
			deleteAbstract(object, cnx);
			cnx.commit();
		} catch (SQLException e) {
			try {
				cnx.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				cnx.setAutoCommit(true);
				cnx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public final void update(T object) {
		Connection cnx = null;
		try {
			cnx = ConnectionDAO.INSTANCE.connectionPool.getConnection();
			cnx.setAutoCommit(false);
			updateAbstract(object, cnx);
			cnx.commit();
		} catch (SQLException e) {
			try {
				cnx.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				cnx.setAutoCommit(true);
				cnx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public final void insert(T object) {
		Connection cnx = null;
		try {
			cnx = ConnectionDAO.INSTANCE.connectionPool.getConnection();
			cnx.setAutoCommit(false);
			insertAbstract(object, cnx);
			cnx.commit();
		} catch (SQLException e) {
			try {
				cnx.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				cnx.setAutoCommit(true);
				cnx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public final T get(int id) {
		Connection cnx = null;
		T t = null;
		try {
			cnx = ConnectionDAO.INSTANCE.connectionPool.getConnection();
			cnx.setAutoCommit(false);
			t = getAbstract(id, cnx);
			cnx.commit();
		} catch (SQLException e) {
			try {
				cnx.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				cnx.setAutoCommit(true);
				cnx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return t;
	}
	
	public final List<T> getAll() {
		Connection cnx = null;
		List<T> t = null;
		try {
			cnx = ConnectionDAO.INSTANCE.connectionPool.getConnection();
			cnx.setAutoCommit(false);
			t = getAllAbstract(cnx);
			cnx.commit();
		} catch (SQLException e) {
			try {
				cnx.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				cnx.setAutoCommit(true);
				cnx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return t;
	}
	
	public abstract void updateAbstract(T object, Connection cnx) throws SQLException;
	
	public abstract void deleteAbstract(T object, Connection cnx) throws SQLException;

	public abstract void insertAbstract(T object, Connection cnx) throws SQLException;

	public abstract T getAbstract(int id, Connection cnx) throws SQLException;

	public abstract List<T> getAllAbstract(Connection cnx) throws SQLException;
	
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
