package com.excilys.computerdatabase.view;

import java.net.URLClassLoader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.computerdatabase.helper.HibernateUtils;
import com.excilys.computerdatabase.model.Company;

public class Launcher {

	public static void main(String[] args) throws SQLException {
//		URLClassLoader classLoader = (URLClassLoader)Launcher.class.getClassLoader();
//        System.out.println(Arrays.toString(classLoader.getURLs()));
        
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
		MainView m = (MainView) ctx.getBean(MainView.class);
//		
//		print();
//		// Récupération d'une session Hibernate
//        Session s = HibernateUtils.getSession();
// 
//        // Début de la transaction
//        Transaction t = s.beginTransaction();
// 
//        // Création d'un objet Event
//        Company e = new Company();
//        e.setName("La companie de DIEU");
// 
//        // Enregistrement de l'event
//        s.save(e);
// 
//        // Fin de la transaction
//        t.commit();
		
		
		m.cli();
		ctx.close();
	}
	
	private static void print() {
		// Récupération d'une session Hibernate
        Session s = HibernateUtils.INSTANCE.sessionFactory.openSession();
	      // Début de la transaction
//	      Transaction tx = s.beginTransaction();
	 
	      // Création de la requête
	      Query q = s.createQuery("from Company");
	      // Récupération de la liste des résultats 
	      List<Company> list = (ArrayList<Company>) q.list();
	      // Affichage des résultats
	      for (Company e: list) {
	            System.out.println("Event : [id] = " + e.getId() + "\t" +
	                        "[title] = " + e.getName() + "\t");
	      }
	 
	      // Fin de la transaction
//	      tx.commit();
	}

}
