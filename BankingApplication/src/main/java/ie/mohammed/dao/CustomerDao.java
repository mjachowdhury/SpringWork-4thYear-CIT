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
	int getCustomerCount();
	int getCustomerByName(String lastName);
	List<Customer> findAll();
	Customer findById(int customerId);
	
	void insertCustomer(String firstName, String lastName, String address, String city, String contactNumber, String email);
	void addACustomer(String firstName, String lastName, String address, String city, String contactNumber, String email);
	int changeCustomerName(String oldFisrtName, String newFirstName, String oldAddress, String newAddress,String oldCity, String newCity, String oldContactNumber, String newContactNumber, String oldEmail, String newEmail);

}
