package ie.mohammed.service;

import java.util.List;

import ie.mohammed.model.Employee;

public interface EmployeeService {
	
	List<Employee> totalEmployees();
	
	int totalEmployeesCount();
	int findEmployeeByName(String lastName);
	Employee findById(int employeeId);
	void saveANewEmployee(String firstName, String lastName, String password);
	//void insertANewEmployee(String firstName, String lastName, String password);
	

}
