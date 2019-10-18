package ie.mohammed.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ie.mohammed.dao.AccountDao;
import ie.mohammed.model.Account;
import ie.mohammed.model.Customer;
import ie.mohammed.model.rowMappers.AccountRowMapper;

@Repository
public class AccountDaoImpl implements AccountDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	Customer customer;
	
	public List<Account> findAllAccount() {
		String sql = "SELECT * FROM account";
		List<Account> accounts = jdbcTemplate.query(sql, new AccountRowMapper());
		return accounts;
	}
	
	public void depositMoney(int accountNumber, double amount) {
		double newBalance = 0;
		String sql = "SELECT * FROM account WHERE accountNumber='" + accountNumber + "'";
		List<Account> accounts = jdbcTemplate.query(sql, new AccountRowMapper());
		for (Account acc : accounts) {
			newBalance = acc.getAmount() + amount;
		}
		jdbcTemplate.update(sql, accountNumber, newBalance);
	}

	public void withdrawMoney(int accountNumber, double amount) {
		double newBalance = 0;
		String sql = "SELECT * FROM account WHERE accountNumber='" + accountNumber + "'";
		List<Account> accounts = jdbcTemplate.query(sql, new AccountRowMapper());
		for (Account acc : accounts) {
			newBalance = acc.getAmount() - amount;
		}
		jdbcTemplate.update(sql, accountNumber, newBalance);
	}
	
	public int getAccountCount() {
		String sql = "SELECT COUNT (*) FROM account";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public Account findByAccountNumber(int accountNumber) {
		String sql = "SELECT * FROM account WHERE accountNumber=?";
		Account account = jdbcTemplate.queryForObject(sql, new AccountRowMapper(), accountNumber);
		return account;
	}

	public Account findByAccountID(int accountId) {
		String sql = "SELECT * FROM account WHERE accountId=?";
		Account account = jdbcTemplate.queryForObject(sql, new AccountRowMapper(), accountId);
		return account;
	}

	public void createAnAccount(int accountNumber, double balance) {
		String sql = "INSERT INTO account(accountNumber,balance) VALUES (?,? )";
		jdbcTemplate.update(sql, accountNumber, balance);

	}

	public void transferMoney() {
		// TODO Auto-generated method stub

	}

	

	public void closeAnAccount() {
		// TODO Auto-generated method stub

	}

	public void displayAccountDetails() {
		String sql = "SELECT * FROM account";
		List<Account> accounts = jdbcTemplate.query(sql, new AccountRowMapper());
		for (Account account : accounts) {
			System.out.println("Account Number : " + account.getAccountNumber() + "Balance : " + account.getAmount());
		}
	}
}
