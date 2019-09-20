package ie.mohammed;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ie.mohammed.com.Power;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		Power hammer = (Power) context.getBean("hammer");
		System.out.println(hammer);
		
		Power strenght = (Power) context.getBean("strenght");
		System.out.println(strenght);
		
		Power lasso = (Power) context.getBean("lasso");
		System.out.println(lasso);
		
		Power ring = (Power) context.getBean("ring");
		System.out.println(ring);
	}

}
