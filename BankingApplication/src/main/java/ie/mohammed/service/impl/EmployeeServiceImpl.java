package ie.mohammed.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.mohammed.dao.EmployeeDao;
import ie.mohammed.model.Employee;
import ie.mohammed.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;

	public List<Employee> totalEmployees() {
		return employeeDao.getAllEmployee();
	}

	public int totalEmployeesCount() {
		return employeeDao.getEmployeeCount();
	}

	public String findEmployeeByName(String lastName) {
		return employeeDao.getEmployeeByName(lastName);
	}

	public int getEmployeeById(int id) {
		return employeeDao.getEmployeeById(id);
	}

	public Employee findById(int employeeId) {
		return employeeDao.findById(employeeId);
	}

	public Employee findByPassword(String employeePassword) {
		return employeeDao.findByPassword(employeePassword);
	}

	public void saveANewEmployee(String firstName, String lastName, String password) {
		// if(employeeDao.getEmployeeByName(lastName) == null) {
		employeeDao.insertANewEmployee(firstName, lastName, password);
		// }

	}
}
