package ie.mohammed;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ie.mohammed.com.Hero;
import ie.mohammed.com.HeroImplementation;
import ie.mohammed.com.Power;
import ie.mohammed.com.PowerImplementation;

@Configuration
public class BeanConfig {
	//creating the bean
	@Bean
	public Power lasso() {
		return new PowerImplementation("lasso", "Forces the person around whom it is wrapped to tellthe truth");
	}
	//Injecting lasso bean inside the hero bean
	@Bean 
	public Hero wonderWoman() {
		return new HeroImplementation("Wonder Woman", lasso());
	}

}
