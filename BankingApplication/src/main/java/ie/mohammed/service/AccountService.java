package ie.mohammed.service;

import java.util.List;

import ie.mohammed.model.Account;

public interface AccountService {

	
	List<Account> findAllAccount();
	void depositMoney(int accountNumber, double amount);
	void withdrawMoney(int accountNumber, double amount);
	
	Account findByAccountNumber(int accountNumber);
	Account findByAccountID(int accountId);
	int totalNumberOfAccount();
	void saveAnAccount(int accountNumber, double balance);
	void transferMoney();	
	void closeAnAccount();
	void displayAccountDetails();
}
