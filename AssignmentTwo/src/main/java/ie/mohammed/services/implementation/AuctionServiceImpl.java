package ie.mohammed.services.implementation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ie.mohammed.dao.AuctionDao;
import ie.mohammed.model.Auction;
import ie.mohammed.services.AuctionService;

/**
 * Implementing the AuctionService interface
 */
@Service
@Transactional
public class AuctionServiceImpl implements AuctionService{

	@Autowired
	private AuctionDao auctionDao;
	@Override
	public List<Auction> findAll() {
		 
		return auctionDao.findAll();
	}

	@Override
	public Optional<Auction> findById(int id) {
		 
		return auctionDao.findById(id);
	}

	@Override
	public List<Auction> findFinished(Boolean finished) {
		 
		return auctionDao.findByFinished(finished);
	}

	@Override
	public List<Auction> findByCategoryName(String categoryName) {
		 
		return auctionDao.findByJobCategoryName(categoryName);
	}

	@Override
	public List<Auction> findByProductNameContains(String searchTag) {
		 
		return auctionDao.findByJobNameContains(searchTag);
	}

	@Override
	public List<Auction> findByUserName(String userName) {
		 
		return auctionDao.findByOwnerUserName(userName);
	}

	@Override
	public List<Auction> findByEndTimeLessThan(Date date) {
		 
		return auctionDao.findByEndTimeIsLessThan(date);
	}

	@Override
	public Auction save(Auction auction) {
		 
		return auctionDao.save(auction);
	}

	@Override
	public void deleteById(int id) {
		 auctionDao.deleteById(id);
		
	}

}
