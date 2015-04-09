package com.excilys.computerdatabase.clictrl;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.helper.CheckEntry;
import com.excilys.computerdatabase.service.IComputerBL;
import com.excilys.computerdatabase.dto.ComputerDTO;

@Component
public class CtrlComputerView {
	@Autowired
	private IComputerBL bl;
	ComputerDTO computerDTO;

	Client client;
	WebTarget computerTarget;

	public CtrlComputerView() {
		client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
		computerTarget = client.target("http://localhost:8080/webservice/rest/computer");
	}

	public ComputerDTO getComputerById(String str) {
		ComputerDTO computer = computerTarget.path("/"+str).request(MediaType.APPLICATION_JSON).get(new GenericType<ComputerDTO>() {});
		return computer;
	}

	public List<ComputerDTO> getAllComputer() {
		List<ComputerDTO> computersDTO = computerTarget.path("/all").request(MediaType.APPLICATION_JSON).get(new GenericType<List<ComputerDTO>>() {});

		return computersDTO;
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
		Response response = computerTarget.path("/"+ computerDTO.getId()).request(MediaType.APPLICATION_JSON).post(Entity.entity(computerDTO, MediaType.APPLICATION_JSON));
		if(response.getStatus() != 200 && response.getStatus() != 204 ) {
			System.out.println("Error http " + response.getStatus());
		}
	}
	/**
	 * Verify all the param before the insert of the computer. If a param is not good, set his value to null
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param company
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
		Response response = computerTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(computerDTO, MediaType.APPLICATION_JSON));
		if(response.getStatus() != 200 && response.getStatus() != 204 ) {
			System.out.println("Error http " + response.getStatus());
		}
	}

	public void deleteComputer(String str) {
		Response response = computerTarget.path("/"+ Integer.parseInt(str)).request(MediaType.APPLICATION_JSON).delete();
		if(response.getStatus() != 200 && response.getStatus() != 204 ) {
			System.out.println("Error http " + response.getStatus());
		}
	}
	/**
	 * Check if the the computer exist.
	 * @param str
	 * @return boolean
	 */
	public boolean computerExist(String str) {
		ComputerDTO computer = new ComputerDTO();
		try {
			computer = bl.get(Integer.parseInt(str));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if (computer.getId() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
