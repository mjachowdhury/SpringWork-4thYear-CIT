package ie.mohammed;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ie.mohammed.com.Hero;
import ie.mohammed.com.Power;

public class MainApp {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beansByType.xml");
		ClassPathXmlApplicationContext contextByName = new ClassPathXmlApplicationContext("beansByName.xml");
		
		
		  System.out.println("Bean Autowiring by type\n"); Power hammer = (Power)
		  context.getBean("hammer"); System.out.println(hammer);
		  
		  Hero thor = (Hero) context.getBean("thor"); System.out.println(thor);
		  System.out.println();
		  
		  Hero hulk = (Hero) context.getBean("hulk"); System.out.println(hulk);
		  System.out.println();
		 
		
		System.out.println("Beans Autowiring by name\n");
		
		Power hammerByName = (Power) contextByName.getBean("hammer");
		System.out.println(hammerByName);
		
		Hero thorByName = (Hero) contextByName.getBean("thor");
		System.out.println(thorByName);
		System.out.println();
		
	}

}
