
import static org.junit.Assert.assertEquals;
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
import ie.mohammed.model.Account;
import ie.mohammed.service.impl.AccountServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class TestingMockito {

	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

	@Mock
	Account account;

	@InjectMocks
	AccountServiceImpl accountServiceImpl = (AccountServiceImpl) context.getBean("accountServiceImpl");

	@BeforeEach
	public void setMockOutput() {
		when(account.getAccountId()).thenReturn(1);
	}

	@DisplayName("Test mock custoemr account ID")
	@Test
	public void accountShouldDisplayAccountId() {
		assertEquals(1, accountServiceImpl.findByAccountID(1).getAccountId());
		context.close();
	}

}
