/**
 * 
 */
package ie.mohammed.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mohammed
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Customer {
	private int customerId; 
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String contactNumber;
	private String email;
	
	private String savingAccount;
	
	private List<Account> accounts = new ArrayList<Account>();
	
	public void setSavingAccounts(String SA) {
		Account tempSavingAcc = new Account(SA);
		tempSavingAcc.setCustomer(this);
		accounts.add(tempSavingAcc);
		this.savingAccount = SA;
	}
	
	 
	@Override
	public String toString() {
		return "\n"+
				"Customer ID : " + customerId + "\n"+
				"First Name : " + firstName + "\n"+
				"Last Name : " + lastName + "\n" +
				"Address : " + address + "\n"+
				"City : " + city + "\n"+
				"Contact Number : " + contactNumber + "\n"+
				"Email : " + email + "\n";
	}

	
}
