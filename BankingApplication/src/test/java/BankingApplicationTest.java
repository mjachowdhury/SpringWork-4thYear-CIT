import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ie.mohammed.dao.AccountDao;
import ie.mohammed.dao.CustomerDao;
import ie.mohammed.dao.EmployeeDao;
import ie.mohammed.model.Customer;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
class BankingApplicationTest {

	@Autowired
	CustomerDao customerDao;
	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	AccountDao accountDao;

	@Test
	public void testFindCustomerName() {
		Customer cus = customerDao.findById(1);
		Assert.assertEquals("Alom", cus.getLastName());
	}

	@Test
	public void testCountingTotalCustomer() {
		int count = customerDao.getCustomerCount();
		Assert.assertEquals(4, count);
	}

	@Test
	public void testEmployeeInsert() {
		int numberOfEmployee = employeeDao.getEmployeeCount();
		employeeDao.insertANewEmployee("Hassan", "Sardar", "123456");
		int itShouldBe = numberOfEmployee + 1;
		numberOfEmployee = employeeDao.getEmployeeCount();
		Assert.assertEquals(itShouldBe, numberOfEmployee);

	}

	@Test
	public void testFindAllCustomer() {
		int numberOfCustomer = customerDao.getCustomerCount();
		Assert.assertEquals(4, numberOfCustomer);
	}
	
	@Test
	public void testFindAllAccount() {
		int numberOfAccount = accountDao.getAccountCount();
		Assert.assertEquals(4, numberOfAccount);
	}
	

}
