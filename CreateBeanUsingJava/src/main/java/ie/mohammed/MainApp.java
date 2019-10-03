package ie.mohammed;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ie.mohammed.com.Hero;

public class MainApp {

	public static void main(String[] args) {
		 AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		 
		 Hero w = (Hero) context.getBean("wonderWoman");
		 System.out.println(w);
	}

}
