package com.excilys.computerdatabase.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.helper.Page;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ComputerDAO;
import com.excilys.computerdatabase.persistence.ConnectionDAO;
import com.excilys.computerdatabase.service.dto.ComputerDTO;
import com.excilys.computerdatabase.service.dto.MapperDTO;

public class ComputerBL extends AbstractBL<ComputerDTO> {
	
	public ComputerBL() {
	}

	/**
	 * Return the number of page related to the number of elements per page
	 * @param maxPage : number of elements per page
	 * @return
	 */
	public long getNumberPage(long maxPage) {
		long numberPage = 0;
		ConnectionDAO.INSTANCE.getConnection();
		numberPage = ComputerDAO.INSTANCE.getCount() / maxPage;
		ConnectionDAO.INSTANCE.closeConnection();
		return numberPage;
	}
	
	public int findByNameCount(String name) {
		ConnectionDAO.INSTANCE.getConnection();
		int counter = (int)ComputerDAO.INSTANCE.getCountByName(name);
		ConnectionDAO.INSTANCE.closeConnection();
		return counter;
	}
	
	public List<ComputerDTO> findByName(Page page) {
		ConnectionDAO.INSTANCE.getConnection();
		List<Computer> computers = ComputerDAO.INSTANCE.findByName(page.search, page.limit, page.offset*page.limit, page.order, page.sort);
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			computersDTO.add(MapperDTO.computerToDTO(computer));
		}
		ConnectionDAO.INSTANCE.closeConnection();
		return computersDTO;
	}
	
	/**
	 * Return a list of computer with the specified limit and offset
	 * @param limit : the beginning of the select
	 * @param offset : the number of elements we want
	 * @return
	 */
	public List<ComputerDTO> getAllLimit(int limit, int offset, String sort, String type) {
		ConnectionDAO.INSTANCE.getConnection();
		List<Computer> computers = ComputerDAO.INSTANCE.getAllLimit(limit, offset, sort, type);
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			computersDTO.add(MapperDTO.computerToDTO(computer));
		}
		ConnectionDAO.INSTANCE.closeConnection();
		return computersDTO;
	}

	@Override
	public void updateAbstract(ComputerDTO object) throws SQLException {
		Computer computer = MapperDTO.dTOToComputer(object);
		ComputerDAO.INSTANCE.update(computer);
		
	}

	@Override
	public void deleteAbstract(ComputerDTO object) throws SQLException {
		ComputerDAO.INSTANCE.delete(ComputerDAO.INSTANCE.get(object.getId()));
	}

	@Override
	public void insertAbstract(ComputerDTO object) throws SQLException {
		Computer computer = MapperDTO.dTOToComputer(object);
		ComputerDAO.INSTANCE.create(computer);
		
	}

	@Override
	public ComputerDTO getAbstract(int id) throws SQLException {
		Computer computer = ComputerDAO.INSTANCE.get(id);
		ComputerDTO computerDTO = MapperDTO.computerToDTO(computer);	
		return computerDTO;
	}

	@Override
	public List<ComputerDTO> getAllAbstract() throws SQLException {
		List<Computer> computers = ComputerDAO.INSTANCE.getAll();
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			computersDTO.add(MapperDTO.computerToDTO(computer));
		}
		return computersDTO;
	}



}
