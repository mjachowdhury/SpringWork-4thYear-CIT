package ie.mohammed;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ie.mohammed.com.Franchise;
import ie.mohammed.com.Hero;

public class MainApp {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);

		Hero heroOne = (Hero) context.getBean("wonderWoman");
		System.out.println(heroOne);

		System.out.println();

		Hero heroTwo = (Hero) context.getBean("thor");
		System.out.println(heroTwo);
		
		System.out.println();
		
		Hero heroThree = (Hero) context.getBean("ironMan");
		System.out.println(heroThree);
		
		Franchise franchise = (Franchise) context.getBean("marvel");
		franchise.display();
		context.close();

	}

}
