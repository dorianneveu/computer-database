package com.excilys.computerdatabase.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.controller.CtrlComputerView;
import com.excilys.computerdatabase.model.Computer;

/**
 * Servlet implementation class DashBoard
 */
//@WebServlet("/views/dashboard.jsp")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nbPage = 0;
		CtrlComputerView ctrl = new CtrlComputerView();
		List<Computer> computers = new ArrayList<Computer>();
		
		if (request.getParameter("page") != null && !request.getParameter("page").equals("ALL")) {
			nbPage = ctrl.getPage(Long.parseLong(request.getParameter("page")));
			int page = Integer.parseInt(request.getParameter("page"));
			if(request.getParameter("offset") != null) {
				int offset = Integer.parseInt(request.getParameter("offset"));
				if(offset > 0 && offset <= nbPage) {
					request.setAttribute("offset",offset);
					computers = ctrl.getAllLimitComputer(page, (page*offset));
				} else {
					computers = ctrl.getAllLimitComputer(page, 0);
					request.setAttribute("offset",0);
				}
			} else {
				computers = ctrl.getAllLimitComputer(page, 0);
				request.setAttribute("offset",0);
			}
		} else {
			computers = ctrl.getAllComputer();
		}
		request.setAttribute("computers",computers);
		request.setAttribute("page",nbPage);
		request.setAttribute("nbeachpage", request.getParameter("page"));
		getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CtrlComputerView ctrl = new CtrlComputerView();
		List<Computer> computers = new ArrayList<Computer>();
		if (request.getParameter("selection") != null) {
			if (request.getParameter("selection").length() > 0) {
				String[] selected = request.getParameter("selection").split(",");
				for (String str : selected) {
					ctrl.deleteComputer(str);
				}
			}
		}
		if (request.getParameter("search") != null) {
			if (!request.getParameter("search").trim().equals("") && request.getParameter("search").trim().length()>0) {
				computers = ctrl.findByName(request.getParameter("search"));
			} else {
				computers = ctrl.getAllComputer();
			}
		} else {
			computers = ctrl.getAllComputer();
		}
		request.setAttribute("computers",computers);
		request.setAttribute("page",0);
		getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request,response);
	}

}
