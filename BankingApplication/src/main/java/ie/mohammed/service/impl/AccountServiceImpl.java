package ie.mohammed.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.mohammed.dao.AccountDao;
import ie.mohammed.model.Account;
import ie.mohammed.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDao accountDao;

	public List<Account> findAllAccount() {
		return accountDao.findAllAccount();
	}

	public void depositMoney(int accountNumber, double amount) {
		accountDao.depositMoney(accountNumber, amount);
	}

	public void withdrawMoney(int accountNumber, double amount) {
		accountDao.withdrawMoney(accountNumber, amount);
	}

	public int totalNumberOfAccount() {
		return accountDao.getAccountCount();
	}

	public Account findByAccountNumber(int accountNumber) {
		return accountDao.findByAccountNumber(accountNumber);
	}

	public Account findByAccountID(int customerId) {
		return accountDao.findByAccountID(customerId);
	}

	public void saveAnAccount(int accountNumber, int customerId, double amount, double overDraft) {
		accountDao.createAnAccount(accountNumber, customerId, amount, overDraft);
	}

	public void closeAnAccount(int accountId) {
		accountDao.closeAnAccount(accountId);
	}

	public List<Account> findAccountWithCustomer(int customerId) {

		return accountDao.findAccountWithCustomer(customerId);
	}

	public List<Account> findAccountsGreaterThan10000() {

		return accountDao.findAccountsGreaterThan10000();
	}

	public double totalAmonut() {
		return accountDao.totalAmonut();
	}

	@Override
	public void TranferMoneyToAnotherAccount(int accountId, int transferAccountId, double amount) {
		accountDao.TranferMoneyToAnotherAccount(accountId, transferAccountId, amount);

	}

	public void displayAccountDetails() {
		accountDao.displayAccountDetails();

	}

	//===============BELOW METHODS DID NOT USE ========================
	
	public void displayAccountDetails(int accountNumber) {
		accountDao.displayAccountDetails(accountNumber);
	}

	public void displayAccountDetailsById(int accountId) {
		accountDao.displayAccountDetailsById(accountId);

	}

	public boolean accountExists(int accountNumber) {
		return accountDao.accountExists(accountNumber);
	}

	public void depositMoneyById(int Id, int accountNumber, double amount) {
		accountDao.depositMoneyById(Id, accountNumber, amount);

	}

	@Override
	public void addAPersonToAccount(int accountId, int custId) {
		accountDao.addAPersonToAccount(accountId, custId);

	}

}
