package com.excilys.computerdatabase.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.model.Company;

@Repository
public class CompanyDAO implements ICompanyDAO {
	@Autowired
	SessionFactory sessionFactory;

	public CompanyDAO() {
		
	}
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.ICompanyDAO#get(int)
	 */
	@Override
	@Transactional
	public Company get(int id) {

		Company c = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Company where id= :id");
		query.setInteger("id", id);
		c = (Company) query.uniqueResult();
		return c;
	}
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.ICompanyDAO#getAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Company> getAll() {
		Session s = sessionFactory.getCurrentSession();
	    Query q = s.createQuery("from Company");
	    List<Company> companies = (ArrayList<Company>) q.list();
		return companies;
	}
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.ICompanyDAO#delete(com.excilys.computerdatabase.model.Company)
	 */
	@Override
	@Transactional
	public void delete(Company company) {
		Session s = sessionFactory.getCurrentSession();
	    Query q = s.createQuery("delete Company where id= :id");
		q.setInteger("id", company.getId());
        q.executeUpdate();
	}

}
