/**
 * 
 */
package ie.mohammed.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ie.mohammed.dao.CustomerDao;
 
import ie.mohammed.model.Customer;
import ie.mohammed.model.rowMappers.CustomerRowMapper;

/**
 * @author Mohammed
 *
 */
@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int getCustomerCount() {
		String sql = "SELECT COUNT (*) FROM customer";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public int getCustomerByName(String lastName) {
		String sql = "SELECT  COUNT (*) FROM customer WHERE customer.lastName LIKE ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, lastName);
	
	}

	public List<Customer> findAll() {
		String sql = "SELECT * FROM customer";
		List<Customer> customers = jdbcTemplate.query(sql, new CustomerRowMapper());
		return customers;
		
	}

	public Customer findById(int customerId) {
		String sql = "SELECT * FROM customer WHERE customerId=?";
		Customer customer = jdbcTemplate.queryForObject(sql, new CustomerRowMapper(), customerId);
		return customer;
	}

	public void insertCustomer(String firstName, String lastName, String address, String city, String contactNumber,
			String email) {
		String sql = "INSERT INTO customer(firstName,lastName,address,city,contactNumber,email) VALUES (?,?,?,?,?,?)";
		jdbcTemplate.update(sql, firstName,lastName,address,city,contactNumber,email);
		
	}

	public void addACustomer(String firstName, String lastName, String address, String city, String contactNumber,
			String email) {
		String sql = "INSERT INTO customer(firstName,lastName,address,city,contactNumber,email) VALUES (?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] { firstName,lastName,address,city,contactNumber,email });

		
	}

	public int changeCustomerName(String oldFisrtName, String newFirstName,String oldLastName, String newLastName, String oldAddress, String newAddress, String oldCity, String newCity, String oldContactNumber,
			String newContactNumber, String oldEmail, String newEmail) {
		return jdbcTemplate.update("UPDATE customer SET firstName=? lastName=? address=? city=? contactNumber=? email=? "
												+ "	where firstName=? lastName=? address=? city=? contactNumber=? email=?", 
													new Object[] { newFirstName, oldFisrtName,oldLastName,newLastName, newAddress, oldAddress, newCity, oldCity, newContactNumber, oldContactNumber, newEmail, oldEmail });
		
	}

}
