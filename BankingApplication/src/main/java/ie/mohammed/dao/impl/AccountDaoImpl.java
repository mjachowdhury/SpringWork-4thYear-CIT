package ie.mohammed.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
		
		String sql = "UPDATE account SET amount=amount+? WHERE accountNumber =?";
		jdbcTemplate.update(sql, amount,accountNumber );
		return;
	}

	public void depositMoneyById(int Id, int accountNumber, double amount) {
 
		String balance = "INSERT INTO account(accountId,accountNumber, amount) VALUES (?,?,? )";
		jdbcTemplate.update(balance, new Object[] {Id, accountNumber, amount});
		/*
		 * double newBalance = 0; String sql = "SELECT * FROM account WHERE accountId='"
		 * + Id + "'"; List<Account> accounts = jdbcTemplate.query(sql, new
		 * AccountRowMapper()); for (Account acc : accounts) { newBalance =
		 * acc.getAmount() + amount; } jdbcTemplate.update(sql, Id, newBalance);
		 */
		 
	}
	
	
	public void withdrawMoney(int accountNumber, double amount) {

		String sql = "UPDATE account SET amount=amount-? WHERE accountNumber =?";
		jdbcTemplate.update(sql, amount,accountNumber );
		return;
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

	public void displayAccountDetails() {
		String sql = "SELECT * FROM account";
		List<Account> accounts = jdbcTemplate.query(sql, new AccountRowMapper());
		for (Account account : accounts) {
			System.out.println("Account Number : " + account.getAccountNumber() + "Balance : " + account.getAmount());
		}
	}

	public void displayAccountDetails(int accountNumber) {
		String sql = "SELECT * FROM account WHERE accountNumber=?";
		List<Account> account = jdbcTemplate.query(sql, new AccountRowMapper(), accountNumber);
		for (Account acc : account) {
			System.out.println("Account Number: "+acc.getAccountNumber()+ "Account ID :  " + acc.getAccountId() + "Balance : " + acc.getAmount()+ "Over Draft :" + acc.getOverDraft());
		}
	}
	
	public void displayAccountDetailsById(int accountId) {
		String sql = "SELECT * FROM account WHERE accountId=?";
		List<Account> account = jdbcTemplate.query(sql, new AccountRowMapper(), accountId);
		for (Account acc : account) {
			System.out.println("Account Number: "+acc.getAccountNumber()+ "Account ID :  " + acc.getAccountId() + "Balance : " + acc.getAmount()+ "Over Draft :" + acc.getOverDraft());
		}
	}
	public void createAnAccount(int accountNumber, double amount, double overDraft) {
		String sql = "INSERT INTO account(accountNumber,amount, overDraft) VALUES (?,?,? )";
		jdbcTemplate.update(sql, accountNumber, amount, overDraft);

	}

	public void transferMoney() {
		// TODO Auto-generated method stub

	}

	

	public void closeAnAccount(int accountId) {
		 String sql = "DELETE FROM account where accountId=?";
		 try {
				jdbcTemplate.update(sql, accountId);				 
			}catch(EmptyResultDataAccessException e){
			 e.printStackTrace();
			}
	}

	public boolean accountExists(int accountNumber) {
		String sql = "SELECT * FROM account WHERE accountNumber=?";
		try {
			jdbcTemplate.queryForObject(sql, new Object[] {accountNumber}, Integer.class);
			return true;
		}catch(EmptyResultDataAccessException e){
			return false;
		}
	}

	public List<Account> findAccountWithCustomer(int customerId) {
		String sql = "SELECT * FROM account JOIN customer ON account.accountId=customer.customerId AND customer.customerId=?";
		List<Account> accounts = jdbcTemplate.query(sql, new AccountRowMapper(), customerId);
		return accounts;
	}

	
}
