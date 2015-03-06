package com.excilys.computerdatabase.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.CompanyMapper;

public enum CompanyDAO implements ICompanyDAO {
	INSTANCE;
	
	private CompanyDAO() {
		
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.ICompanyDAO#get(int)
	 */
	@Override
	public Company get(int id) {
		Company company = new Company();
		try {
			PreparedStatement pt = ConnectionDAO.INSTANCE.getConnection().prepareStatement("SELECT id, name FROM company WHERE id = ?");
			pt.setInt(1, id);
			ResultSet rs = pt.executeQuery();
			if (rs.first()) {
				company = CompanyMapper.mapperCompany(rs);
			}
		} catch (SQLException e) {
			ConnectionDAO.INSTANCE.rollback();
			e.printStackTrace();
		}
		return company;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.ICompanyDAO#getAll()
	 */
	@Override
	public List<Company> getAll() {
		List<Company> companies = new ArrayList<Company>();
		try {
			Statement st = ConnectionDAO.INSTANCE.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT id, name FROM company");
			while (rs.next()) {
				companies.add(CompanyMapper.mapperCompany(rs));
			}
		} catch (SQLException e) {
			ConnectionDAO.INSTANCE.rollback();
			e.printStackTrace();
		}
		return companies;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.ICompanyDAO#create(com.excilys.computerdatabase.model.Company)
	 */
	@Override
	public Company create(Company company) {
		try {
			PreparedStatement pt = ConnectionDAO.INSTANCE.getConnection().prepareStatement("INSERT INTO company(name) values (?)");
			pt.setString(1, company.getName());
			pt.executeUpdate();
			ResultSet rs = pt.getGeneratedKeys();
			if (rs.next()) {
				company.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			ConnectionDAO.INSTANCE.rollback();
			e.printStackTrace();
		}
		return company;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.ICompanyDAO#update(com.excilys.computerdatabase.model.Company)
	 */
	@Override
	public void update(Company company) {
		try {
			PreparedStatement pt = ConnectionDAO.INSTANCE.getConnection().prepareStatement("UPDATE company SET name = ? WHERE id = ?");
			pt.setString(1, company.getName());
			pt.setInt(2, company.getId());
			pt.executeUpdate();
		} catch (SQLException e) {
			ConnectionDAO.INSTANCE.rollback();
			e.printStackTrace();
		} 
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.ICompanyDAO#delete(com.excilys.computerdatabase.model.Company)
	 */
	@Override
	public void delete(Company company) {
		try {
			PreparedStatement pt = ConnectionDAO.INSTANCE.getConnection().prepareStatement("DELETE FROM company WHERE id = ?");
			pt.setInt(1, company.getId());
			pt.executeUpdate();
		} catch (SQLException e) {
			ConnectionDAO.INSTANCE.rollback();
			e.printStackTrace();
		} 
	}

}
