/**
 * 
 */
package ie.mohammed.dao;

import java.util.List;

import ie.mohammed.model.Customer;

/**
 * @author Mohammed
 *
 */
public interface CustomerDao {
	
	
	void createCustomer(Customer c);
	List<Customer> findAllCustomer();

	int getCustomerCount();
	int getCustomerByName(String lastName);	
	Customer findById(int customerId);	
	void insertCustomer(String firstName, String lastName, String address, String city, String contactNumber, String email);
	void addACustomer(String firstName, String lastName, String address, String city, String contactNumber, String email);
	int changeCustomerName(String oldFirstName, String newFirstName,String oldLastName, String newLastName, String oldAddress, String newAddress,String oldCity, String newCity, String oldContactNumber, String newContactNumber, String oldEmail, String newEmail);

}
