package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.CompanyMapper;

public class CompanyDAO {
	
private Connection cnx;
	
	public CompanyDAO() {
		
	}
	
	public CompanyDAO(Connection cnx) {
		this.cnx = cnx;
	}

	public Company get(int id) {
		Company company = new Company();
		try {
			PreparedStatement pt = ConnectionDAO.INSTANCE.connectionPool.getConnection().prepareStatement("SELECT id, name FROM company WHERE id = ?");
			pt.setInt(1, id);
			ResultSet rs = pt.executeQuery();
			if (rs.first()) {
				company = CompanyMapper.mapperCompany(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ConnectionDAO.INSTANCE.connectionPool.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return company;
	}

	public List<Company> getAll() {
		List<Company> companies = new ArrayList<Company>();
		try {
			Statement st = ConnectionDAO.INSTANCE.connectionPool.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT id, name FROM company");
			while (rs.next()) {
				companies.add(CompanyMapper.mapperCompany(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ConnectionDAO.INSTANCE.connectionPool.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return companies;
	}

	public Company create(Company company) {
		try {
			PreparedStatement pt = ConnectionDAO.INSTANCE.connectionPool.getConnection().prepareStatement("INSERT INTO company(name) values (?)");
			pt.setString(1, company.getName());
			pt.executeUpdate();
			ResultSet rs = pt.getGeneratedKeys();
			if (rs.next()) {
				company.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ConnectionDAO.INSTANCE.connectionPool.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return company;
	}

	public void update(Company company) {
		try {
			PreparedStatement pt = ConnectionDAO.INSTANCE.connectionPool.getConnection().prepareStatement("UPDATE company SET name = ? WHERE id = ?");
			pt.setString(1, company.getName());
			pt.setInt(2, company.getId());
			pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ConnectionDAO.INSTANCE.connectionPool.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void delete(Company company) {
		try {
//			PreparedStatement pt = ConnectionDAO.INSTANCE.connectionPool.getConnection().prepareStatement("DELETE FROM company WHERE id = ?");
			PreparedStatement pt = cnx.prepareStatement("DELETE FROM company WHERE id = ?");
			pt.setInt(1, company.getId());
			pt.executeUpdate();
		} catch (SQLException e) {
			try {
				cnx.rollback();
				cnx.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
//		finally {
//			try {
//				ConnectionDAO.INSTANCE.connectionPool.getConnection().close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}

}
