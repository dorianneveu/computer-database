package com.excilys.computerdatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ComputerDAO;
import com.excilys.computerdatabase.service.dto.ComputerDTO;
import com.excilys.computerdatabase.service.dto.MapperDTO;

public class ComputerBL extends AbstractBL<ComputerDTO> {
	private Connection cnx;
	
	public ComputerBL() {
	}

	/**
	 * Return the number of page related to the number of elements per page
	 * @param maxPage : number of elements per page
	 * @return
	 */
	public long getNumberPage(long maxPage) {
		long numberPage = 0;
		cnx = getConnection();
		numberPage = ComputerDAO.INSTANCE.getCount(cnx) / maxPage;
		closeConnection(cnx);
		return numberPage;
	}
	
	public int findByNameCount(String name) {
		cnx = getConnection();
		int counter = (int)ComputerDAO.INSTANCE.getCountByName(name, cnx);
		closeConnection(cnx);
		return counter;
	}
	
	public List<ComputerDTO> findByName(String name, int limit, int offset, String sort, String type) {
		cnx = getConnection();
		List<Computer> computers = ComputerDAO.INSTANCE.findByName(name, limit, offset, sort, type, cnx);
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			computersDTO.add(MapperDTO.computerToDTO(computer));
		}
		closeConnection(cnx);
		return computersDTO;
	}
	
	/**
	 * Return a list of computer with the specified limit and offset
	 * @param limit : the beginning of the select
	 * @param offset : the number of elements we want
	 * @return
	 */
	public List<ComputerDTO> getAllLimit(int limit, int offset, String sort, String type) {
		cnx = getConnection();
		List<Computer> computers = ComputerDAO.INSTANCE.getAllLimit(limit, offset, sort, type, cnx);
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			computersDTO.add(MapperDTO.computerToDTO(computer));
		}
		closeConnection(cnx);
		return computersDTO;
	}
	
	private final void closeConnection(Connection cnx) {
		try {
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateAbstract(ComputerDTO object, Connection cnx) throws SQLException {
		Computer computer = MapperDTO.dTOToComputer(object);
		ComputerDAO.INSTANCE.update(computer, cnx);
		
	}

	@Override
	public void deleteAbstract(ComputerDTO object, Connection cnx) throws SQLException {
		cnx = getConnection();
		ComputerDAO.INSTANCE.delete(ComputerDAO.INSTANCE.get(object.getId(), cnx), cnx);
	}

	@Override
	public void insertAbstract(ComputerDTO object, Connection cnx) throws SQLException {
		Computer computer = MapperDTO.dTOToComputer(object);
		ComputerDAO.INSTANCE.create(computer, cnx);
		
	}

	@Override
	public ComputerDTO getAbstract(int id, Connection cnx) throws SQLException {
		Computer computer = ComputerDAO.INSTANCE.get(id, cnx);
		ComputerDTO computerDTO = MapperDTO.computerToDTO(computer);	
		return computerDTO;
	}

	@Override
	public List<ComputerDTO> getAllAbstract(Connection cnx) throws SQLException {
		List<Computer> computers = ComputerDAO.INSTANCE.getAll(cnx);
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			computersDTO.add(MapperDTO.computerToDTO(computer));
		}
		return computersDTO;
	}



}
