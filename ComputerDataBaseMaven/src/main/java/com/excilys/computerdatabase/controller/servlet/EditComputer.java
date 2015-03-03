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
		ComputerDTO computer = blComputer.get(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("computer",computer);
		request.setAttribute("companies",blCompany.getAllCompany());
		getServletContext().getRequestDispatcher("/views/editComputer.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerDTO computer = blComputer.get(Integer.parseInt(request.getParameter("id")));
		blComputer.update(ValidatorComputerDTO.updateComputer(request.getParameter("name"), request.getParameter("introduced"), 
				request.getParameter("discontinued"), request.getParameter("company"), computer));
		getServletContext().getRequestDispatcher("/Dashboard").forward(request,response);
	}

}
