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

	public long getNumberPage(long maxPage) {
		long numberPage = 0;
		numberPage = dao.getCount() / maxPage;
		return numberPage;
	}
	
	public List<ComputerDTO> findById(String name) {
		List<Computer> computers = dao.findByName(name);
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
	
	public List<ComputerDTO> getAllLimit(int limit, int offset) {
		List<Computer> computers = dao.getAllLimit(limit, offset);
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
}
