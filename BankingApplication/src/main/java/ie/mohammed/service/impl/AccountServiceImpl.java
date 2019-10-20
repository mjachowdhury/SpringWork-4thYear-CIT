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
	
	public void depositMoneyById(int Id, double amount) {
		accountDao.depositMoneyById(Id, amount);
		
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

	public void saveAnAccount(int accountNumber, double amount, double overDraft) {
		accountDao.createAnAccount(accountNumber, amount, overDraft);
	}

	public void transferMoney() {
		// TODO Auto-generated method stub

	}

	public void closeAnAccount() {
		// TODO Auto-generated method stub

	}

	public void displayAccountDetails() {
		accountDao.displayAccountDetails();

	}

	public void displayAccountDetails(int accountNumber) {
		accountDao.displayAccountDetails(accountNumber);
	}

	public void displayAccountDetailsById(int accountId) {
		 accountDao.displayAccountDetailsById(accountId);
		
	}

	
}
