package ie.mohammed.model.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ie.mohammed.model.Account;

public class AccountRowMapper implements RowMapper<Account>	{

	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Account account = new Account();
		account.setAccountId(rs.getInt("accountId"));
		account.setAccountNumber(rs.getInt("accountNumber"));
		account.setAmount(rs.getDouble("amount"));		
		return account;
	}

}
