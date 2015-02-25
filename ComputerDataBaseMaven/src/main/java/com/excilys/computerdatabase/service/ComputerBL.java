package com.excilys.computerdatabase.service;

import com.excilys.computerdatabase.persistence.ComputerDAO;

public class ComputerBL {

	public long getNumberPage(long maxPage) {
		long numberPage = 0;
		ComputerDAO dao = new ComputerDAO();
		numberPage = dao.getCount() / maxPage;
		return numberPage;
	}
}
