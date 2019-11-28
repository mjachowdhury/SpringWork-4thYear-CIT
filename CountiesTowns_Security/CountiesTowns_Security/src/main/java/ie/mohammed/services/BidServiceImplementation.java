package ie.mohammed.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.mohammed.dao.BidDao;
import ie.mohammed.entities.Bid;

@Service
public class BidServiceImplementation implements BidService {

	@Autowired
	private BidDao bidDao;

	@Override
	public List<Bid> findBidsAlphabticalOrder() {
		return bidDao.findAllByOrderByBidAmountAsc();
	}

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
		if (!bid.getUserEmail().equals(bid.getJob().getAddedBy().getUserEmail())) {
			if (findLowestAmountByJobId(bid.getJob().getJobId()) == null) {
				return bidDao.save(bid);
			}
			System.out.println(bid.getBidAmount());
			if (bid.getBidAmount() < bidDao.findLowestAmountByJobId(bid.getJob().getJobId()))
				return bidDao.save(bid);
			System.out.println(bid.getBidAmount());
			return null;
		} else {
			return null;
		}
	}
	/*
	 * if (bidDao.existsByBidAmountAndJob_JobId(bid.getBidAmount(),
	 * bid.getJob().getJobId())) return null; return bidDao.save(bid); }
	 */

	@Override
	public List<Bid> findBidByBidAmount(Double bidAmount) {
		return bidDao.findBidByBidAmount(bidAmount);
	}

	@Override
	public Bid findBid(int bidId) {
		 
		return bidDao.findById(bidId).get();
	}

	@Override
	public List<Bid> findByJob_JobId(int jobId) {
		 
		return bidDao.findByJob_JobId(jobId);
	}

	@Override
	public Integer findLowestAmountByJobId(int id) {
		
		return bidDao.findLowestAmountByJobId(id);
	}

}
