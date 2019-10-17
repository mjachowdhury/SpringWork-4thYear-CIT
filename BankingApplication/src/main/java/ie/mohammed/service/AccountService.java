package ie.mohammed.service;

import java.util.List;

import ie.mohammed.model.Account;

public interface AccountService {

	int totalNumberOfAccount();
	List<Account> findAllAccount();
	Account findByAccountNumber(int accountNumber);
	Account findByAccountID(int accountId);
	
	void saveAnAccount(int accountNumber, double balance);
	void transferMoney();
	void depositMoney(int accountNumber, double amount);
	void withdrawMoney(int accountNumber, double amount);
	void closeAnAccount();
	void displayAccountDetails();
}
