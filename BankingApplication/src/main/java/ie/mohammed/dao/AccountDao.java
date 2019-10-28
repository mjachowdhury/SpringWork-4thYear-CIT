package ie.mohammed.dao;

import java.util.List;
import ie.mohammed.model.Account;

public interface AccountDao {

	void depositMoney(int accountNumber, double amount);

	public void depositMoneyById(int Id, int accountNumber, double amount);

	void withdrawMoney(int accountNumber, double amount);

	List<Account> findAllAccount();

	List<Account> findAccountWithCustomer(int customerId);

	List<Account> findAccountsGreaterThan10000();

	int getAccountCount();

	Account findByAccountNumber(int accountNumber);

	Account findByAccountID(int accountId);

	void createAnAccount(int accountNumber, int customerId, double amount, double overDraft);

	void closeAnAccount(int accountId);

	void displayAccountDetails();

	void displayAccountDetails(int accountNumber);

	public void displayAccountDetailsById(int accountId);

	public boolean accountExists(int accountNumber);

	public double totalAmonut();

	void TranferMoneyToAnotherAccount(int accountId, int transferAccountId, double amount);

	void addAPersonToAccount(int accountId, int custId);
}
