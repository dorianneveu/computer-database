package com.excilys.computerdatabase.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.helper.DateConverter;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.ComputerMapper;

public enum ComputerDAO implements IComputerDAO {
	INSTANCE;
	
	private ComputerDAO() {
		
	}
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#get(int, java.sql.Connection)
	 */
	@Override
	public Computer get(int id) {
		Computer computer = new Computer();
		try {
			PreparedStatement pt = ConnectionDAO.INSTANCE.getConnection().prepareStatement("SELECT computer.*, company.name FROM computer  LEFT OUTER JOIN company ON computer.company_id = company.id WHERE computer.id = ?");
			pt.setInt(1, id);
			ResultSet rs = pt.executeQuery();
			if (rs.first()) {
				computer = ComputerMapper.mapperComputer(rs);
			}
		} catch (SQLException e) {
			ConnectionDAO.INSTANCE.rollback();
			throw new IllegalStateException("bug get computer");
		}
		return computer;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#getAll(java.sql.Connection)
	 */
	@Override
	public List<Computer> getAll() {
		List<Computer> computers = new ArrayList<Computer>();
		try {
			Statement st = ConnectionDAO.INSTANCE.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT computer.*, company.name FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id");
			while (rs.next()) {
				computers.add(ComputerMapper.mapperComputer(rs));
			}
		} catch (SQLException e) {
			ConnectionDAO.INSTANCE.rollback();
			throw new IllegalStateException("bug get all computer");
		}
		return computers;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#create(com.excilys.computerdatabase.model.Computer, java.sql.Connection)
	 */
	@Override
	public Computer create(Computer computer) {
		try {
			PreparedStatement pt = ConnectionDAO.INSTANCE.getConnection()
					.prepareStatement("INSERT INTO computer(name, introduced, discontinued, company_id) values (?,?,?,?)");
			pt.setString(1, computer.getName());
			if (!String.valueOf(computer.getIntroduced()).equals("0000-00-00") && computer.getIntroduced() != null) {
				pt.setTimestamp(2, new Timestamp(DateConverter.stringToDate(computer.getIntroduced()).getTime()));
			} else {
				pt.setNull(2, java.sql.Types.TIMESTAMP);
			}
			if (!String.valueOf(computer.getDiscontinued()).equals("0000-00-00") && computer.getDiscontinued() != null) {
				pt.setTimestamp(3, new Timestamp(DateConverter.stringToDate(computer.getDiscontinued()).getTime()));
			} else {
				pt.setNull(3, java.sql.Types.TIMESTAMP);
			}
			if(computer.getCompany() != null && computer.getCompany().getId() != 0) {
				pt.setInt(4, computer.getCompany().getId());
			}
			else {
				pt.setNull(4, java.sql.Types.BIGINT);
			}
			pt.executeUpdate();
			ResultSet rs = pt.getGeneratedKeys();
			if (rs.next()) {
				computer.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			ConnectionDAO.INSTANCE.rollback();
			throw new IllegalStateException("bug insert computer");
		}
		return computer;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#update(com.excilys.computerdatabase.model.Computer, java.sql.Connection)
	 */
	@Override
	public int update(Computer computer) {
		int i = 0;
		try {
			PreparedStatement pt = ConnectionDAO.INSTANCE.getConnection().prepareStatement("UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?");
			pt.setString(1, computer.getName());
			if (!String.valueOf(computer.getIntroduced()).equals("0000-00-00") && computer.getIntroduced() != null) {
				pt.setTimestamp(2, new Timestamp(DateConverter.stringToDate(computer.getIntroduced()).getTime()));
			} else {
				pt.setNull(2, java.sql.Types.TIMESTAMP);
			}
			if (!String.valueOf(computer.getDiscontinued()).equals("0000-00-00") && computer.getDiscontinued() != null) {
				pt.setTimestamp(3, new Timestamp(DateConverter.stringToDate(computer.getDiscontinued()).getTime()));
			} else {
				pt.setNull(3, java.sql.Types.TIMESTAMP);
			}
			pt.setInt(4, computer.getCompany().getId());
			pt.setInt(5, computer.getId());
			i = pt.executeUpdate();
			ResultSet rs = pt.getGeneratedKeys();
			if (rs.next()) {
				computer.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			ConnectionDAO.INSTANCE.rollback();
			throw new IllegalStateException("bug update computer");
		}
		return i;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#delete(com.excilys.computerdatabase.model.Computer, java.sql.Connection)
	 */
	@Override
	public int delete(Computer computer) {
		int value = 0;
		try {
			PreparedStatement pt = ConnectionDAO.INSTANCE.getConnection().prepareStatement("DELETE FROM computer WHERE id = ?");
			pt.setInt(1, computer.getId());
			value = pt.executeUpdate();
		} catch (SQLException e) {
			ConnectionDAO.INSTANCE.rollback();
			throw new IllegalStateException("bug delete computer");
		}
		return value;
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#deleteByCompany(com.excilys.computerdatabase.model.Company, java.sql.Connection)
	 */
	@Override
	public void deleteByCompany(Company company) {
		try {
			PreparedStatement pt = ConnectionDAO.INSTANCE.getConnection().prepareStatement("DELETE FROM computer WHERE company_id = ?");
			pt.setInt(1, company.getId());
			pt.executeUpdate();
			
		} catch (SQLException e) {
			ConnectionDAO.INSTANCE.rollback();
			throw new IllegalStateException("bug delete computer");
		} 
	}
	
	

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#getCount(java.sql.Connection)
	 */
	@Override
	public long getCount() {
		long value = 0;
		try {
			Statement st = ConnectionDAO.INSTANCE.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT count(name) FROM computer");
			if(rs.first()){
				value = rs.getLong(1);
			}
		} catch (SQLException e) {
			ConnectionDAO.INSTANCE.rollback();
			e.printStackTrace();
		}
		return value;
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#getCountByName(java.lang.String, java.sql.Connection)
	 */
	@Override
	public long getCountByName(String name) {
		long value = 0;
		try {
			PreparedStatement pt = ConnectionDAO.INSTANCE.getConnection().prepareStatement("SELECT count(name) FROM computer WHERE name like ? ");
			pt.setString(1, name+"%");
			ResultSet rs = pt.executeQuery();
			if(rs.first()){
				value = rs.getLong(1);
			}
		} catch (SQLException e) {
			ConnectionDAO.INSTANCE.rollback();
			e.printStackTrace();
		}
		return value;
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#getAllLimit(int, int, java.lang.String, java.lang.String, java.sql.Connection)
	 */
	@Override
	public List<Computer> getAllLimit(int limit, int offset, String sort, String type) {
		List<Computer> computers = new ArrayList<Computer>();
		try {
			PreparedStatement pt = ConnectionDAO.INSTANCE.getConnection().prepareStatement("SELECT computer.*, company.name FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id ORDER BY "+sort+" "+type+" LIMIT ? OFFSET ?");
			pt.setInt(1, limit);
			pt.setInt(2, offset);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				computers.add(ComputerMapper.mapperComputer(rs));
			}
		} catch (SQLException e) {
			ConnectionDAO.INSTANCE.rollback();
			throw new IllegalStateException("bug get computer with limit");
		}
		return computers;
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#findByName(java.lang.String, int, int, java.lang.String, java.lang.String, java.sql.Connection)
	 */
	@Override
	public List<Computer> findByName(String name, int limit, int offset, String sort, String type) {
		List<Computer> computers = new ArrayList<Computer>();
		try {
			PreparedStatement pt = ConnectionDAO.INSTANCE.getConnection().prepareStatement("SELECT computer.*, company.name FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? ORDER BY "+sort+" "+type+" LIMIT ? OFFSET ?");
			pt.setString(1, name+"%");
			pt.setInt(2, limit);
			pt.setInt(3, offset);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				computers.add(ComputerMapper.mapperComputer(rs));
			}
		} catch (SQLException e) {
			ConnectionDAO.INSTANCE.rollback();
			throw new IllegalStateException("bug find by name");
		}
		return computers;
	}
}
