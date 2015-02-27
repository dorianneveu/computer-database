package com.excilys.computerdatabase.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.controller.CtrlComputerView;
import com.excilys.computerdatabase.model.Computer;

/**
 * Servlet implementation class AddComputer
 */
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Computer computer = new Computer();
		CtrlComputerView ctrl = new CtrlComputerView();
//		computer = ctrl.insertComputer(request.getParameter("name"), request.getParameter("introduced"), 
//				request.getParameter("discontinued"), request.getParameter("company"));
		ctrl.insertComputer(request.getParameter("name"), request.getParameter("introduced"), 
				request.getParameter("discontinued"), request.getParameter("company"));
//		request.setAttribute("resultat",computer);
		getServletContext().getRequestDispatcher("/Dashboard").forward(request,response);
	}

}
