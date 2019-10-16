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
	
	public int getCustomerCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getCustomerByName(String lastName) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Customer> findAllCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer findById(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveACustomer(String firstName, String lastName, String address, String city, String contactNumber,
			String email) {
		// TODO Auto-generated method stub
		
	}

	public int changeCustomerName(String oldFisrtName, String newFirstName, String oldAddress, String newAddress,
			String oldCity, String newCity, String oldContactNumber, String newContactNumber, String oldEmail,
			String newEmail) {
		// TODO Auto-generated method stub
		return 0;
	}

}
