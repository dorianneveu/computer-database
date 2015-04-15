package com.excilys.computerdatabase.persistence;

import com.excilys.computerdatabase.model.User;

public interface IUserDAO {

	public abstract User get(String login);

}