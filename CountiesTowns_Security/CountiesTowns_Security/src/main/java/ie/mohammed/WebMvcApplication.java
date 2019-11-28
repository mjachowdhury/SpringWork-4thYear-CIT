package ie.mohammed;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import ie.mohammed.entities.Job;
import ie.mohammed.entities.MyApiUser;
import ie.mohammed.entities.MyUser;
import ie.mohammed.entities.Role;
import ie.mohammed.entities.Bid;
import ie.mohammed.services.JobService;
import ie.mohammed.services.MyApiUserService;
import ie.mohammed.services.MyUserService;
import ie.mohammed.services.RoleService;
import ie.mohammed.services.BidService;

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
	private JobService jobService;
	
	@Autowired
	private BidService bidService;
	
	@PostConstruct	 // Another new annotation for you...
	public void loadData() {
		LocalDate localeDate = null;
		Role role1 = new Role("user.one@cit.ie", "ROLE_USER");
		Role role2 = new Role("user.two@cit.ie", "ROLE_USER");
		Role role3 = new Role("user.three@cit.ie", "ROLE_USER");
		
		Role role4 = new Role("api.user@cit.ie", "ROLE_API");
		
		Role role5 = new Role("admin.user@cit.ie", "ROLE_ADMIN");
		
		roleService.save(role1);
		roleService.save(role2);
		roleService.save(role3);
		
		roleService.save(role4);
		
		roleService.save(role5);

        MyUser user1 = new MyUser("user.one@cit.ie", passwordEncoder.encode("password"), role1,true, "User", "One");
        MyUser user2 = new MyUser("user.two@cit.ie", passwordEncoder.encode("password"), role1,true, "User", "Two");
        MyUser user3 = new MyUser("user.three@cit.ie", passwordEncoder.encode("password"), role1,true, "User", "Three");
        
		MyApiUser apiUser = new MyApiUser("api.user@cit.ie", passwordEncoder.encode("password"), role2, true);	
		MyUser admin = new MyUser("admin.user@cit.ie", passwordEncoder.encode("password"), role3, true, "Admin", "User");
		
		myUserService.save(user1);
		myUserService.save(user2);
		myUserService.save(user3);
		
		myApiUserService.save(apiUser);
		myUserService.save(admin);
		
		Job painting = new Job("Painting", "Have to be perfect",localeDate.now(),1,user1);		
		jobService.save(painting);
		Job cookerInstall = new Job("Cooker Install", "Have to be perfect",localeDate.now(),0,user1);
		jobService.save(cookerInstall);
		Job plumbing = new Job("Plumbing", "Have to be perfect",localeDate.now(),1,user1);
		jobService.save(plumbing);
		
		
		bidService.save(new Bid(200.00, plumbing, user1.getUserEmail()));
		bidService.save(new Bid(300.00, plumbing, user1.getUserEmail()));
		bidService.save(new Bid(100.00, plumbing, user1.getUserEmail()));
		bidService.save(new Bid(200.00, plumbing, user1.getUserEmail()));
		 
		bidService.save(new Bid(200.00, cookerInstall, user2.getUserEmail()));
		bidService.save(new Bid(300.00, cookerInstall, user2.getUserEmail()));
		bidService.save(new Bid(100.00, cookerInstall, user2.getUserEmail()));
		bidService.save(new Bid(200.00, cookerInstall, user2.getUserEmail()));
		
		bidService.save(new Bid(200.00, painting, user3.getUserEmail()));
		bidService.save(new Bid(300.00, painting, user3.getUserEmail()));
		bidService.save(new Bid(100.00, painting, user3.getUserEmail()));
		bidService.save(new Bid(200.00, painting, user3.getUserEmail()));
		/*
		 * bidService.save(new Bid("Blackrock", painting)); bidService.save(new
		 * Bid("Malahide", painting)); bidService.save(new Bid("Dundrum", painting));
		 * bidService.save(new Bid("Tallaght", painting));
		 * 
		 * bidService.save(new Bid("Tralee", cookerInstall)); bidService.save(new
		 * Bid("Ballyferriter", cookerInstall)); bidService.save(new Bid("Killarney",
		 * cookerInstall)); bidService.save(new Bid("Dingle", cookerInstall));
		 */
	}
}