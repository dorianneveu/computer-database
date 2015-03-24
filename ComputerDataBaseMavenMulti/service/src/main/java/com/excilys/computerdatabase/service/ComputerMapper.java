package com.excilys.computerdatabase.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

public class ComputerMapper {

	public ComputerMapper() {
	}
	
	public static Computer mapperComputer(ResultSet rs){
		ResultSetMetaData rsmd = null;
		Computer computer = null;
		try {
		    rsmd = rs.getMetaData();
			int columnsNum = rsmd.getColumnCount();
			if (columnsNum == 6) {
				Company company = new Company(rs.getInt(5), rs.getString(6));
				computer = new Computer(rs.getInt(1), rs.getString(2), null, null, company);
				computer.setIntroduced(rs.getTimestamp(3));
				computer.setDiscontinued(rs.getTimestamp(4));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException("bug mapping computer");
		}
		return computer;
	}
}
