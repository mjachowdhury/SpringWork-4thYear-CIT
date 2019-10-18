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

	public void saveACustomer(String firstName, String lastName, String address, String city, String contactNumber, String email) {
		if (customerDao.getCustomerByName(lastName) == 0) {
			customerDao.insertCustomer(firstName, lastName, address, city, contactNumber, email);
		}
	}

	public int changeCustomerName(String oldFirstName, String newFirstName,String oldLastName, String newLastName, String oldAddress, String newAddress,
			String oldCity, String newCity, String oldContactNumber, String newContactNumber, String oldEmail,
			String newEmail) {
		if(customerDao.getCustomerByName(oldLastName) == 1) {
			return customerDao.changeCustomerName(oldFirstName, newFirstName,oldLastName, newLastName, oldAddress, newAddress, oldCity, newCity, oldContactNumber, newContactNumber, oldEmail, newEmail);
		}
		return 0;
	}

	public void createACustomer(Customer c) {
		 customerDao.createCustomer(c);
		
	}

	 

}
