package com.excilys.computerdatabase.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.helper.Page;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.IComputerDAO;
import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.MapperDTO;

@Service
public class ComputerBL extends AbstractBL<ComputerDTO> {
	@Autowired
	private IComputerDAO computerDAO;
	public ComputerBL() {
		
	}

	/**
	 * Return the number of page related to the number of elements per page
	 * @param maxPage : number of elements per page
	 * @return
	 */
	public long getNumberPage(long maxPage) {
		long numberPage = 0;
		numberPage = computerDAO.getCount() / maxPage;
		return numberPage;
	}
	
	public int findByNameCount(String name) {
		int counter = (int)computerDAO.getCountByName(name);
		return counter;
	}
	
	public List<ComputerDTO> findByName(Page page) {
		List<Computer> computers = computerDAO.findByName(page.search, page.page, page.offset*page.page, page.order, page.sort);
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			computersDTO.add(MapperDTO.computerToDTO(computer));
		}
		return computersDTO;
	}
	
	@Override
	public void updateAbstract(ComputerDTO object) throws SQLException {
		Computer computer = MapperDTO.dTOToComputer(object);
		computerDAO.update(computer);
		
	}

	@Override
	public void deleteAbstract(ComputerDTO object) throws SQLException {
		computerDAO.delete(computerDAO.get(object.getId()));
	}

	@Override
	public void insertAbstract(ComputerDTO object) throws SQLException {
		Computer computer = MapperDTO.dTOToComputer(object);
		computerDAO.create(computer);
		
	}

	@Override
	public ComputerDTO getAbstract(int id) throws SQLException {
		Computer computer = computerDAO.get(id);
		ComputerDTO computerDTO = MapperDTO.computerToDTO(computer);	
		return computerDTO;
	}

	@Override
	public List<ComputerDTO> getAllAbstract() throws SQLException {
		List<Computer> computers = computerDAO.getAll();
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			computersDTO.add(MapperDTO.computerToDTO(computer));
		}
		return computersDTO;
	}
}
