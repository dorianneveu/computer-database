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
import com.excilys.computerdatabase.model.Computer;

@Repository
public class ComputerDAO implements IComputerDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	
	public ComputerDAO() {
		
	}
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#get(int, java.sql.Connection)
	 */
	@Override
	@Transactional
	public Computer get(int id) {
		Computer computer = new Computer();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select cmp from Computer cmp left outer join cmp.company as company where cmp.id = :id");
		query.setInteger("id", id);
		computer = (Computer) query.uniqueResult();
		return computer;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#getAll(java.sql.Connection)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Computer> getAll() {
		Session s = sessionFactory.getCurrentSession();
	    Query q = s.createQuery("from Computer");
	    List<Computer> computers = (ArrayList<Computer>) q.list();
		return computers;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#create(com.excilys.computerdatabase.model.Computer, java.sql.Connection)
	 */
	@Override
	@Transactional
	public Computer create(Computer computer) {
		sessionFactory.getCurrentSession().save(computer);
		return computer;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#update(com.excilys.computerdatabase.model.Computer, java.sql.Connection)
	 */
	@Override
	@Transactional
	public int update(Computer computer) {
		sessionFactory.getCurrentSession().update(computer);
		return 1;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#delete(com.excilys.computerdatabase.model.Computer, java.sql.Connection)
	 */
	@Override
	@Transactional
	public int delete(Computer computer) {
		sessionFactory.getCurrentSession().delete(computer);
		return 1;
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#deleteByCompany(com.excilys.computerdatabase.model.Company, java.sql.Connection)
	 */
	@Override
	@Transactional
	public void deleteByCompany(Company company) {
		Session s = sessionFactory.getCurrentSession();
	    Query q = s.createQuery("delete from Computer where company_id = :id");
	    q.setInteger("id", company.getId());
	    q.executeUpdate();
	}
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#getCount(java.sql.Connection)
	 */
	@Override
	@Transactional
	public long getCount() {
		long value = 0;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Computer");
		value = (long) query.uniqueResult();
		return value;
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#getCountByName(java.lang.String, java.sql.Connection)
	 */
	@Override
	@Transactional
	public long getCountByName(String name) {
		long value = 0;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Computer where name like :name");
		query.setString("name", name+"%");
		value = (long) query.uniqueResult();
		return value;
	}
	
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#findByName(java.lang.String, int, int, java.lang.String, java.lang.String, java.sql.Connection)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Computer> findByName(String name, int limit, int offset, String sort, String type) {
		Session s = sessionFactory.getCurrentSession();
	    Query q = s.createQuery("select cmp from Computer cmp left join cmp.company as company where cmp.name like :name order by "+sort+" "+type);
	    q.setString("name", name+"%");
	    q.setFirstResult(offset);
	    q.setMaxResults(limit);
	    List<Computer> computers = (ArrayList<Computer>) q.list();
		return computers;
	}
}
