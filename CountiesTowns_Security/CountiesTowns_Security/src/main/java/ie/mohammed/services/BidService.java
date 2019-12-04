package ie.mohammed.services;
import java.util.List;

import org.springframework.data.repository.query.Param;

import ie.mohammed.entities.Bid;
import ie.mohammed.entities.Job;

public interface BidService {
	List<Bid> findBidsAlphabticalOrder();
	//List<Bid> getBidsWithSameName(String bidName);
	List<Bid> findBidByBidAmount(Double bidAmount);
	Bid findBidById(int bidId);
	Bid save(Bid bid);
	String saveBid (Bid bid);
	Bid findBid(int bidId);
	List<Bid> findByJob_JobId(int jobId);
	Integer findLowestAmountByJobId(int id);
	List<Bid> listAllBids();
	 
}
