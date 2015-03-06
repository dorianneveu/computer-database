package com.excilys.computerdatabase.controller.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.helper.Page;
import com.excilys.computerdatabase.persistence.ConnectionDAO;
import com.excilys.computerdatabase.service.ComputerBL;
import com.excilys.computerdatabase.service.dto.ComputerDTO;

/**
 * Servlet implementation class DashBoard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private static final String PARAM_SEARCH = "search";
	private static final String PARAM_ORDER = "order";
	private static final String PARAM_SORT = "sort";
	private static final String PARAM_OFFSET = "offset";
	private static final String PARAM_PAGE = "page";
	private static final String PARAM_SELECTION = "selection";
	private static final long serialVersionUID = 1L;
	private ComputerBL blComputer;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
        super();
		blComputer = new ComputerBL();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		workServlet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter(PARAM_SELECTION) != null) {
			if (request.getParameter(PARAM_SELECTION).length() > 0) {
				String[] selected = request.getParameter(PARAM_SELECTION).split(",");
				for (String str : selected) {
					blComputer.delete(blComputer.get(Integer.parseInt(str)));
				}
			}
		}
		workServlet(request, response);
	}
	
	private void workServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		
		Page page = new Page();
		if (request.getParameter(PARAM_PAGE) != null) {
			page.limit = Integer.parseInt(request.getParameter(PARAM_PAGE));
			page.nbPage = (int) blComputer.getNumberPage(page.limit);
		} else {
			page.limit = 50;
			page.nbPage = (int) blComputer.getNumberPage(page.limit);
		}
		if (request.getParameter(PARAM_OFFSET) != null) {
			page.offset = Integer.parseInt(request.getParameter(PARAM_OFFSET));
		}
		if (request.getParameter(PARAM_SORT) != null) {
			page.sort = request.getParameter(PARAM_SORT);
		}
		if (request.getParameter(PARAM_ORDER) != null) {
			page.order = request.getParameter(PARAM_ORDER);
		}
		if (request.getParameter(PARAM_SEARCH) != null) {
			page.search = request.getParameter(PARAM_SEARCH);
			page.nbPage = (int) blComputer.findByNameCount(page.search)/page.limit;
		}
		computersDTO = blComputer.findByName(page);

		request.setAttribute("nbFound", (int) blComputer.findByNameCount(page.search));
		request.setAttribute(PARAM_PAGE, page);
		request.setAttribute("computers", computersDTO);
		getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request,response);
	}

}
