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



/*
 
  @Service
@EnableScheduling
public class Scheduler {

    @Autowired
    private LotRepository lotRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserRepository userRepository;


    //Every day Removal of all lots ending on that day in 00:00 
    // Add EndPaymentDay as throw 2 days
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteEndLots() {
        Date curTime = new Date();
        DateFormat dtfrm = DateFormat.getDateInstance();
        String endDate = dtfrm.format(curTime);

        List<Lot> lots = new ArrayList<>();
        lotRepository.findByEndDate(endDate)
                .forEach(lots::add);
        lots.stream()
                .peek((e)
                        -> {
                        Purchase purchase = new Purchase(e);
                        Calendar cal = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                        String nowDate = sdf.format(cal.getTime());
                            try {
                                Date d = sdf.parse(nowDate);
                                long msdate = d.getTime();
                                long twoday = 2*86400000L;

                                Date ms = new Date(msdate+twoday);
                                purchase.setPurchaseDate(sdf.format(ms));

                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }
                        purchaseRepository.save(purchase);

                        }
                )
                .forEach(lotRepository :: delete);

    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkPurchases(){
        Date curTime = new Date();
        DateFormat dtfrm = DateFormat.getDateInstance();
        String endDate = dtfrm.format(curTime);
        List<Purchase> purchases = new ArrayList<>();
        purchaseRepository.findByPurchaseDate(endDate)
                .forEach(purchases::add);
        for(Purchase purchase: purchases){
            if(purchase.getStatus().equals("NOT PAID")){
                purchase.setStatus("CLOSED");
                purchase.getBuyer().addPenalty(0.2*purchase.getPrice());//Add penalty
                userRepository.save(purchase.getBuyer());
                purchaseRepository.save(purchase);
            }
        }
    }
}
 
*/