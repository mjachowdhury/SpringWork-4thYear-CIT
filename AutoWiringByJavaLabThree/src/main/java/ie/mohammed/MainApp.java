package ie.mohammed;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ie.mohammed.com.Hero;

public class MainApp {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		
		Hero one =(Hero) context.getBean("wonderWoman");
		System.out.println(one);
		
		System.out.println();
		
		Hero two = (Hero) context.getBean("hulk");
		System.out.println(two);
		
		Hero three =(Hero) context.getBean("hammer");
		System.out.println(three);
		context.close();
	}

}
