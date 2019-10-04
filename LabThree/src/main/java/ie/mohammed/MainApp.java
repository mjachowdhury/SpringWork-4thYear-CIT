package ie.mohammed;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ie.mohammed.com.Hero;
import ie.mohammed.com.Power;

public class MainApp {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beansByType.xml");
		
		Power hammer = (Power) context.getBean("hammer");
		System.out.println(hammer);
		
		Power lasso = (Power) context.getBean("lasso");
		System.out.println(lasso);
		
		Hero thor = (Hero) context.getBean("thor");
		System.out.println(thor);
		System.out.println();
		
		Hero hulk = (Hero) context.getBean("hulk");
		System.out.println(hulk);
		System.out.println();
	}

}
