package com.excilys.computerdatabase.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.CompanyMapper;

public class CompanyDAO {

	public Company get(int id) {
		Company company = new Company();
		try {
			ConnectionDAO.INSTANCE.init();
			PreparedStatement pt = ConnectionDAO.INSTANCE.conn.prepareStatement("SELECT id, name FROM company WHERE id = ?");
			pt.setInt(1, id);
			ResultSet rs = pt.executeQuery();
			if (rs.first()) {
				company = CompanyMapper.mapperCompany(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			ConnectionDAO.INSTANCE.close();
		}
		return company;
	}

	public List<Company> getAll() {
		List<Company> companies = new ArrayList<Company>();
		try {
			ConnectionDAO.INSTANCE.init();
			Statement st = ConnectionDAO.INSTANCE.conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT id, name FROM company");
			while (rs.next()) {
				companies.add(CompanyMapper.mapperCompany(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDAO.INSTANCE.close();
		}
		return companies;
	}

	public Company create(Company company) {
		try {
			ConnectionDAO.INSTANCE.init();
			PreparedStatement pt = ConnectionDAO.INSTANCE.conn.prepareStatement("INSERT INTO company(name) values (?)");
			pt.setString(1, company.getName());
			pt.executeUpdate();
			ResultSet rs = pt.getGeneratedKeys();
			if (rs.next()) {
				company.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDAO.INSTANCE.close();
		}
		return company;
	}

	public void update(Company company) {
		try {
			ConnectionDAO.INSTANCE.init();
			PreparedStatement pt = ConnectionDAO.INSTANCE.conn.prepareStatement("UPDATE company SET name = ? WHERE id = ?");
			pt.setString(1, company.getName());
			pt.setInt(2, company.getId());
			pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDAO.INSTANCE.close();
		}
	}

	public void delete(Company company) {
		try {
			ConnectionDAO.INSTANCE.init();
			PreparedStatement pt = ConnectionDAO.INSTANCE.conn.prepareStatement("DELETE FROM company WHERE id = ?");
			pt.setInt(1, company.getId());
			pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDAO.INSTANCE.close();
		}
	}

}
