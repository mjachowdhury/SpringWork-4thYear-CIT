package ie.mohammed;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ie.mohammed.entities.Bid;
import ie.mohammed.services.BidService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SchedulingTasks {
	
	@Autowired
	BidService bidService;
	
	// At 14:56 every day....
	@Scheduled(cron = "0 56 14 * * *")
	public void closeBids()
	{
		log.info("Hello it is time for tea");
	}
	
	// Every 50000ms print the list of towns
	//@Scheduled(fixedRate = 50000)
	/*public void listBids() {
		List<Bid> bids = bidService.findBidsAlphabticalOrder();
		for (Bid t: bids)
			log.info(t.getBidAmount());
	}*/
}
