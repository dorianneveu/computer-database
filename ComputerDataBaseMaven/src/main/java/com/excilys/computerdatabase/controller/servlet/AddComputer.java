package com.excilys.computerdatabase.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.controller.CtrlComputerView;
import com.excilys.computerdatabase.controller.CtrlMainView;
import com.excilys.computerdatabase.controller.validator.ValidatorComputerDTO;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.CompanyBL;
import com.excilys.computerdatabase.service.ComputerBL;

/**
 * Servlet implementation class AddComputer
 */
@WebServlet("/AddComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ComputerBL blComputer;
	CompanyBL blCompany;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputer() {
        super();
		blComputer = new ComputerBL();
		blCompany = new CompanyBL();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("companies",blCompany.getAllCompany());
		getServletContext().getRequestDispatcher("/views/addComputer.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		blComputer.insertComputer(ValidatorComputerDTO.insertComputer(request.getParameter("name"), request.getParameter("introduced"), 
				request.getParameter("discontinued"), request.getParameter("company")));
		getServletContext().getRequestDispatcher("/Dashboard").forward(request,response);
	}

}
