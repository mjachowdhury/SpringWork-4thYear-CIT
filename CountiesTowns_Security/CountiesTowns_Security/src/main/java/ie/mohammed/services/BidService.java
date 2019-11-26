package ie.mohammed.services;
import java.util.List;

import ie.mohammed.entities.Bid;

public interface BidService {
	List<Bid> findBidsAlphabticalOrder();
	//List<Bid> getBidsWithSameName(String bidName);
	List<Bid> findBidByBidAmount(Double bidAmount);
	Bid findBidById(int bidId);
	Bid save(Bid bid);
	Bid findBid(int bidId);
}
