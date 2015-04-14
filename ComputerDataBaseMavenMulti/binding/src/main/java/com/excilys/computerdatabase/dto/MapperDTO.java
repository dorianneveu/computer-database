package com.excilys.computerdatabase.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.helper.DateConverter;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
@Component
public class MapperDTO {
	
	/**
	 * Convert a computer to it DTO
	 * @param computer
	 * @return ComputerDTO
	 */
	static public ComputerDTO computerToDTO(Computer computer) {
		ComputerDTO cDTO = new ComputerDTO();
		if (computer.getId() > 0) {
			cDTO.setId(computer.getId());
		}
		cDTO.setName(computer.getName());
		if (computer.getIntroduced() != null) {
			cDTO.setIntroduced(DateConverter.localDate((computer.getIntroduced().toString())));
		} else {
			cDTO.setIntroduced("");
		}
		if (computer.getDiscontinued() != null) {
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
	/**
	 * Convert a ComputerDTO to a Computer
	 * @param computerDTO
	 * @return Computer
	 */
	static public Computer dTOToComputer(ComputerDTO computerDTO)  {
		Locale userLocale = LocaleContextHolder.getLocale();
		DateTimeFormatter formatter;
		if(userLocale.getLanguage().equals(new Locale("en").getLanguage())) {
			 formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		} else {
			 formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		}
		Computer computer = new Computer();
		if (computerDTO.getId() > 0) {
			computer.setId(computerDTO.getId());
		}
		computer.setName(computerDTO.getName());
		if (computerDTO.getIntroduced() != null && !computerDTO.getIntroduced().equals("")) {
			LocalDate date = LocalDate.parse(computerDTO.getIntroduced(), formatter);
			computer.setIntroduced(date);
		} else {
			LocalDate d = null;
			computer.setIntroduced(d);
		}
		if (computerDTO.getDiscontinued() != null && !computerDTO.getDiscontinued().equals("")) {
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
	/**
	 * Convert a list of Computer to a list of his DTO
	 * @param computers
	 * @return List<ComputerDTO>
	 */
	static public List<ComputerDTO> listToDto(List<Computer> computers) {
		return computers.stream().map(c -> computerToDTO(c)).collect(Collectors.toList());
	}

	/**
	 * Convert a list of ComputerDTO to a list of Computer
	 * @param computerDTOs
	 * @return List<Computer>
	 */
	static public List<Computer> listFromDto(List<ComputerDTO> computerDTOs) {
		return computerDTOs.stream().map(c -> dTOToComputer(c)).collect(Collectors.toList());
	}

}
