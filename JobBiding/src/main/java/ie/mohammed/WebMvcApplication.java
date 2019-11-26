package ie.mohammed;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import ie.mohammed.entities.County;
import ie.mohammed.entities.MyApiUser;
import ie.mohammed.entities.MyUser;
import ie.mohammed.entities.Role;
import ie.mohammed.entities.Town;
import ie.mohammed.services.CountyService;
import ie.mohammed.services.MyApiUserService;
import ie.mohammed.services.MyUserService;
import ie.mohammed.services.RoleService;
import ie.mohammed.services.TownService;

@SpringBootApplication
@EnableScheduling
public class WebMvcApplication  {

	public static void main(String[] args) {
		SpringApplication.run(WebMvcApplication.class, args);
	}

	@Autowired
	private MyUserService myUserService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired 
	private MyApiUserService myApiUserService;

	@Autowired
	private CountyService countyService;
	
	@Autowired
	private TownService townService;
	
	@PostConstruct	 // Another new annotation for you...
	public void loadData() {
		Role role1 = new Role("mickey.mouse@cit.ie", "ROLE_USER");
		Role role2 = new Role("minnie.mouse@cit.ie", "ROLE_API");
		Role role3 = new Role("donald.duck@cit.ie", "ROLE_ADMIN");
		roleService.save(role1);
		roleService.save(role2);
		roleService.save(role3);

        MyUser user1 = new MyUser("mickey.mouse@cit.ie", passwordEncoder.encode("password"), role1,true, "Minnie", "Mouse");
		MyApiUser user2 = new MyApiUser("minnie.mouse@cit.ie", passwordEncoder.encode("password"), role2, true);
		MyUser user3 = new MyUser("donald.duck@cit.ie", passwordEncoder.encode("password"), role3, true, "Donald", "Duck");
		myUserService.save(user1);
		myApiUserService.save(user2);
		myUserService.save(user3);
		
		County dublin = new County("Dublin", user3);		
		countyService.save(dublin);
		County kerry = new County("Kerry", user3);
		countyService.save(kerry);
		
		County cork = new County("Cork", user3);
		countyService.save(cork);
		
		townService.save(new Town("Blackrock", cork));
		townService.save(new Town("Wilton", cork));
		townService.save(new Town("Bishopstown", cork));
		townService.save(new Town("Mayfield", cork));
		
		townService.save(new Town("Blackrock", dublin));
		townService.save(new Town("Malahide", dublin));
		townService.save(new Town("Dundrum", dublin));
		townService.save(new Town("Tallaght", dublin));
		
		townService.save(new Town("Tralee", kerry));
		townService.save(new Town("Ballyferriter", kerry));
		townService.save(new Town("Killarney", kerry));
		townService.save(new Town("Dingle", kerry));
	}
}