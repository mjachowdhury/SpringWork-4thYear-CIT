package ie.mohammed.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.mohammed.model.Bid;

/**
 * Repository for data management in a table "bets"
 */

public interface BidDao extends JpaRepository<Bid, Integer> {
	
	List<Bid> findByAuctionId(int id);
	
	List<Bid> findByUserUserName(String userName);

}
