package ie.mohammed.service;

import java.util.List;

import ie.mohammed.model.Account;

public interface AccountService {

	List<Account> findAllAccount();

	List<Account> findAccountWithCustomer(int customerId);

	List<Account> findAccountsGreaterThan10000();

	void depositMoney(int accountNumber, double amount);

	public void depositMoneyById(int Id, int accountNumber, double amount);

	public boolean accountExists(int accountNumber);

	void withdrawMoney(int accountNumber, double amount);

	Account findByAccountNumber(int accountNumber);

	Account findByAccountID(int accountId);

	int totalNumberOfAccount();

	void saveAnAccount(int accountNumber, int customerId, double amount, double overDraft);

	void transferMoney();

	void closeAnAccount(int accountId);

	void displayAccountDetails();

	void displayAccountDetails(int accountNumber);

	public void displayAccountDetailsById(int accountId);
	
	public double totalAmonut();
}
