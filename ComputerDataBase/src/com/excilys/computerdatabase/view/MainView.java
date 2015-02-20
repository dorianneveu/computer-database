package com.excilys.computerdatabase.view;

import java.util.List;
import java.util.Scanner;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.*;

public class MainView {

	public static void main(String[] args) {
		boolean life = true;
		ComputerDAO computerDAO = new ComputerDAO();
		CompanyDAO companyDAO = new CompanyDAO();
		Computer computer;
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
								List<Computer> cs = computerDAO.getAll();
								for (Computer comp : cs) {
									System.out.println(comp.toStringLittle());
								}
								break;
							case "2" :
								System.out.println("Name of the computer (mandatory) : ");
								str = sc.nextLine();
								computer = new Computer(str);
								System.out.println("Introduced (yyyy-MM-dd): ");
								str = sc.nextLine();
								computer.setIntroduced(str);
								System.out.println("Discontinued (yyyy-MM-dd): ");
								str = sc.nextLine();
								computer.setDiscontinued(str);
								System.out.println("Company's key (yyyy-MM-dd): ");
								str = sc.nextLine();
								if(str.equals("")){
									computer.setCompany(null);
								}
								else
									computer.setCompany(new Company(Integer.parseInt(str)));
								computerDAO.create(computer);
								break;
							case "3" :
								System.out.println("Id of the computer to modify (0 to cancel) : ");
								str = sc.nextLine();
								if(!str.equals("0") && str.matches("[0-9]+")){
									computer = computerDAO.get(Integer.parseInt(str));
									if(computer.getId() != 0 )
									{
										System.out.println("Type just on \"Enter\" if you don't want to change");
										System.out.println("Name of the computer (mandatory) : " + computer.getName());
										str = sc.nextLine();
										if(!str.equals(""))
											computer.setName(str);
										System.out.println("Introduced (yyyy-MM-dd): " + computer.getIntroduced());
										str = sc.nextLine();
										if (str.matches("\\d{4}-\\d{2}-\\d{2}"))
											computer.setIntroduced(str);
										System.out.println("Discontinued (yyyy-MM-dd): " + computer.getDiscontinued());
										str = sc.nextLine();
										if (str.matches("\\d{4}-\\d{2}-\\d{2}"))
											computer.setDiscontinued(str);
										System.out.println("Company's key (yyyy-MM-dd): " + computer.getCompany().getName());
										str = sc.nextLine();
										if(!str.equals("") && !str.equals("0") && str.matches("[0-9]+")){
											computer.setCompany(new Company(Integer.parseInt(str)));
										}
										computerDAO.update(computer);
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
									computerDAO.delete(computerDAO.get(Integer.parseInt(str)));
									System.out.println("Success");
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
					for (Company company : cs) {
						System.out.println(company.getId() + " : "
								+ company.getName());
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
