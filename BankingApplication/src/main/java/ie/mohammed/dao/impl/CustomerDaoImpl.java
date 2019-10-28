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

	public List<Customer> findAllCustomer() {
		String sql = "SELECT * FROM customer";
		List<Customer> customers = jdbcTemplate.query(sql, new CustomerRowMapper());
		for (Customer c : customers) {
			if (c.getAccounts().size() > 0) {
				c.setSavingAccount(c.getAccounts().get(0).getAccountNumber());
			}
		}
		return customers;

	}

	public Customer findById(int customerId) {
		String sql = "SELECT * FROM customer WHERE customerId=?";
		Customer customer = jdbcTemplate.queryForObject(sql, new CustomerRowMapper(), customerId);
		return customer;
	}

	public void insertCustomer(String title, String firstName, String lastName, String address, String city,
			String contactNumber, String email, String password) {
		String sql = "INSERT INTO customer(title, firstName,lastName,address,city,contactNumber,email, password) VALUES (?,?,?,?,?,?,?,? )";
		jdbcTemplate.update(sql, title, firstName, lastName, address, city, contactNumber, email, password);

	}

	public void addACustomer(String title, String firstName, String lastName, String address, String city,
			String contactNumber, String email, String password) {
		String sql = "INSERT INTO customer(title,firstName,lastName,address,city,contactNumber,email, password) VALUES (?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] { title, firstName, lastName, address, city, contactNumber, email, password });

	}

	public int changeCustomerName(String oldTitle, String newTitle, String oldFirstName, String newFirstName,
			String oldLastName, String newLastName, String oldAddress, String newAddress, String oldCity,
			String newCity, String oldContactNumber, String newContactNumber, String oldEmail, String newEmail, String oldPassword, String newPassword) {
		return jdbcTemplate.update(
				"UPDATE customer SET title=? firstName=? lastName=? address=? city=? contactNumber=? email=? password=? "
						+ "	where title=? firstName=? lastName=? address=? city=? contactNumber=? email=?, password=?",
				new Object[] { newTitle, oldTitle, newFirstName, oldFirstName, oldLastName, newLastName, newAddress,
						oldAddress, newCity, oldCity, newContactNumber, oldContactNumber, newEmail, oldEmail, newPassword, oldPassword });

	}

	public void createCustomer(Customer c) {

	}

}
