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

	private static final String PARAM_SEARCH = "search";
	private static final String PARAM_ORDER = "order";
	private static final String PARAM_SORT = "sort";
	private static final String PARAM_OFFSET = "offset";
	private static final String PARAM_PAGE = "page";
	private static final String PARAM_SELECTION = "selection";
	@Autowired
	private ComputerBL blComputer;
	
	@RequestMapping(method = RequestMethod.GET)
	public String allParameters(@Valid @ModelAttribute Page page, ModelMap model
			, @RequestParam(value=PARAM_PAGE, required=false) String pPage, @RequestParam(value=PARAM_OFFSET, required=false) final String pOffset
			, @RequestParam(value=PARAM_SORT, required=false) final String pSort, @RequestParam(value=PARAM_ORDER, required=false) final String pOrder
			, @RequestParam(value=PARAM_SEARCH, required=false) final String pSearch
			) {
		System.out.println(page);
		model = workServlet(model, pPage, pOffset, pSort, pOrder, pSearch);
		return "dashboard";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String delete(ModelMap model, @RequestParam(value=PARAM_PAGE, required=false) String pPage, @RequestParam(value=PARAM_OFFSET, required=false) final String pOffset
			, @RequestParam(value=PARAM_SORT, required=false) final String pSort, @RequestParam(value=PARAM_ORDER, required=false) final String pOrder
			, @RequestParam(value=PARAM_SEARCH, required=false) final String pSearch, @RequestParam(value=PARAM_SELECTION, required=false) final String pSelected) {
		if (pSelected != null) {
			if (pSelected.length() > 0) {
				String[] selected = pSelected.split(",");
				for (String str : selected) {
					blComputer.delete(blComputer.get(Integer.parseInt(str)));
				}
			}
		}
		model = workServlet(model, pPage, pOffset, pSort, pOrder, pSearch);
		return "dashboard";
	}
	
	private ModelMap workServlet(ModelMap model, String pPage, String pOffset, String pSort, String pOrder, String pSearch) {
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		Page page = new Page();
		if (pPage != null) {
			page.limit = Integer.parseInt(pPage);
			page.nbPage = (int) blComputer.getNumberPage(page.limit);
		} else {
			page.limit = 50;
			page.nbPage = (int) blComputer.getNumberPage(page.limit);
		}
		if (pOffset != null) {
			page.offset = Integer.parseInt(pOffset);
		}
		if (pSort != null) {
			page.sort = pSort;
		}
		if (pOrder != null) {
			page.order = pOrder;
		}
		if (pSearch != null) {
			page.search = pSearch;
			page.nbPage = (int) blComputer.findByNameCount(page.search)/page.limit;
		}
		computersDTO = blComputer.findByName(page);
		model.addAttribute("nbFound", (int) blComputer.findByNameCount(page.search));
		model.addAttribute(PARAM_PAGE, page);
		model.addAttribute("computers", computersDTO);
		return model;
	}


}