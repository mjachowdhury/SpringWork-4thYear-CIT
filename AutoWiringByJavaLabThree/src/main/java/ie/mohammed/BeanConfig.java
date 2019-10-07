package ie.mohammed;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ie.mohammed.com.Hero;
import ie.mohammed.com.HeroImplementation;
import ie.mohammed.com.Power;
import ie.mohammed.com.PowerImplementation;

@Configuration
public class BeanConfig {
	
	@Bean
	public Power lasso() {
		return new PowerImplementation("lasso", "Forces the person around whom it is wrapped to tell the truth");
	}
	
	@Bean
	
	public Hero wonderWoman() {
		return new HeroImplementation("Wonder Woman");
	}

	@Bean
	public Hero hulk() {
		return new HeroImplementation("Hulk");
	}
	
	@Bean
	@Qualifier("hammer")
	public Hero hammer() {
		return new HeroImplementation("Hammer");
	}
}
