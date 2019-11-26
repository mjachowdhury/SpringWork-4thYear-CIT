package ie.mohammed.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.mohammed.dao.BidDao;
import ie.mohammed.entities.Bid;

@Service
public class BidServiceImplementation implements BidService{

	@Autowired
	private BidDao bidDao;
	
	/*
	 * @Override public List<Bid> findBidsAlphabticalOrder() { return
	 * bidDao.findAllByOrderByBidNameAsc(); }
	 */

	/*
	 * @Override public List<Bid> getBidsWithSameName(String bidName) { return
	 * bidDao.getByBidNameIgnoringCase(bidName); }
	 */

	@Override
	public Bid findBidById(int bidId) {
		if (bidDao.existsById(bidId))
			return bidDao.findById(bidId).get();
		return null;
	}

	@Override
	public Bid save(Bid bid) {
		if (bidDao.existsByBidAmountAndJob_JobId(bid.getBidAmount(), bid.getJob().getJobId()))
			return null;
		return bidDao.save(bid);	
	}

	/*
	 * @Override public List<Bid> findBidByBidName(String bidName) { return
	 * bidDao.findBidByBidName(bidName); }
	 */
	
}
