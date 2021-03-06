/**
 * 
 */
package ie.mohammed.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.mohammed.dao.CustomerDao;
import ie.mohammed.model.Customer;
import ie.mohammed.service.CustomerService;

/**
 * @author Mohammed
 *
 */

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;

	public int CountTotalCustomer() {
		return customerDao.getCustomerCount();
	}

	public int CustomerByName(String lastName) {
		return customerDao.getCustomerByName(lastName);
	}

	public List<Customer> findAllCustomer() {
		return customerDao.findAllCustomer();
	}

	public Customer findById(int customerId) {
		return customerDao.findById(customerId);
	}

	public void saveACustomer(String title, String firstName, String lastName, String address, String city,
			String contactNumber, String email, String password) {
		if (customerDao.getCustomerByName(lastName) == 0) {
			customerDao.insertCustomer(title, firstName, lastName, address, city, contactNumber, email, password);
		}
	}

	public int changeCustomerName(String oldTitle, String newTitle, String oldFirstName, String newFirstName,
			String oldLastName, String newLastName, String oldAddress, String newAddress, String oldCity,
			String newCity, String oldContactNumber, String newContactNumber, String oldEmail, String newEmail, String oldPassword, String newPassword) {
		if (customerDao.getCustomerByName(oldLastName) == 1) {
			return customerDao.changeCustomerName(oldTitle, newTitle, oldFirstName, newFirstName, oldLastName,
					newLastName, oldAddress, newAddress, oldCity, newCity, oldContactNumber, newContactNumber, oldEmail,
					newEmail, oldPassword, newPassword);
		}
		return 0;
	}

	public void createACustomer(Customer c) {
		customerDao.createCustomer(c);

	}

}
