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

	public Account findByAccountID(int accountId) {
		return accountDao.findByAccountID(accountId);
	}

	public void saveAnAccount(int accountNumber, double balance) {

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

}
