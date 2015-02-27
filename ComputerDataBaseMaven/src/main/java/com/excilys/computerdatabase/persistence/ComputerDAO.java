package com.excilys.computerdatabase.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.helper.DateConverter;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.ComputerMapper;

public class ComputerDAO {

	public Computer get(int id) {
		Computer computer = new Computer();
		try {
			ConnectionDAO.INSTANCE.init();
			PreparedStatement pt = ConnectionDAO.INSTANCE.conn.prepareStatement("SELECT * FROM computer WHERE id = ?");
			pt.setInt(1, id);
			ResultSet rs = pt.executeQuery();
			if (rs.first()) {
				computer = ComputerMapper.mapperComputer(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDAO.INSTANCE.close();
		}
		return computer;
	}

	public List<Computer> getAll() {
		List<Computer> computers = new ArrayList<Computer>();
		try {
			ConnectionDAO.INSTANCE.init();
			Statement st = ConnectionDAO.INSTANCE.conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM computer");
			while (rs.next()) {
				computers.add(ComputerMapper.mapperComputer(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDAO.INSTANCE.close();
		}
		return computers;
	}

	public Computer create(Computer computer) {
		try {
			ConnectionDAO.INSTANCE.init();
			PreparedStatement pt = ConnectionDAO.INSTANCE.conn
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
			e.printStackTrace();
		} finally {
			ConnectionDAO.INSTANCE.close();
		}
		return computer;
	}

	public int update(Computer computer) {
		int i = 0;
		try {
			ConnectionDAO.INSTANCE.init();
			PreparedStatement pt = ConnectionDAO.INSTANCE.conn.prepareStatement("UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?");
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
			e.printStackTrace();
		} finally {
			ConnectionDAO.INSTANCE.close();
		}
		return i;
	}

	public int delete(Computer computer) {
		int value = 0;
		try {
			ConnectionDAO.INSTANCE.init();
			PreparedStatement pt = ConnectionDAO.INSTANCE.conn.prepareStatement("DELETE FROM computer WHERE id = ?");
			pt.setInt(1, computer.getId());
			value = pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDAO.INSTANCE.close();
		}
		return value;
	}
	

	public long getCount(){
		long value = 0;
		try {
			ConnectionDAO.INSTANCE.init();
			Statement st = ConnectionDAO.INSTANCE.conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT count(name) FROM computer");
			if(rs.first()){
				value = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDAO.INSTANCE.close();
		}
		return value;
	}
	
	public List<Computer> getAllLimit(int limit, int offset) {
		List<Computer> computers = new ArrayList<Computer>();
		try {
			ConnectionDAO.INSTANCE.init();
			PreparedStatement pt = ConnectionDAO.INSTANCE.conn.prepareStatement("SELECT * FROM computer LIMIT ? OFFSET ?");
			pt.setInt(1, limit);
			pt.setInt(2, offset);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				computers.add(ComputerMapper.mapperComputer(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDAO.INSTANCE.close();
		}
		return computers;
	}
	
	public List<Computer> findByName(String name) {
		List<Computer> computers = new ArrayList<Computer>();
		try {
			ConnectionDAO.INSTANCE.init();
			PreparedStatement pt = ConnectionDAO.INSTANCE.conn.prepareStatement("SELECT * FROM computer WHERE name LIKE ?");
			pt.setString(1, name+"%");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				computers.add(ComputerMapper.mapperComputer(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDAO.INSTANCE.close();
		}
		return computers;
	}
}
