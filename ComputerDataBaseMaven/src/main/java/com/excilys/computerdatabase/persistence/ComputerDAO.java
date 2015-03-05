package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.helper.DateConverter;
import com.excilys.computerdatabase.helper.Page;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.ComputerMapper;

public enum ComputerDAO {
	INSTANCE;
	
	private ComputerDAO() {
		
	}
	public Computer get(int id, Connection cnx) {
		Computer computer = new Computer();
		try {
			PreparedStatement pt = cnx.prepareStatement("SELECT computer.*, company.name FROM computer  LEFT OUTER JOIN company ON computer.company_id = company.id WHERE computer.id = ?");
			pt.setInt(1, id);
			ResultSet rs = pt.executeQuery();
			if (rs.first()) {
				computer = ComputerMapper.mapperComputer(rs);
			}
		} catch (SQLException e) {
			throw new IllegalStateException("bug get computer");
		}
		return computer;
	}

	public List<Computer> getAll(Connection cnx) {
		List<Computer> computers = new ArrayList<Computer>();
		try {
			Statement st = cnx.createStatement();
			ResultSet rs = st.executeQuery("SELECT computer.*, company.name FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id");
			while (rs.next()) {
				computers.add(ComputerMapper.mapperComputer(rs));
			}
		} catch (SQLException e) {
			throw new IllegalStateException("bug get all computer");
		}
		return computers;
	}

	public Computer create(Computer computer, Connection cnx) {
		try {
			PreparedStatement pt = cnx
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
			throw new IllegalStateException("bug insert computer");
		}
		return computer;
	}

	public int update(Computer computer, Connection cnx) {
		int i = 0;
		try {
			PreparedStatement pt = cnx.prepareStatement("UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?");
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
			throw new IllegalStateException("bug update computer");
		}
		return i;
	}

	public int delete(Computer computer, Connection cnx) {
		int value = 0;
		try {
			PreparedStatement pt = cnx.prepareStatement("DELETE FROM computer WHERE id = ?");
			pt.setInt(1, computer.getId());
			value = pt.executeUpdate();
		} catch (SQLException e) {
			throw new IllegalStateException("bug delete computer");
		}
		return value;
	}
	
	public void deleteByCompany(Company company, Connection cnx) {
		try {
			PreparedStatement pt = cnx.prepareStatement("DELETE FROM computer WHERE company_id = ?");
			pt.setInt(1, company.getId());
			pt.executeUpdate();
			
		} catch (SQLException e) {
			try {
				cnx.rollback();
				cnx.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new IllegalStateException("bug delete computer");
		} 
	}
	
	

	public long getCount(Connection cnx) {
		long value = 0;
		try {
			Statement st = cnx.createStatement();
			ResultSet rs = st.executeQuery("SELECT count(name) FROM computer");
			if(rs.first()){
				value = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public long getCountByName(String name, Connection cnx) {
		long value = 0;
		try {
			PreparedStatement pt = cnx.prepareStatement("SELECT count(name) FROM computer WHERE name like ? ");
			pt.setString(1, name+"%");
			ResultSet rs = pt.executeQuery();
			if(rs.first()){
				value = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public List<Computer> getAllLimit(int limit, int offset, String sort, String type, Connection cnx) {
		List<Computer> computers = new ArrayList<Computer>();
		try {
			PreparedStatement pt = cnx.prepareStatement("SELECT computer.*, company.name FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id ORDER BY "+sort+" "+type+" LIMIT ? OFFSET ?");
			pt.setInt(1, limit);
			pt.setInt(2, offset);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				computers.add(ComputerMapper.mapperComputer(rs));
			}
		} catch (SQLException e) {
			throw new IllegalStateException("bug get computer with limit");
		}
		return computers;
	}
	
	public List<Computer> findByName(String name, int limit, int offset, String sort, String type, Connection cnx) {
		List<Computer> computers = new ArrayList<Computer>();
		try {
			PreparedStatement pt = cnx.prepareStatement("SELECT computer.*, company.name FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? ORDER BY "+sort+" "+type+" LIMIT ? OFFSET ?");
			pt.setString(1, name+"%");
			pt.setInt(2, limit);
			pt.setInt(3, offset);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				computers.add(ComputerMapper.mapperComputer(rs));
			}
		} catch (SQLException e) {
			throw new IllegalStateException("bug find by name");
		}
		return computers;
	}
}
