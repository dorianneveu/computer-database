package com.excilys.computerdatabase.persistence;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.helper.DateConverter;
import com.excilys.computerdatabase.helper.MapperComputerJDBC;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

@Repository
public class ComputerDAO implements IComputerDAO {
	
	private DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private ComputerDAO() {
		
	}
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#get(int, java.sql.Connection)
	 */
	@Override
	public Computer get(int id) {
		Computer computer = new Computer();
		String query = "SELECT computer.*, company.name FROM computer  LEFT OUTER JOIN company ON computer.company_id = company.id WHERE computer.id = ?"; 
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        computer = (Computer)jdbcTemplate.queryForObject(query, new Object[] {id}, new MapperComputerJDBC());
		return computer;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#getAll(java.sql.Connection)
	 */
	@Override
	public List<Computer> getAll() {
		List<Computer> computers = new ArrayList<Computer>();
		String query = "SELECT computer.*, company.name FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id"; 
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
		for (Map row : rows) {
			Computer computer = new Computer();
			if(row.get("company_id") != null) {
				Company company = new Company(Integer.parseInt(String.valueOf(row.get("company_id"))), (String)row.get("cname"));
				computer.setCompany(company);
			} else {
				computer.setCompany(new Company());
			}
			computer.setId(Integer.parseInt(String.valueOf(row.get("id"))));
			computer.setName((String)row.get("name"));
			if(row.get("introduced") != null) {
				computer.setIntroduced(row.get("introduced").toString());
			}
			if(row.get("discontinued") != null) {
				computer.setDiscontinued(row.get("discontinued").toString());
			}
			computers.add(computer);
		}
		return computers;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#create(com.excilys.computerdatabase.model.Computer, java.sql.Connection)
	 */
	@Override
	public Computer create(Computer computer) {
		String query = "INSERT INTO computer(name, introduced, discontinued, company_id) values (?,?,?,?)";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
 
        Timestamp introduced = null;
        Timestamp discontinued = null;
        int company = 0;
			if (!String.valueOf(computer.getIntroduced()).equals("0000-00-00") && computer.getIntroduced() != null) {
				introduced = new Timestamp(DateConverter.stringToDate(computer.getIntroduced()).getTime());
			}
			if (!String.valueOf(computer.getDiscontinued()).equals("0000-00-00") && computer.getDiscontinued() != null) {
				discontinued = new Timestamp(DateConverter.stringToDate(computer.getDiscontinued()).getTime());
			}
			if(computer.getCompany() != null && computer.getCompany().getId() != 0) {
				company = computer.getCompany().getId();
			}
			

			if(company == 0) {
				jdbcTemplate.update(query, new Object[] { computer.getName(), introduced, discontinued, null });
			} else {
				jdbcTemplate.update(query, new Object[] { computer.getName(), introduced, discontinued, company });
			}
		return computer;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#update(com.excilys.computerdatabase.model.Computer, java.sql.Connection)
	 */
	@Override
	public int update(Computer computer) {
		String query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Timestamp introduced = null;
		Timestamp discontinued = null;
		int i = 0;
		if (!String.valueOf(computer.getIntroduced()).equals("0000-00-00") && computer.getIntroduced() != null) {
			introduced = new Timestamp(DateConverter.stringToDate(computer.getIntroduced()).getTime());
		}
		if (!String.valueOf(computer.getDiscontinued()).equals("0000-00-00") && computer.getDiscontinued() != null) {
			discontinued = new Timestamp(DateConverter.stringToDate(computer.getDiscontinued()).getTime());
		}
		i = jdbcTemplate.update(query, new Object[] { computer.getName(), introduced, discontinued, computer.getCompany().getId(), computer.getId()});
		return i;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#delete(com.excilys.computerdatabase.model.Computer, java.sql.Connection)
	 */
	@Override
	public int delete(Computer computer) {
		int value = 0;
		
		String query = "DELETE FROM computer WHERE id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        value = jdbcTemplate.update(query, new Object[] { computer.getId() });
		return value;
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#deleteByCompany(com.excilys.computerdatabase.model.Company, java.sql.Connection)
	 */
	@Override
	public void deleteByCompany(Company company) {
		String query = "DELETE FROM computer WHERE company_id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(query, new Object[] { company.getId() });
	}
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#getCount(java.sql.Connection)
	 */
	@Override
	public long getCount() {
		long value = 0;
		String query = "SELECT count(name) FROM computer"; 
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        value = jdbcTemplate.queryForLong(query);
		return value;
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#getCountByName(java.lang.String, java.sql.Connection)
	 */
	@Override
	public long getCountByName(String name) {
		long value = 0;
		String query = "SELECT count(name) FROM computer WHERE name like ? "; 
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        value = jdbcTemplate.queryForLong(query, new Object[] { (name+"%") });
		return value;
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#getAllLimit(int, int, java.lang.String, java.lang.String, java.sql.Connection)
	 */
	@Override
	public List<Computer> getAllLimit(int limit, int offset, String sort, String type) {
		List<Computer> computers = new ArrayList<Computer>();
		String query = "SELECT computer.*, company.name FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id ORDER BY "+sort+" "+type+" LIMIT ? OFFSET ?"; 
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, new Object[] {limit, offset} );
		for (Map row : rows) {
			Computer computer = new Computer();
			if(row.get("company_id") != null) {
				Company company = new Company(Integer.parseInt(String.valueOf(row.get("company_id"))), (String)row.get("cname"));
				computer.setCompany(company);
			} else {
				computer.setCompany(new Company());
			}
			computer.setId(Integer.parseInt(String.valueOf(row.get("id"))));
			computer.setName((String)row.get("name"));
			if(row.get("introduced") != null) {
				computer.setIntroduced(row.get("introduced").toString());
			}
			if(row.get("discontinued") != null) {
				computer.setDiscontinued(row.get("discontinued").toString());
			}
			computers.add(computer);
		}
		return computers;
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.IComputerDAO#findByName(java.lang.String, int, int, java.lang.String, java.lang.String, java.sql.Connection)
	 */
	@Override
	public List<Computer> findByName(String name, int limit, int offset, String sort, String type) {
		
		List<Computer> computers = new ArrayList<Computer>();
		String query = "SELECT computer.*, company.name as cname FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? ORDER BY "+sort+" "+type+" LIMIT ? OFFSET ?"; 
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, new Object[] { (name+"%"), limit, offset} );
		for (Map row : rows) {
			Computer computer = new Computer();
			if(row.get("company_id") != null) {
				Company company = new Company(Integer.parseInt(String.valueOf(row.get("company_id"))), (String)row.get("cname"));
				computer.setCompany(company);
			} else {
				computer.setCompany(new Company());
			}
			computer.setId(Integer.parseInt(String.valueOf(row.get("id"))));
			computer.setName((String)row.get("name"));
			if(row.get("introduced") != null) {
				computer.setIntroduced(row.get("introduced").toString());
			}
			if(row.get("discontinued") != null) {
				computer.setDiscontinued(row.get("discontinued").toString());
			}
			computers.add(computer);
		}
		return computers;
	}
}
