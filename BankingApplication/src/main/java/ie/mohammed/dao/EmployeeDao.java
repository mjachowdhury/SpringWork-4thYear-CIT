package ie.mohammed.dao;

import java.util.List;

import ie.mohammed.model.Employee;

 
public interface EmployeeDao {
	
	List<Employee> getAllEmployee();
	
	int getEmployeeCount();
	String getEmployeeByName(String lastName);
	int getEmployeeById(int id);
	
	Employee findById(int employeeId);
	Employee findByPassword(String employeePassword);
	
	void insertANewEmployee(String firstName, String lastName, String password);
	void addANewEmployee(String firstName, String lastName, String password);

}
