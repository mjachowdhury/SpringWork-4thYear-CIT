/**
 * 
 */
package ie.mohammed.model.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ie.mohammed.model.Customer;

/**
 * @author Mohammed
 *
 */
public class CustomerRowMapper implements RowMapper<Customer>{

	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Customer  customer = new Customer();
		customer.setCustomerId(rs.getInt("customerId"));
		customer.setFirstName(rs.getString("fistName"));
		customer.setLastName(rs.getString("lastName"));
		customer.setAddress(rs.getString("address"));
		customer.setCity(rs.getString("city"));
		customer.setContactNumber(rs.getString("contactNumber"));
		customer.setEmail(rs.getString("email"));
		return customer;
	}

}
