package com.excilys.computerdatabase.view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.clictrl.CtrlMainView;
import com.excilys.computerdatabase.helper.CheckEntry;
import com.excilys.computerdatabase.model.Company;
@Component
public class MainView {

	@Autowired
	CtrlMainView ctrl;
	@Autowired
	ComputerView computerView;
	
	public MainView() {}
			
	public void cli() throws SQLException {
		boolean life = true;
		while (life) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Hi, please type the number which correspond to your choice:\n1.Computer\n2.List of all "
							+ "companies\n3.Exit\4.Delete a company");
			String str = sc.nextLine();
			System.out.println("You type : " + str);
			switch(str.trim()) {
				case "1" :
					//Launch the dialog for the action on computer
					computerView.showView(sc);
					break;
				case "2" :
					List<Company> cs = ctrl.getAllCompany();
					for (Company company1 : cs) {
						System.out.println(company1.toString());
					}
					break;
				case "3" :
					System.out.println("Good bye");
					life = false;
					sc.close();
					break;
				case "4" :
					System.out.println("Id of the company to delete (0 to cancel) : ");
					str = sc.nextLine();
					if (CheckEntry.checkIsId(str)) {
						ctrl.deleteCompany(str);
						System.out.println("Success");
					} else {
						System.out.println("Wrong entry");
					}
					break;
				default :
					break;
			}
		}
	}
}
