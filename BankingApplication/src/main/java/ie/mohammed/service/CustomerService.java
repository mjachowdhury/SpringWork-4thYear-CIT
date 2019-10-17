package ie.mohammed.service;

import java.util.List;

import ie.mohammed.model.Customer;

public interface CustomerService {
	int CountTotalCustomer();
	int CustomerByName(String lastName);
	List<Customer> findAllCustomer();
	Customer findById(int customerId);
	
	//void insertCustomer(String firstName, String lastName, String address, String city, String contactNumber, String email);
	void saveACustomer(String firstName, String lastName, String address, String city, String contactNumber, String email);
	int changeCustomerName(String oldFirstName, String newFirstName,String oldLastName, String newLastName, String oldAddress, String newAddress,String oldCity, String newCity, String oldContactNumber, String newContactNumber, String oldEmail, String newEmail);
	void createACustomer(Customer c);

}
