package com.excilys.computerdatabase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.helper.Page;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.IComputerDAO;
import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.MapperDTO;

@Service
public class ComputerBL implements IComputerBL {
	@Autowired
	private IComputerDAO computerDAO;
	public ComputerBL() {
	}
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.IComputerBL#getNumberPage(long)
	 */
	@Override
	public long getNumberPage(long maxPage) {
		long numberPage = 0;
		numberPage = computerDAO.getCount() / maxPage;
		return numberPage;
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.IComputerBL#findByNameCount(java.lang.String)
	 */
	@Override
	public int findByNameCount(String name) {
		int counter = (int)computerDAO.getCountByName(name);
		return counter;
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.IComputerBL#findByName(com.excilys.computerdatabase.helper.Page)
	 */
	@Override
	public List<ComputerDTO> findByName(Page page) {
		List<Computer> computers = computerDAO.findByName(page.search, page.page, page.offset*page.page, page.order, page.sort);
		List<ComputerDTO> computersDTO = MapperDTO.listToDto(computers);
		return computersDTO;
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.IComputerBL#update(com.excilys.computerdatabase.dto.ComputerDTO)
	 */
	@Override
	public void update(ComputerDTO object) {
		Computer computer = MapperDTO.dTOToComputer(object);
		computerDAO.update(computer);
		
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.IComputerBL#delete(com.excilys.computerdatabase.dto.ComputerDTO)
	 */
	@Override
	public void delete(ComputerDTO object) {
		computerDAO.delete(computerDAO.get(object.getId()));
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.IComputerBL#insert(com.excilys.computerdatabase.dto.ComputerDTO)
	 */
	@Override
	public void insert(ComputerDTO object) {
		Computer computer = MapperDTO.dTOToComputer(object);
		computerDAO.create(computer);
		
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.IComputerBL#get(int)
	 */
	@Override
	public ComputerDTO get(int id) {
		Computer computer = computerDAO.get(id);
		ComputerDTO computerDTO = MapperDTO.computerToDTO(computer);	
		return computerDTO;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.IComputerBL#getAll()
	 */
	@Override
	public List<ComputerDTO> getAll() {
		List<Computer> computers = computerDAO.getAll();
		List<ComputerDTO> computersDTO = MapperDTO.listToDto(computers);
		return computersDTO;
	}
}
