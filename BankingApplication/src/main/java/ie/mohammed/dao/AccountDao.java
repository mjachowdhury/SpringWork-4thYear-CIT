package ie.mohammed.dao;

import java.util.List;

import ie.mohammed.model.Account;
 
public interface AccountDao {
	
	int getAccountCount();
	List<Account> findAll();
	Account findByAccountNumber(int accountNumber);
	Account findByAccountID(int accountId);
	
	void createAnAccount(int accountNumber, double balance);
	void transferMoney();
	void depositMoney(int accountNumber, double amount);
	void withdrawMoney(int accountNumber, double amount);
	void closeAnAccount();
	void displayAccountDetails();
	
}
