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
import com.excilys.computerdatabase.service.CompanyBL;
import com.excilys.computerdatabase.service.ComputerBL;
import com.excilys.computerdatabase.service.dto.ComputerDTO;

/**
 * Servlet implementation class EditComputer
 */
@WebServlet("/EditComputer")
public class EditComputer extends HttpServlet {
	private static final String PARAM_COMPANY = "company";
	private static final String PARAM_DISCONTINUED = "discontinued";
	private static final String PARAM_INTRODUCED = "introduced";
	private static final String PARAM_NAME = "name";
	private static final String PARAM_ID = "id";
	private static final long serialVersionUID = 1L;
	ComputerBL blComputer;
	CompanyBL blCompany;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditComputer() {
        super();
		blComputer = new ComputerBL();
		blCompany = new CompanyBL();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerDTO computer = blComputer.get(Integer.parseInt(request.getParameter(PARAM_ID)));
		request.setAttribute("computer",computer);
		request.setAttribute("companies",blCompany.getAllCompany());
		getServletContext().getRequestDispatcher("/views/editComputer.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerDTO computer = blComputer.get(Integer.parseInt(request.getParameter(PARAM_ID)));
		blComputer.update(ValidatorComputerDTO.updateComputer(request.getParameter(PARAM_NAME), request.getParameter(PARAM_INTRODUCED), 
				request.getParameter(PARAM_DISCONTINUED), request.getParameter(PARAM_COMPANY), computer));
		getServletContext().getRequestDispatcher("/Dashboard").forward(request,response);
	}

}
