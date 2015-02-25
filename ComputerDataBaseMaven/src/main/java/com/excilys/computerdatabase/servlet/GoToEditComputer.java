package com.excilys.computerdatabase.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.controller.CtrlComputerView;
import com.excilys.computerdatabase.controller.CtrlMainView;

/**
 * Servlet implementation class GoToEditComputer
 */
public class GoToEditComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoToEditComputer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CtrlComputerView ctrl = new CtrlComputerView();
		Computer computer = ctrl.getComputerById(request.getParameter("id"));
		request.setAttribute("computer",computer);
		CtrlMainView ctrlCompany = new CtrlMainView();
		request.setAttribute("companies",ctrlCompany.getAllCompany());
		getServletContext().getRequestDispatcher("/views/editComputer.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
