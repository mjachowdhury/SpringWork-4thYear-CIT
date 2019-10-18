package ie.mohammed.dao;

import java.util.List;

import ie.mohammed.model.Account;
 
public interface AccountDao {
	
	void depositMoney(int accountNumber, double amount);
	void withdrawMoney(int accountNumber, double amount);
	List<Account> findAllAccount();
	
	int getAccountCount();	
	Account findByAccountNumber(int accountNumber);
	Account findByAccountID(int accountId);	
	void createAnAccount(int accountNumber, double balance);
	void transferMoney();	
	void closeAnAccount();
	void displayAccountDetails();
	
}
