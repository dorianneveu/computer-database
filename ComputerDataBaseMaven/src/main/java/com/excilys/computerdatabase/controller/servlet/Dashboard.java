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
		
		if (request.getParameter("page") != null && !request.getParameter("page").equals("ALL") && !Page.isSearch()) {
			Page.INSTANCE.nbPage = (int) blComputer.getNumberPage(Long.parseLong(request.getParameter("page")));
			if (request.getParameter("page").contains("-")) {
				Page.INSTANCE.nbPage = 10;
				Page.INSTANCE.limit = 10;
			} else {
				Page.INSTANCE.limit = Integer.parseInt(request.getParameter("page"));
			}
			if(request.getParameter("offset") != null) {
				Page.INSTANCE.offset = Integer.parseInt(request.getParameter("offset"));
				if(Page.INSTANCE.offset > 0 && Page.INSTANCE.offset <= Page.INSTANCE.nbPage) {
					request.setAttribute("offset",Page.INSTANCE.offset);
				} else {
					Page.INSTANCE.offset = 0;
					request.setAttribute("offset",0);
				}
			} else {
				Page.INSTANCE.offset = 0;
				request.setAttribute("offset",0);
			}
			request.setAttribute("nbeachpage", request.getParameter("page"));
		} else {
		}
		
		if (!Page.isEmpty() && !request.getParameter("page").equals("ALL")) {
			if(request.getParameter("offset") != null) {
				Page.INSTANCE.offset = Integer.parseInt(request.getParameter("offset"));
			}
			if (request.getParameter("page") != null) {
				if(Integer.parseInt(request.getParameter("page")) != Page.INSTANCE.limit) {
					Page.INSTANCE.offset = 0;
					Page.INSTANCE.limit = Integer.parseInt(request.getParameter("page"));
				}
				Page.INSTANCE.nbPage =  blComputer.findByNameCount(Page.INSTANCE.search)/Page.INSTANCE.limit;
				if (request.getParameter("page").contains("-")) {
					Page.INSTANCE.nbPage = 10;
					Page.INSTANCE.limit = 10;
				} else {
					Page.INSTANCE.limit = Integer.parseInt(request.getParameter("page"));
				}
			}
			if (Page.isSearch()) {
				computersDTO = blComputer.findByName(Page.INSTANCE.search, Page.INSTANCE.limit, Page.INSTANCE.offset * Page.INSTANCE.limit);
			} else {
				computersDTO = blComputer.getAllLimit(Page.INSTANCE.limit, Page.INSTANCE.offset * Page.INSTANCE.limit, Page.INSTANCE.order, Page.INSTANCE.sort);
			}
		} else {
			Page.reset();
			computersDTO = blComputer.getAll();
		}
		request.setAttribute("isPaginated", Page.isEmpty());
		request.setAttribute("cPage", Page.INSTANCE);
		request.setAttribute("page",Page.INSTANCE.nbPage);
		request.setAttribute("computers",computersDTO);
		getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		if (request.getParameter("selection") != null) {
			if (request.getParameter("selection").length() > 0) {
				String[] selected = request.getParameter("selection").split(",");
				for (String str : selected) {
					blComputer.deleteComputer(str);
				}
			}
		}
		if (request.getParameter("search") != null) {
			if (!request.getParameter("search").trim().equals("") && request.getParameter("search").trim().length()>0) {
				Page.INSTANCE.offset = 0;
				if (Page.isEmpty()) {
					Page.INSTANCE.limit = 100;
				}
				computersDTO = blComputer.findByName(request.getParameter("search"), Page.INSTANCE.limit, Page.INSTANCE.offset * Page.INSTANCE.limit);
				Page.INSTANCE.nbPage = blComputer.findByNameCount(request.getParameter("search"))/Page.INSTANCE.limit;
				Page.INSTANCE.search = request.getParameter("search");
			} else {
				computersDTO = blComputer.getAll();
			}
		} else {
			computersDTO = blComputer.getAll();
		}
		request.setAttribute("computers",computersDTO);
		request.setAttribute("page",0);
		getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request,response);
	}

}
