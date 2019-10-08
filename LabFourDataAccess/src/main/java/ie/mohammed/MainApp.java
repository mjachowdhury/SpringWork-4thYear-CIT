package ie.mohammed;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ie.mohammed.domain.Power;
import ie.mohammed.services.PowerService;

public class MainApp {

	public static void main(String[] args) {
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		 PowerService powerService = (PowerService) context.getBean("powerServiceImpl");
	
		 System.out.println("There are " + powerService.CountThePowers() + "powers in the database");
		 
		 System.out.println("There are " + powerService.CountPowersStartingWithLetter('l') + "powers in the database starting with L.");
		 
		 List<Power> powers = powerService.findAllPowers();
		 	for(Power power: powers)
		 			System.out.println(power);
		 	
		 System.out.println(powerService.CountThePowers());

		 powerService.generateReport("powers.txt");
	}

}
