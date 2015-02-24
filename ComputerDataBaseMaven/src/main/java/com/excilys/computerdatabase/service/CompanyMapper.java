package com.excilys.computerdatabase.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.excilys.computerdatabase.model.Company;

public class CompanyMapper {
	
	public CompanyMapper() {
	}
	
	public static Company mapperCompany(ResultSet rs){
		ResultSetMetaData rsmd = null;
		Company company = null;
		try {
		    rsmd = rs.getMetaData();
			int columnsNum = rsmd.getColumnCount();
			if (columnsNum == 2) {
				company = new Company(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			throw new RuntimeException("bug mapping computer");
		}
		return company;
	}
}
