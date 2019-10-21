 
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ie.mohammed.dao.AccountDao;
import ie.mohammed.dao.impl.AccountDaoImpl;


@ExtendWith(MockitoExtension.class)
public class TestingMockito {

	@Mock
	AccountDaoImpl accountDao;

	@Test
	public void accountShouldDisplay(int accountId) {
		accountDao.findByAccountID(accountId); // verify(accountDao, true).toString();
	}
}
