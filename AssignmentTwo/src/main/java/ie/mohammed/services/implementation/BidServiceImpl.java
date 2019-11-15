package ie.mohammed.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ie.mohammed.dao.BidDao;
import ie.mohammed.model.Bid;
import ie.mohammed.services.BidService;

/**
 * Implementing the BetService interface
 */

@Service("bidService")
@Transactional
public class BidServiceImpl implements BidService {

	@Autowired
	private BidDao bidDao;

	@Override
	public List<Bid> findAll() {

		return bidDao.findAll();
	}

	@Override
	public Optional<Bid> findById(int id) {

		return bidDao.findById(id);
	}

	@Override
	public List<Bid> findByAuctionId(int id) {

		return bidDao.findByAuctionId(id);
	}

	@Override
	public List<Bid> findByUserName(String userName) {

		return bidDao.findByUserUserName(userName);
	}

	@Override
	public Bid save(Bid bet) {

		return bidDao.save(bet);
	}

	@Override
	public void deleteById(int id) {
		bidDao.deleteById(id);

	}

}
