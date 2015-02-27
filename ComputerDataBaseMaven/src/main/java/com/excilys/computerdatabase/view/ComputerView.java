package com.excilys.computerdatabase.view;

import java.util.List;
import java.util.Scanner;

import com.excilys.computerdatabase.controller.CtrlComputerView;
import com.excilys.computerdatabase.helper.CheckEntry;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.dto.ComputerDTO;

public class ComputerView {
	boolean life;
	CtrlComputerView ctrl;
	Computer computer;
	String name, introduced, discontinued, company;
	
	public ComputerView() {
		ctrl = new CtrlComputerView();
	}

	public void showView(Scanner sc) {
		life = true;
		while (life) {
			System.out.println("Please type the number which correspond to your choice:\n1.List of all computer\n2.New computer "
							+ "\n3.Modify\n4.Delete\n5.Show detail\n6.Exit");
			String str = sc.nextLine();
			System.out.println("You type : " + str);
			switch(str.trim()) {
				case "1" :
					List<ComputerDTO> cs = ctrl.getAllComputer();
					for (ComputerDTO comp : cs) {
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
					if (ctrl.insertComputer(name, introduced, discontinued, company).getId()>0) {
						System.out.println("Sucess!");
					} else {
						System.out.println("Nothing insert...");
					}
					break;
				case "3" :
					System.out.println("Id of the computer to modify (0 to cancel) : ");
					str = sc.nextLine();
					if (CheckEntry.checkIsId(str)) {
						if (ctrl.computerExist(str)) {
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
							if (ctrl.updateComputer(name, introduced, discontinued, company, computer) > 0) {
								System.out.println("Sucess!");
							} else {
								System.out.println("Nothing updated...");
							}
						} else {
							System.out.println("Wrong entry");
						}
					} else {
						System.out.println("Wrong entry");
					}
					break;
				case "4" :
					System.out.println("Id of the computer to delete (0 to cancel) : ");
					str = sc.nextLine();
					if (CheckEntry.checkIsId(str)) {
						if (ctrl.deleteComputer(str) > 0) {
							System.out.println("Success");
						} else {
							System.out.println("No computer deleted, please check the id");
						}
					} else {
						System.out.println("Wrong entry");
					}
					break;
				case "5" :
					System.out.println("Id of the computer to show (0 to cancel) : ");
					str = sc.nextLine();
					if (CheckEntry.checkIsId(str)) {
						computer = ctrl.getComputerById(str);
						if (computer.getId() != 0 ) {
							System.out.println(computer.toString());
						} else {
							System.out.println("Wrong entry");
						}
					} else {
						System.out.println("Wrong entry");
					}
					break;
				case "6" :
					life = false;
					break;
			}
		}
	}
}
