package com.excilys.computerdatabase.view;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.computerdatabase.service.UserBL;


public class Launcher {

	public static void main(String[] args) throws SQLException {
   		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext-console.xml");
		MainView m = (MainView) ctx.getBean(MainView.class);	
		m.cli();
		ctx.close();
	}
}
