package ie.mohammed;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ie.mohammed.com.Franchise;
import ie.mohammed.com.Hero;
import ie.mohammed.com.Power;

public class MainApp {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		
		Power hammer = (Power) context.getBean("hammer");
		System.out.println(hammer);

		Power strenght = (Power) context.getBean("strength");
		System.out.println(strenght);

		Power lasso = (Power) context.getBean("lasso");
		System.out.println(lasso);

		Power ring = (Power) context.getBean("ring");
		System.out.println(ring);
		
		System.out.println();
		
		Hero wounderWomen = (Hero) context.getBean("wonderWoman");
		System.out.println(wounderWomen);
		System.out.println();
		
		Hero thor = (Hero) context.getBean("thor");
		System.out.println(thor);
		System.out.println();
		
		Hero hulk = (Hero) context.getBean("hulk");
		System.out.println(hulk);
		System.out.println();
		
		Hero greenLantern = (Hero) context.getBean("greenLantern");
		System.out.println(greenLantern);
		System.out.println();
		
		Hero ironMan = (Hero) context.getBean("ironMan");
		System.out.println(ironMan);

		Franchise franchise = (Franchise) context.getBean("dc");
		franchise.display();
	}

}
