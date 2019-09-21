package ie.mohammed;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ie.mohammed.com.Hero;

public class MainApp {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		
		Hero Hero = (Hero) context.getBean("HeroOne");
		System.out.println(Hero);
		
		Hero HeroTwo = (Hero) context.getBean("HeroTwo");
		System.out.println(HeroTwo);
		
		Hero wounderWomen = (Hero) context.getBean("WounderWomen");
		System.out.println(wounderWomen);

	}

}
