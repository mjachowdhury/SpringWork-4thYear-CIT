package ie.mohammed.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Employee {

	private int employeeId; 
	private String firstName;
	private String lastName;
	private String password;
	
	@Override
	public String toString() {
		return "\n"+ 
				"Employee ID :" + employeeId + "\n"+ 
				"First Name :" + firstName + "\n"+ 
				"Last Name :" + lastName + "\n"+ 
				"Password :" + password + "\n";
	}
	
	
}
