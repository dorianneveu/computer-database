package com.excilys.computerdatabase.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.*;

public class MainView {

	public static void main(String[] args) {
		boolean life = true;
		while (life) {
			Scanner sc = new Scanner(System.in);
			System.out
					.println("Bonjour, veuillez choisir l'un de ces choix en indiquant le bon numéro:\n1.Computer\n2.Liste des "
							+ "entreprises\n3.Quitter");
			String str = sc.nextLine();
			System.out.println("Vous avez saisi : " + str);
			if (str.trim().equals("1")) {

			}
			if (str.trim().equals("2")) {
				// codes controller
				CompanyDAO cDAO = new CompanyDAO();
				List<Company> cs = cDAO.getAll();
				for (Company company : cs) {
					System.out.println(company.getId() + " : "
							+ company.getName());
				}
			}
			if (str.trim().equals("3")) {
				System.out.println("Au revoir");
				life = false;
			}
		}

	}

}
