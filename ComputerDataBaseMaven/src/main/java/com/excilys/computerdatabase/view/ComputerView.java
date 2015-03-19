package com.excilys.computerdatabase.view;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.controller.CtrlComputerView;
import com.excilys.computerdatabase.helper.CheckEntry;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.dto.ComputerDTO;

@Component
public class ComputerView {
	boolean life;
	@Autowired
	CtrlComputerView ctrl;
	Computer computer;
	ComputerDTO computerDTO;
	String name, introduced, discontinued, company;
	
	public ComputerView() {
//		ctrl = new CtrlComputerView();
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
					ctrl.insertComputer(name, introduced, discontinued, company);
					break;
				case "3" :
					System.out.println("Id of the computer to modify (0 to cancel) : ");
					str = sc.nextLine();
					if (CheckEntry.checkIsId(str)) {
						if (ctrl.computerExist(str)) {
							computerDTO = ctrl.getComputerById(str);
							System.out.println("Type just on \"Enter\" if you don't want to change");
							System.out.println("Name of the computer (mandatory) : " + computerDTO.getName());
							str = sc.nextLine();
							name = str;
							System.out.println("Introduced (yyyy-MM-dd): " + computerDTO.getIntroduced());
							str = sc.nextLine();
							introduced = str;
							System.out.println("Discontinued (yyyy-MM-dd): " + computerDTO.getDiscontinued());
							str = sc.nextLine();
							discontinued = str;
							System.out.println("Company's key (yyyy-MM-dd): " + computerDTO.getCompanyName());
							str = sc.nextLine();
							company = str;
							ctrl.updateComputer(name, introduced, discontinued, company, computerDTO);
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
						ctrl.deleteComputer(str);
					} else {
						System.out.println("Wrong entry");
					}
					break;
				case "5" :
					System.out.println("Id of the computer to show (0 to cancel) : ");
					str = sc.nextLine();
					if (CheckEntry.checkIsId(str)) {
						computerDTO = ctrl.getComputerById(str);
						if (computerDTO.getId() != 0 ) {
							System.out.println(computerDTO.toString());
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
