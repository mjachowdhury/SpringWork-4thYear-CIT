package ie.mohammed.service;

import java.util.List;

import ie.mohammed.model.Account;

public interface AccountService {

	
	List<Account> findAllAccount();
	void depositMoney(int accountNumber, double amount);
	public void depositMoneyById(int Id, double amount);
	
	
	void withdrawMoney(int accountNumber, double amount);
	
	Account findByAccountNumber(int accountNumber);
	Account findByAccountID(int accountId);
	int totalNumberOfAccount();
	void saveAnAccount(int accountNumber, double amount,double overDraft);
	void transferMoney();	
	void closeAnAccount();
	void displayAccountDetails();
	void displayAccountDetails(int accountNumber);
	public void displayAccountDetailsById(int accountId);
}
