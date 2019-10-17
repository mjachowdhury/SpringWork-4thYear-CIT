package ie.mohammed.model.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ie.mohammed.model.Employee;

public class EmployeeRowMapper implements RowMapper<Employee> {

	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Employee employee = new Employee();
		employee.setEmployeeId(rs.getInt("employeeId"));
		employee.setFirstName(rs.getString("firstName"));
		employee.setLastName(rs.getString("lastName"));
		employee.setPassword(rs.getString("password"));
		return employee;
	}

}
