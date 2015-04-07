package com.excilys.computerdatabase.webservice;

import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdatabase.dto.ComputerDTO;

public interface IComputerBLWeb {

	public abstract void update(ComputerDTO object) throws SQLException;

	public abstract void delete(int id) throws SQLException;

	public abstract void insert(ComputerDTO object) throws SQLException;

	public abstract ComputerDTO get(int id) throws SQLException;

	public abstract List<ComputerDTO> getAll() throws SQLException;

}