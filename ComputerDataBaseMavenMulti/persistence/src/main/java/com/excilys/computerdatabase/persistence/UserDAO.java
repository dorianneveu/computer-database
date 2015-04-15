package com.excilys.computerdatabase.persistence;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.model.User;

@Repository
public class UserDAO implements IUserDAO {
	@Autowired
	SessionFactory sessionFactory;
	public UserDAO() {
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IUserDAO#get(java.lang.String)
	 */
	@Override
	@Transactional
	public User get(String login) {

		User c = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select usr from User usr left join usr.role as role where usr.username = :login");
		query.setString("login", login);
		c = (User) query.uniqueResult();
		return c;
	}

}
