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
			if (columnsNum == 5) {
				computer = new Computer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), new Company(rs.getInt(5)));
			}
		} catch (SQLException e) {
			throw new RuntimeException("bug mapping computer");
		}
		return computer;
	}
}
