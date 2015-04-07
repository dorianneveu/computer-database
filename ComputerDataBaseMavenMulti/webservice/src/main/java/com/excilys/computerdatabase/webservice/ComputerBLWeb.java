package com.excilys.computerdatabase.webservice;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.IComputerDAO;
import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.MapperDTO;

@Path("/computer")
public class ComputerBLWeb implements IComputerBLWeb {
	@Autowired
	private IComputerDAO computerDAO;
	public ComputerBLWeb() {
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.webservice.IComputerBL#update(com.excilys.computerdatabase.dto.ComputerDTO)
	 */
	@Override
	@POST
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(ComputerDTO object) throws SQLException {
		Computer computer = MapperDTO.dTOToComputer(object);
		computerDAO.update(computer);
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.webservice.IComputerBL#delete(com.excilys.computerdatabase.dto.ComputerDTO)
	 */
	@Override
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") int id) throws SQLException {
		computerDAO.delete(computerDAO.get(id));
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.webservice.IComputerBL#insert(com.excilys.computerdatabase.dto.ComputerDTO)
	 */
	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void insert(ComputerDTO object) throws SQLException {
		Computer computer = MapperDTO.dTOToComputer(object);
		computerDAO.create(computer);
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.webservice.IComputerBL#get(int)
	 */
	@Override
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public ComputerDTO get(@PathParam("param") int id) throws SQLException {
		Computer computer = computerDAO.get(id);
		ComputerDTO computerDTO = MapperDTO.computerToDTO(computer);	
		return computerDTO;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.webservice.IComputerBL#getAll()
	 */
	@Override
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComputerDTO> getAll() throws SQLException {
		List<Computer> computers = computerDAO.getAll();
		List<ComputerDTO> computersDTO = MapperDTO.listToDto(computers);
		return computersDTO;
	}
}
