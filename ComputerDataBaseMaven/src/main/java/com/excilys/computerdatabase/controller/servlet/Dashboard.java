package com.excilys.computerdatabase.controller.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.helper.Page;
import com.excilys.computerdatabase.service.ComputerBL;
import com.excilys.computerdatabase.service.dto.ComputerDTO;

/**
 * Servlet implementation class DashBoard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
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
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		
		Page page = new Page();
		if (request.getParameter("page") != null) {
			page.limit = Integer.parseInt(request.getParameter("page"));
			page.nbPage = (int) blComputer.getNumberPage(page.limit);
		} else {
			page.limit = 50;
			page.nbPage = (int) blComputer.getNumberPage(page.limit);
		}
		if (request.getParameter("offset") != null) {
			page.offset = Integer.parseInt(request.getParameter("offset"));
		}
		if (request.getParameter("sort") != null) {
			page.sort = request.getParameter("sort");
		}
		if (request.getParameter("order") != null) {
			page.order = request.getParameter("order");
		}
		if (request.getParameter("search") != null) {
			page.search = request.getParameter("search");
			page.nbPage = (int) blComputer.findByNameCount(page.search)/page.limit;
		}
		
		computersDTO = blComputer.findByName(page.search, page.limit, page.offset * page.limit, page.order, page.sort);

		request.setAttribute("nbFound", (int) blComputer.findByNameCount(page.search));
		request.setAttribute("page", page);
		request.setAttribute("computers", computersDTO);
		getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
//		if (request.getParameter("selection") != null) {
//			if (request.getParameter("selection").length() > 0) {
//				String[] selected = request.getParameter("selection").split(",");
//				for (String str : selected) {
//					blComputer.deleteComputer(str);
//				}
//			}
//		}
//		if (request.getParameter("search") != null) {
//			if (!request.getParameter("search").trim().equals("") && request.getParameter("search").trim().length()>0) {
//				Page.INSTANCE.offset = 0;
//				if (Page.isEmpty()) {
//					Page.INSTANCE.limit = 100;
//				}
//				computersDTO = blComputer.findByName(request.getParameter("search"), Page.INSTANCE.limit, Page.INSTANCE.offset * Page.INSTANCE.limit, Page.INSTANCE.order, Page.INSTANCE.sort);
//				Page.INSTANCE.nbPage = blComputer.findByNameCount(request.getParameter("search"))/Page.INSTANCE.limit;
//				Page.INSTANCE.search = request.getParameter("search");
//			} else {
//				computersDTO = blComputer.getAll();
//			}
//		} else {
//			computersDTO = blComputer.getAll();
//		}
//		request.setAttribute("computers",computersDTO);
//		request.setAttribute("page",0);
		getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request,response);
	}

}
