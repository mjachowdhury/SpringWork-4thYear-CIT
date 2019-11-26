package ie.mohammed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import ie.mohammed.model.Auction;
import ie.mohammed.model.Category;
import ie.mohammed.model.Job;
import ie.mohammed.model.User;
import ie.mohammed.services.AuctionService;
import ie.mohammed.services.BidService;
import ie.mohammed.services.CategoryService;
import ie.mohammed.services.JobService;
import ie.mohammed.services.UserRoleService;
import ie.mohammed.services.UserService;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class DataLoader implements ApplicationRunner{

	/*
	 * @Autowired AuctionService auctionService;
	 * 
	 * @Autowired BidService bidService;
	 * 
	 *  
	 * 
	 */
	@Autowired 
	JobService jobService;
	
	@Autowired 
	CategoryService categoryService;
	 
	@Autowired
	UserService userService;
	
	LocalDateTime dateTime = LocalDateTime.now();
	/*
	 * @Autowired UserRoleService userRoleService;
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		//Auction auction = new Auction(2019-10-25 10:10:10, "kk ff", 2019-11-10 10:10:10,false, 1, "hhh");
		User user = new User("mohammed","mohammed", true);
		userService.save(user);
		
		Category category = new Category("Kitchen Fitting");
		categoryService.save(category);
		
		Job job = new Job("Full Kitchen Fit", "Kitchen Fit",500.00, category);
		jobService.save(job);
		
		//Auction auction = new Auction(Date create_time, "Kitchen Fit", Date end_time, false, 1, user);
	}

}
