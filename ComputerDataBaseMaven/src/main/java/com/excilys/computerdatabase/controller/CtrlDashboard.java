package com.excilys.computerdatabase.controller;

import java.util.ArrayList;
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
import com.excilys.computerdatabase.service.dto.ComputerDTO;

@Controller
@RequestMapping("/dashboard")
public class CtrlDashboard {

	private static final String PARAM_PAGE = "page";
	private static final String PARAM_SELECTION = "selection";
	@Autowired
	private ComputerBL blComputer;
	
	@RequestMapping(method = RequestMethod.GET)
	public String allParameters(@Valid @ModelAttribute Page page, ModelMap model
			) {
		System.out.println(page.getPage());
		model = workServlet(model, page.getPage(), page.getOffset(), page.getSort(), page.getOrder(), page.getSearch());
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
		model = workServlet(model, page.getPage(), page.getOffset(), page.getSort(), page.getOrder(), page.getSearch());
		return "dashboard";
	}
	
	private ModelMap workServlet(ModelMap model, int pPage, int pOffset, String pSort, String pOrder, String pSearch) {
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		Page page = new Page();
		if (pPage != 0) {
			page.page = pPage;
			page.nbPage = (int) blComputer.getNumberPage(page.page);
		} else {
			page.page = 50;
			page.nbPage = (int) blComputer.getNumberPage(page.page);
		}
		if (pOffset != 0) {
			page.offset = pOffset;
		}
		if (pSort != null) {
			page.sort = pSort;
		}
		if (pOrder != null) {
			page.order = pOrder;
		}
		if (pSearch != null) {
			page.search = pSearch;
			page.nbPage = (int) blComputer.findByNameCount(page.search)/page.page;
		}
		computersDTO = blComputer.findByName(page);
		model.addAttribute("nbFound", (int) blComputer.findByNameCount(page.search));
		model.addAttribute(PARAM_PAGE, page);
		model.addAttribute("computers", computersDTO);
		return model;
	}


}