package ie.mohammed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ie.mohammed.com.Franchise;
import ie.mohammed.com.FranchiseImplementation;
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
	
	@Bean
	public Power hammer() {
		return new PowerImplementation("Hammer", "Various powers said to be able to level mountains");
	}
	
	@Bean 
	public Power suit() {
		return new PowerImplementation("Suit", "Files, fires lasers, talks with Irish accent");
	}
	
	@Bean
	public Hero thor() {
		return new HeroImplementation("Hammer", hammer());
	}
	
	@Bean
	public Hero ironMan() {
		return new HeroImplementation("Suit", suit());
	}
	
	 
	
	@Bean
	public Franchise marvel(){
		List<Hero> marvelHeros = new ArrayList<Hero>();
		marvelHeros.add(thor());
		marvelHeros.add(ironMan());
		return new FranchiseImplementation(marvelHeros,"Avengers");
		
	}
	
	 

	
}
