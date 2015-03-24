package com.excilys.computerdatabase.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdatabase.helper.Page;
import com.excilys.computerdatabase.service.ComputerBL;
import com.excilys.computerdatabase.dto.ComputerDTO;

@Controller
@RequestMapping("/dashboard")
public class CtrlDashboard {

	private static final String PARAM_PAGE = "page";
	private static final String PARAM_SELECTION = "selection";
	@Autowired
	private ComputerBL blComputer;
	
	@RequestMapping(method = RequestMethod.GET)
	public String allParameters(@Valid @ModelAttribute Page page, ModelMap model ) {
		model = workServlet(model, page);
		return "dashboard";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String delete(@Valid @ModelAttribute Page page, ModelMap model
			, @RequestParam(value=PARAM_SELECTION, required=false) final String pSelected) {
		if (pSelected != null) {
			if (pSelected.length() > 0) {
				String[] selected = pSelected.split(",");
				for (String str : selected) {
					blComputer.delete(blComputer.get(Integer.parseInt(str)));
				}
			}
		}
		model = workServlet(model, page);
		return "dashboard";
	}
	
	private ModelMap workServlet(ModelMap model, Page page) {
		List<ComputerDTO> computersDTO = blComputer.findByName(page);
		page.nbPage = (int) blComputer.getNumberPage(page.page);
		if (page.getSearch() != null) {
			page.nbPage = (int) blComputer.findByNameCount(page.search)/page.page;
		}
		model.addAttribute("nbFound", (int) blComputer.findByNameCount(page.search));
		model.addAttribute(PARAM_PAGE, page);
		model.addAttribute("computers", computersDTO);
		return model;
	}


}