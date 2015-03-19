package com.excilys.computerdatabase.view;

import java.sql.SQLException;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Launcher {

	public static void main(String[] args) throws SQLException {
   		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
		MainView m = (MainView) ctx.getBean(MainView.class);	
		m.cli();
		ctx.close();
	}
}
