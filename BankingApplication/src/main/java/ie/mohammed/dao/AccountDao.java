package ie.mohammed.dao;

import java.util.List;

import ie.mohammed.model.Account;
 
public interface AccountDao {
	
	void depositMoney(int accountNumber, double amount);
	public void depositMoneyById(int Id,int accountNumber, double amount);
	
	void withdrawMoney(int accountNumber, double amount);
	List<Account> findAllAccount();
	List<Account> findAccountWithCustomer(int customerId);
	
	int getAccountCount();	
	Account findByAccountNumber(int accountNumber);
	Account findByAccountID(int accountId);	
	void createAnAccount(int accountNumber, double amount, double overDraft);
	void transferMoney();	
	void closeAnAccount(int accountId);
	void displayAccountDetails();
	void displayAccountDetails(int accountNumber);
	public void displayAccountDetailsById(int accountId);
	public boolean accountExists(int accountNumber);
}
