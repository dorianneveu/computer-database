package com.excilys.computerdatabase.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ComputerDAO;
import com.excilys.computerdatabase.service.dto.ComputerDTO;
import com.excilys.computerdatabase.service.dto.MapperDTO;

public class ComputerBL {
	ComputerDAO dao;
	
	public ComputerBL() {
		dao = new ComputerDAO();
	}

	/**
	 * Return the number of page related to the number of elements per page
	 * @param maxPage : number of elements per page
	 * @return
	 */
	public long getNumberPage(long maxPage) {
		long numberPage = 0;
		numberPage = dao.getCount() / maxPage;
		return numberPage;
	}
	
	public int findByNameCount(String name) {
		return (int)dao.getCountByName(name);
	}
	
	public int update(ComputerDTO computerDTO) {
		Computer computer = MapperDTO.dTOToComputer(computerDTO);
		return this.dao.update(computer);
	}
	
	public ComputerDTO get(int id) {
		Computer computer = dao.get(id);
		ComputerDTO computerDTO = MapperDTO.computerToDTO(computer);	
		return computerDTO;
	}
	
	public List<ComputerDTO> findByName(String name, int limit, int offset, String sort, String type) {
		List<Computer> computers = dao.findByName(name, limit, offset, sort, type);
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			computersDTO.add(MapperDTO.computerToDTO(computer));
		}
		return computersDTO;
	}
	
	public List<ComputerDTO> getAll() {
		List<Computer> computers = dao.getAll();
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			computersDTO.add(MapperDTO.computerToDTO(computer));
		}
		return computersDTO;
	}
	
	/**
	 * Return a list of computer with the specified limit and offset
	 * @param limit : the beginning of the select
	 * @param offset : the number of elements we want
	 * @return
	 */
	public List<ComputerDTO> getAllLimit(int limit, int offset, String sort, String type) {
		List<Computer> computers = dao.getAllLimit(limit, offset, sort, type);
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			computersDTO.add(MapperDTO.computerToDTO(computer));
		}
		return computersDTO;
	}
	
	public Computer insertComputer(ComputerDTO computerDTO) {
		Computer computer = MapperDTO.dTOToComputer(computerDTO);
		return this.dao.create(computer);
	}
	
	public int deleteComputer(String str) {
		return dao.delete(dao.get(Integer.parseInt(str)));
	}
}
