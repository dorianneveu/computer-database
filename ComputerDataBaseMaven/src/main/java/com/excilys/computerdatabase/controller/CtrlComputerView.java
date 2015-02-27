package com.excilys.computerdatabase.controller;

import java.util.List;

import com.excilys.computerdatabase.helper.CheckEntry;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ComputerDAO;
import com.excilys.computerdatabase.service.ComputerBL;
import com.excilys.computerdatabase.service.dto.ComputerDTO;

public class CtrlComputerView {
	private ComputerDAO computerDAO;
	private Computer computer;
	private ComputerBL bl;

	public CtrlComputerView() {
		this.computerDAO = new ComputerDAO();
		this.bl = new ComputerBL();
	}

	public Computer getComputerById(String str) {
		return computerDAO.get(Integer.parseInt(str));
	}

	public List<ComputerDTO> getAllComputer() {
		return bl.getAll();
	}
	
	public List<ComputerDTO> getAllLimitComputer(int limit, int offset) {
		return bl.getAllLimit(limit, offset);
	}
	
	
	public int getPage(long maxPage){
		return (int)(bl.getNumberPage(maxPage));
	}
	/**
	 * Verify all the param before the update of the computer. If a param is not good, set his old value
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param company
	 * @param computer
	 * @return int > 0 if the computer is updated
	 */
	public int updateComputer(String name, String introduced, String discontinued, String company, Computer computer) {
		this.computer = new Computer();
		if (CheckEntry.checkIsId(company)) {
			if (new Company(Integer.parseInt(company)).getId() != 0) {
				this.computer.setCompany(new Company(Integer.parseInt(company)));
			} else {
				this.computer.setCompany(computer.getCompany());
			}
		} else {
			this.computer.setCompany(computer.getCompany());
		}
		if (CheckEntry.checkIsDate(introduced)) {
			this.computer.setIntroduced(introduced);
		} else {
			if (computer.getIntroduced() != null) {
				this.computer.setIntroduced(computer.getIntroduced().substring(0,10));
			} else {
				this.computer.setIntroduced(null);
			}
		}
		if (CheckEntry.checkIsDate(discontinued)) {
			this.computer.setDiscontinued(discontinued);
		} else {
			if (computer.getDiscontinued() != null) {
				this.computer.setDiscontinued(computer.getDiscontinued().substring(0, 10));
			} else {
				this.computer.setDiscontinued(null);
			}
		}
		if (!name.trim().equals("")) {
			this.computer.setName(name.trim());
		} else {
			this.computer.setName(computer.getName());
		}
		this.computer.setId(computer.getId());
		return this.computerDAO.update(this.computer);
	}
	/**
	 * Verify all the param before the insert of the computer. If a param is not good, set his value to null
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param company
	 * @return Computer created
	 */
	public Computer insertComputer(String name, String introduced, String discontinued, String company) {
		ComputerDTO computerDTO = new ComputerDTO();
		if (CheckEntry.checkIsId(company)) {
			computerDTO.setCompanyId(Integer.parseInt(company));
		} else {
			computerDTO.setCompanyId(0);
		}
		if (CheckEntry.checkIsDate(introduced)) {
			computerDTO.setIntroduced(introduced);
		} else {
			computerDTO.setIntroduced(null);
		}
		if (CheckEntry.checkIsDate(discontinued)) {
			computerDTO.setDiscontinued(discontinued);
		} else {
			computerDTO.setDiscontinued(null);
		}
		computerDTO.setName(name.trim());
		return this.bl.insertComputer(computerDTO);
	}

	public int deleteComputer(String str) {
		return computerDAO.delete(computerDAO.get(Integer.parseInt(str)));
	}
	/**
	 * Check if the the computer exist.
	 * @param str
	 * @return boolean
	 */
	public boolean computerExist(String str) {
		Computer computer = computerDAO.get(Integer.parseInt(str));
		if (computer.getId() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<ComputerDTO> findByName(String name) {
		if (name.length() > 0) {
			return bl.findById(name);
		} 
		return bl.getAll();
	}
}
