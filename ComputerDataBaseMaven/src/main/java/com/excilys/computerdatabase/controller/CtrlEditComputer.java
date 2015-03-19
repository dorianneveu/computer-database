package com.excilys.computerdatabase.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdatabase.controller.validator.ValidatorComputerDTO;
import com.excilys.computerdatabase.helper.Page;
import com.excilys.computerdatabase.service.CompanyBL;
import com.excilys.computerdatabase.service.ComputerBL;
import com.excilys.computerdatabase.service.dto.ComputerDTO;

@Controller
@RequestMapping("/editComputer")
public class CtrlEditComputer {
	private static final String PARAM_COMPANY = "company";
	private static final String PARAM_DISCONTINUED = "discontinued";
	private static final String PARAM_INTRODUCED = "introduced";
	private static final String PARAM_NAME = "name";
	private static final String PARAM_ID = "id";
	@Autowired
	private ComputerBL blComputer;
	@Autowired
	private CompanyBL blCompany;
	
	@RequestMapping(method = RequestMethod.GET)
	public String goToEditComputer(ModelMap model, @RequestParam(value=PARAM_ID, required=false) final String pId) {
		ComputerDTO computer = blComputer.get(Integer.parseInt(pId));
		model.addAttribute("computer",computer);
		model.addAttribute("companies", blCompany.getAll());
		return "editComputer";
	}
	@RequestMapping(method = RequestMethod.POST)
	public String edit(ModelMap model, 
			@Valid @ModelAttribute ComputerDTO computerDto
//			,@RequestParam(value=PARAM_COMPANY, required=false) String pCompany, @RequestParam(value=PARAM_DISCONTINUED, required=false) final String pDiscontinued
//			, @RequestParam(value=PARAM_INTRODUCED, required=false) final String pIntroduced, @RequestParam(value=PARAM_NAME, required=false) final String pName,
//			,@RequestParam(value=PARAM_ID, required=false) final String pId
			) {
//		ComputerDTO computer = blComputer.get(Integer.parseInt(pId));
//		blComputer.update(ValidatorComputerDTO.updateComputer(pName, pIntroduced, pDiscontinued, pCompany, computer)); 
		blComputer.update(computerDto); 
		Page page = new Page();
		page.nbPage = (int) blComputer.getNumberPage(page.limit);
		model.addAttribute("nbFound", (int) blComputer.findByNameCount(page.search));
		model.addAttribute("page", page);
		model.addAttribute("computers", blComputer.findByName(page));
		return "dashboard";
	}

}
