package com.excilys.computerdatabase.service.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.helper.DateConverter;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
@Component
public class MapperDTO {
	
	
	static public ComputerDTO computerToDTO(Computer computer) {
		ComputerDTO cDTO = new ComputerDTO();
		if (computer.getId() > 0) {
			cDTO.setId(computer.getId());
		}
		cDTO.setName(computer.getName());
		if (computer.getIntroduced() != null) {
//			cDTO.setIntroduced(DateConverter.localDate((computer.getIntroduced().substring(0,10))));
			cDTO.setIntroduced(DateConverter.localDate((computer.getIntroduced().toString())));
		} else {
			cDTO.setIntroduced("");
		}
		if (computer.getDiscontinued() != null) {
//			cDTO.setDiscontinued(DateConverter.localDate((computer.getDiscontinued().substring(0,10))));
			cDTO.setDiscontinued(DateConverter.localDate((computer.getDiscontinued().toString())));
		} else {
			cDTO.setDiscontinued("");
		}
		if(computer.getCompany() != null ) {
			if (computer.getCompany().getId() > 0) {
				cDTO.setCompanyId(computer.getCompany().getId());
				cDTO.setCompanyName(computer.getCompany().getName());
			} else {
				cDTO.setCompanyId(0);
				cDTO.setCompanyName("");
			}
		}
		return cDTO;
	}
	
	static public Computer dTOToComputer(ComputerDTO computerDTO)  {
		Computer computer = new Computer();
		if (computerDTO.getId() > 0) {
			computer.setId(computerDTO.getId());
		}
		computer.setName(computerDTO.getName());
		if (computerDTO.getIntroduced() != null && !computerDTO.getIntroduced().equals("")) {
//			computer.setIntroduced(computerDTO.getIntroduced().substring(0,10));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(computerDTO.getIntroduced(), formatter);
			computer.setIntroduced(date);
		} else {
			LocalDate d = null;
			computer.setIntroduced(d);
		}
		if (computerDTO.getDiscontinued() != null && !computerDTO.getDiscontinued().equals("")) {
//			computer.setDiscontinued(computerDTO.getDiscontinued().substring(0,10));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(computerDTO.getDiscontinued(), formatter);
			computer.setDiscontinued(date);
		} else {
			LocalDate d = null;
			computer.setDiscontinued(d);
		}
		if (computerDTO.getCompanyId() > 0) {
			computer.setCompany(new Company(computerDTO.getCompanyId(), computerDTO.getCompanyName()));
		} else {
			computer.setCompany(null);
		}
		return computer;
	}

}
