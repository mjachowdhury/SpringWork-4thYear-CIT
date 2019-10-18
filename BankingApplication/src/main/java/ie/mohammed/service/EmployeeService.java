package ie.mohammed.service;

import java.util.List;

import ie.mohammed.model.Employee;

public interface EmployeeService {
	
	List<Employee> totalEmployees();
	
	int totalEmployeesCount();
	String findEmployeeByName(String lastName);
	int getEmployeeById(int id);
	Employee findById(int employeeId);
	Employee findByPassword(String employeePassword);
	
	void saveANewEmployee(String firstName, String lastName, String password);
	//void insertANewEmployee(String firstName, String lastName, String password);
	

}
