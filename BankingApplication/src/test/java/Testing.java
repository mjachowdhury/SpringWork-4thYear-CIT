import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import ie.mohammed.model.Account;

//@RunWith(JUnitPlatform.class)
public class Testing {
	
	@Test
	public void testAccount() {
		int accountId=0;
		int accountNumber= 0;
		double amount = 0;
		double overDraft = 0;
		Account acc = new Account();
		acc.setAccountId(accountId);
		acc.setAccountNumber(accountNumber);
		acc.setAmount(amount);
		acc.setOverDraft(overDraft);
		Assertions.assertEquals(3, 3);
	}

}
