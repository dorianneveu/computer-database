package com.excilys.computerdatabase.controller;

import java.util.List;

import com.excilys.computerdatabase.helper.CheckEntry;
import com.excilys.computerdatabase.service.ComputerBL;
import com.excilys.computerdatabase.service.dto.ComputerDTO;

public class CtrlComputerView {
	private ComputerBL bl;

	public CtrlComputerView() {
		this.bl = new ComputerBL();
	}

	public ComputerDTO getComputerById(String str) {
		return bl.get(Integer.parseInt(str));
	}

	public List<ComputerDTO> getAllComputer() {
		return bl.getAll();
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
	public void updateComputer(String name, String introduced, String discontinued, String company, ComputerDTO computer) {
		ComputerDTO computerDTO = new ComputerDTO();
		if (CheckEntry.checkIsId(company)) {
			if (Integer.parseInt(company) != 0) {
				computerDTO.setCompanyId(Integer.parseInt(company));
			} else {
				computerDTO.setCompanyId(computer.getCompanyId());
			}
		} else {
			computerDTO.setCompanyId(computer.getCompanyId());
		}
		if (CheckEntry.checkIsDate(introduced)) {
			computerDTO.setIntroduced(introduced);
		} else {
			if (computer.getIntroduced() != null) {
				if (!computer.getIntroduced().equals("")) {
					computerDTO.setIntroduced(computer.getIntroduced().substring(0,10));
				} else {
					computerDTO.setIntroduced(null);
				}
			} else {
				computerDTO.setIntroduced(null);
			}
		}
		if (CheckEntry.checkIsDate(discontinued)) {
			computerDTO.setDiscontinued(discontinued);
		} else {
			if (computer.getDiscontinued() != null) {
				if (!computer.getDiscontinued().equals("")) {
					computerDTO.setDiscontinued(computer.getDiscontinued().substring(0, 10));
				} else {
					computerDTO.setIntroduced(null);
				}
			} else {
				computerDTO.setDiscontinued(null);
			}
		}
		if (!name.trim().equals("")) {
			computerDTO.setName(name.trim());
		} else {
			computerDTO.setName(computer.getName());
		}
		computerDTO.setId(computer.getId());
		bl.update(computerDTO);
	}
	/**
	 * Verify all the param before the insert of the computer. If a param is not good, set his value to null
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param company
	 * @return Computer created
	 */
	public void insertComputer(String name, String introduced, String discontinued, String company) {
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
		bl.insert(computerDTO);
	}

	public void deleteComputer(String str) {
		bl.delete(bl.get(Integer.parseInt(str)));
	}
	/**
	 * Check if the the computer exist.
	 * @param str
	 * @return boolean
	 */
	public boolean computerExist(String str) {
		ComputerDTO computer = bl.get(Integer.parseInt(str));
		if (computer.getId() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
