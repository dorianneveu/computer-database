package com.excilys.computerdatabase.service.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.CompanyDAO;
import com.excilys.computerdatabase.persistence.ICompanyDAO;
@Component
public class MapperDTO {
	
	@Autowired
	private static ICompanyDAO companyDAO;
	
	static public ComputerDTO computerToDTO(Computer computer) {
		ComputerDTO cDTO = new ComputerDTO();
		if (computer.getId() > 0) {
			cDTO.setId(computer.getId());
		}
		cDTO.setName(computer.getName());
		if (computer.getIntroduced() != null) {
			cDTO.setIntroduced(computer.getIntroduced().substring(0,10));
		} else {
			cDTO.setIntroduced("");
		}
		if (computer.getDiscontinued() != null) {
			cDTO.setDiscontinued(computer.getDiscontinued().substring(0,10));
		} else {
			cDTO.setDiscontinued("");
		}
		if (computer.getCompany().getId() > 0) {
			cDTO.setCompanyId(computer.getCompany().getId());
			cDTO.setCompanyName(computer.getCompany().getName());
		} else {
			cDTO.setCompanyId(0);
			cDTO.setCompanyName("");
		}
		return cDTO;
	}
	
	static public Computer dTOToComputer(ComputerDTO computerDTO)  {
		Computer computer = new Computer();
//		ICompanyDAO companyDAO = new CompanyDAO();
		if (computerDTO.getId() > 0) {
			computer.setId(computerDTO.getId());
		}
		computer.setName(computerDTO.getName());
		if (computerDTO.getIntroduced() != null) {
			computer.setIntroduced(computerDTO.getIntroduced().substring(0,10));
		} else {
			computer.setIntroduced(null);
		}
		if (computerDTO.getDiscontinued() != null) {
			computer.setDiscontinued(computerDTO.getDiscontinued().substring(0,10));
		} else {
			computer.setDiscontinued(null);
		}
		if (computerDTO.getCompanyId() > 0) {
			computer.setCompany(new Company(computerDTO.getCompanyId(), computerDTO.getCompanyName()));
		} else {
			computer.setCompany(null);
		}
		return computer;
	}

}
