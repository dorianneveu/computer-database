package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.helper.Page;

public interface IComputerBL {

	/**
	 * Return the number of page related to the number of elements per page
	 * @param maxPage : number of elements per page
	 * @return
	 */
	public abstract long getNumberPage(long maxPage);
	/**
	 * Return the number of computer found
	 * @param name the name of the computer
	 * @return
	 */
	public abstract int findByNameCount(String name);
	
	public abstract List<ComputerDTO> findByName(Page page);

	public abstract void update(ComputerDTO object);

	public abstract void delete(ComputerDTO object);

	public abstract void insert(ComputerDTO object);

	public abstract ComputerDTO get(int id);

	public abstract List<ComputerDTO> getAll();

}