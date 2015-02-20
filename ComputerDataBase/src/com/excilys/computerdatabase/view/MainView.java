package com.excilys.computerdatabase.view;

import java.util.List;
import java.util.Scanner;

import com.excilys.computerdatabase.controller.CtrlMainView;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.*;

public class MainView {

	public static void main(String[] args) {
		boolean life = true;
		CtrlMainView ctrl = new CtrlMainView();
		ComputerDAO computerDAO = new ComputerDAO();
		CompanyDAO companyDAO = new CompanyDAO();
		Computer computer;
		String name, introduced, discontinued, company;
		while (life) {
			Scanner sc = new Scanner(System.in);
			System.out
					.println("Bonjour, veuillez choisir l'un de ces choix en indiquant le bon numéro:\n1.Computer\n2.Liste des "
							+ "entreprises\n3.Quitter");
			String str = sc.nextLine();
			System.out.println("Vous avez saisi : " + str);
			switch(str.trim()){
				case "1" :
					boolean lifeComputer = true;
					while (lifeComputer) {
						System.out
								.println("Veuillez choisir l'un de ces choix en indiquant le bon numéro:\n1.Liste des ordinateurs\n2.New computer "
										+ "\n3.Modifier\n4.Supprimer\n5.Afficher détails\n6.Quitter");
						str = sc.nextLine();
						System.out.println("Vous avez saisi : " + str);
						switch(str.trim()){
							case "1" :
								// codes controller
								List<Computer> cs = ctrl.getAllCompany();
								for (Computer comp : cs) {
									System.out.println(comp.toStringLittle());
								}
								break;
							case "2" :
								System.out.println("Name of the computer (mandatory) : ");
								str = sc.nextLine();
								name = str;
								System.out.println("Introduced (yyyy-MM-dd): ");
								str = sc.nextLine();
								introduced = str;
								System.out.println("Discontinued (yyyy-MM-dd): ");
								str = sc.nextLine();
								discontinued = str;
								System.out.println("Company's key (yyyy-MM-dd): ");
								str = sc.nextLine();
								company = str;
								if(ctrl.insertComputer(name, introduced, discontinued, company).getId()>0){
									System.out.println("Sucess!");
								}else{
									System.out.println("Nothing insert...");
								}
								break;
							case "3" :
								System.out.println("Id of the computer to modify (0 to cancel) : ");
								str = sc.nextLine();
								if(ctrl.checkIsId(str)){
									if(ctrl.computerExist(str))
									{
										computer = ctrl.getComputerById(str);
										System.out.println("Type just on \"Enter\" if you don't want to change");
										System.out.println("Name of the computer (mandatory) : " + computer.getName());
										str = sc.nextLine();
										name = str;
										System.out.println("Introduced (yyyy-MM-dd): " + computer.getIntroduced());
										str = sc.nextLine();
										introduced = str;
										System.out.println("Discontinued (yyyy-MM-dd): " + computer.getDiscontinued());
										str = sc.nextLine();
										discontinued = str;
										System.out.println("Company's key (yyyy-MM-dd): " + computer.getCompany().getName());
										str = sc.nextLine();
										company = str;
										
										if(ctrl.updateComputer(name, introduced, discontinued, company, computer) > 0){
											System.out.println("Sucess!");
										}else{
											System.out.println("Nothing insert...");
										}
									}
									else{
										System.out.println("Wrong entry");
									}
								}else{
									System.out.println("Wrong entry");
								}
								
								break;
							case "4" :
								System.out.println("Id of the computer to delete (0 to cancel) : ");
								str = sc.nextLine();
								if(!str.equals("0") && str.matches("[0-9]+")){
									int i = computerDAO.delete(computerDAO.get(Integer.parseInt(str)));
									if(i == 1){
										System.out.println("Success");
									}else{
										System.out.println("No computer deleted, please check the id");
									}
									
								}else{
									System.out.println("Wrong entry");
								}
								break;
							case "5" :
								System.out.println("Id of the computer to show (0 to cancel) : ");
								str = sc.nextLine();
								if(!str.equals("0") && str.matches("[0-9]+")){
									computer = computerDAO.get(Integer.parseInt(str));
									if(computer.getId() != 0 ){
										System.out.println(computerDAO.get(Integer.parseInt(str)).toString());
									}
									else{
										System.out.println("Wrong entry");
									}
								}else{
									System.out.println("Wrong entry");
								}
								break;
							case "6" :
								lifeComputer = false;
								break;
						}
					}
					break;
				case "2" :
					// codes controller
					List<Company> cs = companyDAO.getAll();
					for (Company company1 : cs) {
						System.out.println(company1.getId() + " : "
								+ company1.getName());
					}
					break;
				case "3" :
					System.out.println("Au revoir");
					life = false;
					sc.close();
					break;
				default :
					break;
			}
		}

	}

}
