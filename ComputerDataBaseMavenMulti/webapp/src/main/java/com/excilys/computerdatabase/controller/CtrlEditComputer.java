package com.excilys.computerdatabase.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdatabase.helper.Page;
import com.excilys.computerdatabase.service.CompanyBL;
import com.excilys.computerdatabase.service.IComputerBL;
import com.excilys.computerdatabase.dto.ComputerDTO;

@Controller
@RequestMapping("/editComputer")
public class CtrlEditComputer {
	private static final String PARAM_ID = "id";
	@Autowired
	private IComputerBL blComputer;
	@Autowired
	private CompanyBL blCompany;
	
	@RequestMapping(method = RequestMethod.GET)
	public String goToEditComputer(ModelMap model, @RequestParam(value=PARAM_ID, required=false) final String pId) {
		ComputerDTO computerDTO = blComputer.get(Integer.parseInt(pId));
		model.addAttribute("computerDTO",computerDTO);
		model.addAttribute("companies", blCompany.getAll());
		return "editComputer";
	}
	@RequestMapping(method = RequestMethod.POST)
	public String edit(ModelMap model, 
			@Valid ComputerDTO computerDTO, BindingResult result
			) {
		if(result.hasErrors()) {
			model.addAttribute("companies", blCompany.getAll());
			model.addAttribute("computerDTO", computerDTO);
			return "editComputer";
		}
		
		blComputer.update(computerDTO); 
		Page page = new Page();
		page.nbPage = (int) blComputer.getNumberPage(page.page);
		model.addAttribute("nbFound", (int) blComputer.findByNameCount(page.search));
		model.addAttribute("page", page);
		model.addAttribute("computers", blComputer.findByName(page));
		return "redirect:/dashboard";
	}

}
