package ie.mohammed.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ie.mohammed.dao.EmployeeDao;
import ie.mohammed.model.Employee;
import ie.mohammed.model.rowMappers.EmployeeRowMapper;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Employee> getAllEmployee() {
		String sql = "SELECT * FROM employee";
		List<Employee> employee = jdbcTemplate.query(sql, new EmployeeRowMapper());
		return employee;
	}

	public int getEmployeeCount() {
		String sql = "SELECT COUNT (*) FROM employee";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public int getEmployeeByName(String lastName) {
		String sql = "SELECT COUNT (*) FROM employee WHERE employee.lastName LIKE ?";
		return jdbcTemplate.queryForObject(sql, Integer.class,lastName);
	}

	public Employee findById(int employeeId) {
		String sql = "SELECT * FROM employee WHERE employeeId=?";
		Employee employee = jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), employeeId);
		return employee;
	}

	public void insertANewEmployee(String firstName, String lastName, String password) {
		String sql = "INSERT INTO employee(firstName,lastName,password) VALUES (?,?,? )";
		jdbcTemplate.update(sql, firstName,lastName,password);
		 
		
	}

	public void addANewEmployee(String firstName, String lastName, String password) {
		String sql = "INSERT INTO employee(firstName,lastName,password) VALUES (?,?,?)";
		jdbcTemplate.update(sql, new Object[] { firstName,lastName,password });

		
	}

}
