package com.excilys.computerdatabase.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Company;

public class CompanyDAO {

	public Company get(int id) {
		Company company = new Company();
		try {
			Statement st = ConnectionDAO.INSTANCE.conn.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT id, name FROM company WHERE id = "
							+ id);
			if (rs.first()) {
				company.setId(rs.getInt(1));
				company.setName(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

	public List<Company> getAll() {
		List<Company> companies = new ArrayList<Company>();
		try {
			Statement st = ConnectionDAO.INSTANCE.conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT id, name FROM company");
			while (rs.next()) {
				companies.add(new Company(rs.getInt(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companies;
	}

	public Company create(Company company) {
		try {
			PreparedStatement pt = ConnectionDAO.INSTANCE.conn
					.prepareStatement("INSERT INTO company(name) values (?)");
			pt.setString(1, company.getName());
			pt.executeUpdate();
			ResultSet rs = pt.getGeneratedKeys();
			if (rs.next()) {
				company.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

	public void update(Company company) {
		try {
			PreparedStatement pt = ConnectionDAO.INSTANCE.conn
					.prepareStatement("UPDATE company SET name = ? WHERE id = ?");
			pt.setString(1, company.getName());
			pt.setInt(2, company.getId());
			pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Company company) {
		try {
			PreparedStatement pt = ConnectionDAO.INSTANCE.conn
					.prepareStatement("DELETE FROM company WHERE id = ?");
			pt.setInt(1, company.getId());
			pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
