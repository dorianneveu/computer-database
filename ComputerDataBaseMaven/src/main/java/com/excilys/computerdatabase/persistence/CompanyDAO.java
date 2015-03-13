package com.excilys.computerdatabase.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.helper.MapperCompanyJDBC;
import com.excilys.computerdatabase.model.Company;

@Repository
public class CompanyDAO implements ICompanyDAO {
	private DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public CompanyDAO() {
		
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.ICompanyDAO#get(int)
	 */
	@Override
	public Company get(int id) {
		Company company = new Company();
		String query = "SELECT id, name FROM company WHERE id = ?"; 
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        
        company = (Company)jdbcTemplate.queryForObject(query, new Object[] {id}, new MapperCompanyJDBC());
		return company;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.ICompanyDAO#getAll()
	 */
	@Override
	public List<Company> getAll() {

		String query = "SELECT id, name FROM company"; 
		List<Company> companies = new ArrayList<Company>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
		for (Map row : rows) {
			Company company = new Company();
			company.setId(Integer.parseInt(String.valueOf(row.get("id"))));
			company.setName((String)row.get("name"));
			companies.add(company);
		}
		return companies;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.ICompanyDAO#create(com.excilys.computerdatabase.model.Company)
	 */
	@Override
	public Company create(Company company) {
		String query = "INSERT INTO company(name) values (?)";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
 
        jdbcTemplate.update(query, new Object[] { company.getName() });
		return company;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.ICompanyDAO#update(com.excilys.computerdatabase.model.Company)
	 */
	@Override
	public void update(Company company) {
		String query = "UPDATE company SET name = ? WHERE id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(query, new Object[] { company.getName(), company.getId() });
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.ICompanyDAO#delete(com.excilys.computerdatabase.model.Company)
	 */
	@Override
	public void delete(Company company) {
		String query = "DELETE FROM company WHERE id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(query, new Object[] { company.getId() });
	}

}
