package ie.mohammed.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ie.mohammed.model.Account;
import ie.mohammed.model.Customer;

public interface AccountDao {

	void depositMoney(int accountNumber, double amount);

	public void depositMoneyById(int Id, int accountNumber, double amount);

	void withdrawMoney(int accountNumber, double amount);

	List<Account> findAllAccount();

	List<Account> findAccountWithCustomer(int customerId);

	List<Account> findAccountsGreaterThan10000(double amount);

	int getAccountCount();

	Account findByAccountNumber(int accountNumber);

	Account findByAccountID(int accountId);

	void createAnAccount(int accountNumber, int customerId, double amount, double overDraft);

	void transferMoney();

	void closeAnAccount(int accountId);

	void displayAccountDetails();

	void displayAccountDetails(int accountNumber);

	public void displayAccountDetailsById(int accountId);

	public boolean accountExists(int accountNumber);
}
