package com.excilys.computerdatabase.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

public class MapperComputerJDBC implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		Computer computer = new Computer();
		Company company = new Company(rs.getInt(5), rs.getString(6));
		computer.setId(rs.getInt(1));
		computer.setName(rs.getString(2));
		computer.setIntroduced(rs.getString(3));
		computer.setDiscontinued(rs.getString(4));
		computer.setCompany(company);
		return computer;
	}

}
