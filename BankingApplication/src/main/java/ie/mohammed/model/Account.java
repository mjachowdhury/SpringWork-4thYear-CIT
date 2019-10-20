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
	
	private int accountNumber;
	private int accountId;
	private int customerId;
	private double amount = 0;
	private double overDraft = 0;
	
	@Autowired
	private Customer customer;
	
	@Override
	public String toString() {
		return "\n"+
				"Account ID : " + accountId + "\n"+
				"Customer ID : " + customerId + "\n"+ 
				"Account Number : " + accountNumber +"\n"+				
				"Account Balance : " + amount + "\n"+
				"Over Draft : " + overDraft + "\n";
	}

	public Account(int accountNumber) {
		this.accountNumber = accountNumber;
	}
  }
