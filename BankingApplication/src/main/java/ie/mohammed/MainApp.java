package ie.mohammed;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ie.mohammed.service.impl.CustomerServiceImpl;

public class MainApp {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		
		CustomerServiceImpl customerService = (CustomerServiceImpl) context.getBean("customerServiceImpl");
		System.out.println("There are " + customerService.CountTotalCustomer());
		
	}

}
