package com.excilys.computerdatabase.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/addComputer")
public class CtrlAddComputer {
	private static final String PARAM_COMPANY = "company";
	private static final String PARAM_DISCONTINUED = "discontinued";
	private static final String PARAM_INTRODUCED = "introduced";
	private static final String PARAM_NAME = "name";
	@Autowired
	private ComputerBL blComputer;
	@Autowired
	private CompanyBL blCompany;
	
	@RequestMapping(method = RequestMethod.GET)
	public String goToAddComputer(ModelMap model) {
		model.addAttribute("companies", blCompany.getAll());
		model.addAttribute("computerDTO", new ComputerDTO());
		return "addComputer";
	}
	@RequestMapping(method = RequestMethod.POST)
	public String add(ModelMap model, @Valid ComputerDTO computerDTO, BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("computerDTO", computerDTO);
			return "addComputer";
		}
		blComputer.insert(computerDTO);
		return "forward:/dashboard";
	}

}
