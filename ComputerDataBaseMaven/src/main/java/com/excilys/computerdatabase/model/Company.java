package com.excilys.computerdatabase.model;


import org.springframework.stereotype.Component;

@Component
public class Company {
	private int id;
	private String name;
	
	public Company() {
		this.id = 0;
	}
	public Company(String name) {
		this.id = 0;
		this.name = name;
	}
	public Company(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id : ");
		sb.append(this.id);
		sb.append(" name : ");
		sb.append(this.name);
		return sb.toString();
	}
	
	public boolean equals(Company c){
		if (this.id == c.id) {
			return true;
		}
		if (this.name.equals(c.name)) {
			return true;
		} else {
			return false;
		}
	}
}
