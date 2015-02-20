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

public class ComputerDAO {

	public Computer get(int id) {
		Computer computer = new Computer();
		try {
			Statement st = ConnectionDAO.INSTANCE.conn.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT * FROM computer WHERE id = "
							+ id);
			if (rs.first()) {
				computer.setId(rs.getInt(1));
				computer.setName(rs.getString(2));
				computer.setIntroduced(rs.getString(3));
				computer.setDiscontinued(rs.getString(4));
				computer.setCompany(new Company(rs.getInt(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	public List<Computer> getAll() {
		List<Computer> computers = new ArrayList<Computer>();
		try {
			Statement st = ConnectionDAO.INSTANCE.conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM computer");
			while (rs.next()) {
				computers.add(new Computer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), new Company(rs.getInt(5))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computers;
	}

	public Computer create(Computer computer) {
		try {
			PreparedStatement pt = ConnectionDAO.INSTANCE.conn
					.prepareStatement("INSERT INTO computer(name, introduced, discontinued, company_id) values (?,?,?,?)");
			pt.setString(1, computer.getName());
			pt.setTimestamp(2, new Timestamp(DateConverter.stringToDate(computer.getIntroduced()).getTime()));
			pt.setTimestamp(3, new Timestamp(DateConverter.stringToDate(computer.getIntroduced()).getTime()));
			pt.setInt(4, computer.getCompany().getId());
			pt.executeUpdate();
			ResultSet rs = pt.getGeneratedKeys();
			if (rs.next()) {
				computer.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	public void update(Computer computer) {
		try {
			PreparedStatement pt = ConnectionDAO.INSTANCE.conn
					.prepareStatement("UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?");
			pt.setString(1, computer.getName());
			pt.setTimestamp(2, new Timestamp(DateConverter.stringToDate(computer.getIntroduced()).getTime()));
			pt.setTimestamp(3, new Timestamp(DateConverter.stringToDate(computer.getIntroduced()).getTime()));
			pt.setInt(4, computer.getCompany().getId());
			pt.setInt(5, computer.getId());
			pt.executeUpdate();
			ResultSet rs = pt.getGeneratedKeys();
			if (rs.next()) {
				computer.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Computer computer) {
		try {
			PreparedStatement pt = ConnectionDAO.INSTANCE.conn
					.prepareStatement("DELETE FROM computer WHERE id = ?");
			pt.setInt(1, computer.getId());
			pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
