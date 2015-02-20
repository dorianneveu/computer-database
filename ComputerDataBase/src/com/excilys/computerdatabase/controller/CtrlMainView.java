package com.excilys.computerdatabase.controller;

import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.CompanyDAO;
import com.excilys.computerdatabase.persistence.ComputerDAO;

public class CtrlMainView {

	private CompanyDAO companyDAO;
	private ComputerDAO computerDAO;
	private Computer computer;
	
	public CtrlMainView(){
		this.companyDAO = new CompanyDAO();
		this.computerDAO = new ComputerDAO();
	}
	
	public Computer getComputerById(String str){
		return computerDAO.get(Integer.parseInt(str));
	}
	
	public List<Computer> getAllCompany(){
		return computerDAO.getAll();
	}
	
	public int updateComputer(String name, String introduced, String discontinued, String company, Computer computer){
		this.computer = new Computer();
		if(checkIsId(company)){
			if(new Company(Integer.parseInt(company)).getId() != 0)
				this.computer.setCompany(new Company(Integer.parseInt(company)));
			else
				this.computer.setCompany(computer.getCompany());
		}
		else{
			this.computer.setCompany(computer.getCompany());
		}
		if(checkIsDate(introduced)){
			this.computer.setIntroduced(introduced);
		}else{
			this.computer.setIntroduced(computer.getIntroduced().substring(0, 10));
		}
		if(checkIsDate(discontinued)){
			this.computer.setDiscontinued(discontinued);
		}else{
			this.computer.setDiscontinued(computer.getDiscontinued().substring(0, 10));
		}if(!name.trim().equals("")){
			this.computer.setName(name.trim());
		}else{
			this.computer.setName(computer.getName());
		}
		this.computer.setId(computer.getId());
		return this.computerDAO.update(this.computer);
	}
	
	public Computer insertComputer(String name, String introduced, String discontinued, String company){
		Computer computer = new Computer();
		if(checkIsId(company)){
			computer.setCompany(new Company(Integer.parseInt(company)));
		}
		else{
			computer.setCompany(null);
		}
		if(checkIsDate(introduced)){
			computer.setIntroduced(introduced);
		}else{
			computer.setIntroduced(null);
		}
		if(checkIsDate(discontinued)){
			computer.setDiscontinued(discontinued);
		}else{
			computer.setDiscontinued(null);
		}
		computer.setName(name.trim());
		return this.computerDAO.create(computer);
	}
	
	public boolean checkIsId(String str){
		if(!str.equals("") && !str.equals("0") && str.matches("[0-9]+")){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean checkIsDate(String str){
		if (str.matches("\\d{4}-\\d{2}-\\d{2}")){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean computerExist(String str){
		Computer computer = computerDAO.get(Integer.parseInt(str));
		if(computer.getId()>0){
			return true;
		}else{
			return false;
		}
	}
}
