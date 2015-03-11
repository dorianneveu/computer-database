package com.excilys.computerdatabase.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.computerdatabase.controller.validator.ValidatorComputerDTO;
import com.excilys.computerdatabase.service.CompanyBL;
import com.excilys.computerdatabase.service.ComputerBL;

/**
 * Servlet implementation class AddComputer
 */
@WebServlet("/AddComputer")
public class AddComputer extends HttpServlet {
	private static final String PARAM_COMPANY = "company";
	private static final String PARAM_DISCONTINUED = "discontinued";
	private static final String PARAM_INTRODUCED = "introduced";
	private static final String PARAM_NAME = "name";
	private static final long serialVersionUID = 1L;
	@Autowired
	private ComputerBL blComputer;
	@Autowired
	private CompanyBL blCompany;
       
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
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
		request.setAttribute("companies",blCompany.getAll());
		getServletContext().getRequestDispatcher("/views/addComputer.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		blComputer.insert(ValidatorComputerDTO.insertComputer(request.getParameter(PARAM_NAME), request.getParameter(PARAM_INTRODUCED), 
				request.getParameter(PARAM_DISCONTINUED), request.getParameter(PARAM_COMPANY)));
		getServletContext().getRequestDispatcher("/Dashboard").forward(request,response);
	}

}
