package com.excilys.computerdatabase.webservice;

import java.util.List;

import com.excilys.computerdatabase.dto.ComputerDTO;

public interface IComputerBLWeb {
	/**
	 * Update in the database the computer set in parameter 
	 * @param object the computer to update
	 */
	public abstract void update(ComputerDTO object);
	/**
	 * Delete in the database the Computer wich correspond to the id set
	 * @param id the id of the computer to delete
	 */
	public abstract void delete(int id);
	/**
	 * Insert in the database the computer set in parameter 
	 * @param object the computer to insert
	 */
	public abstract void insert(ComputerDTO object);
	/**
	 * Return the ComputerDTO wich correspond to the id set
	 * @param id the id of the computer to get
	 * @return 
	 */
	public abstract ComputerDTO get(int id);
	/**
	 * Return the list of all the Computer in the database
	 * @return 
	 */
	public abstract List<ComputerDTO> getAll();

}