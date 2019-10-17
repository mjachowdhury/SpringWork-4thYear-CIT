package ie.mohammed.model;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Account {
	
	private int accountId;
	private int accountNumber;
	private double amount = 0;
	
	@Autowired
	private Customer customer;
	
	@Override
	public String toString() {
		return "\n"+
				"Account ID : " + accountId +"\n"+
				"Account Number : " + accountNumber+ "\n"+ 
				"Account Balance : " + amount + "\n";
	}
	
	 
	
	
	
}
