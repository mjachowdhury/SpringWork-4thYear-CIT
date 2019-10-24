 
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ie.mohammed.dao.AccountDao;
import ie.mohammed.dao.impl.AccountDaoImpl;
import ie.mohammed.model.Account;
import ie.mohammed.model.Customer;
import ie.mohammed.service.impl.AccountServiceImpl;
import ie.mohammed.service.impl.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class TestingMockito {

	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	
	/*
	 * @Mock AccountDaoImpl accountDao;
	 * 
	 * @Mock AccountServiceImpl acountService;
	 */
	@Mock
	Account account;
	@Mock
	Customer customer;
	
	@InjectMocks
	AccountServiceImpl accountSer = (AccountServiceImpl) context.getBean("accountServiceImpl");
	
	
	  @InjectMocks CustomerServiceImpl customerSer = (CustomerServiceImpl)
	  context.getBean("customerServiceImpl");
	 
	@BeforeEach
	public void setMockOutput() {
		when(account.getAccountId()).thenReturn(1);
	}
	
	
	  @BeforeEach public void setMockOutputCustomerName() {
	  when(customer.getCustomerId()).thenReturn(1); }
	 
	
	@DisplayName("Test mock custoemr account ID")
	@Test
	public void accountShouldDisplayAccountId() {
		 assertEquals(1, accountSer.findByAccountID(1).getAccountId());
		//return accountNumb;
	}
	
	
	  @DisplayName("Test mock custoemr Id")
	  
	  @Test public void customerID() { assertEquals(1,
	  customerSer.findById(1).getCustomerId()); //return accountNumb; 
	  }
	 
	
	
}
