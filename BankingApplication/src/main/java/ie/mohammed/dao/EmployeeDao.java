package ie.mohammed.dao;

import java.util.List;

import ie.mohammed.model.Employee;

 
public interface EmployeeDao {
	
	List<Employee> getAllEmployee();
	int getEmployeeCount();
	int getEmployeeByName(String lastName);
	Employee findById(int employeeId);
	void insertANewEmployee(String firstName, String lastName, String password);
	void addANewEmployee(String firstName, String lastName, String password);

}
