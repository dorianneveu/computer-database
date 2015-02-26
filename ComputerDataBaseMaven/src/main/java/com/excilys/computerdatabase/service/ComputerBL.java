package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ComputerDAO;

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
	
	public List<Computer> findById(String name) {
		return dao.findByName(name);
	}
	
	public List<Computer> getAll() {
		return dao.getAll();
	}
}
