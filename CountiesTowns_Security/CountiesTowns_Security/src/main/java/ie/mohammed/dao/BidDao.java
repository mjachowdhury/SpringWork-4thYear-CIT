package ie.mohammed.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ie.mohammed.entities.Bid;
import ie.mohammed.entities.Job;

public interface BidDao extends JpaRepository<Bid, Integer> {
	 
	List<Bid> findAllByOrderByBidAmountAsc();
	List<Bid> findByJob_JobName(String jobName);
	
	@Query("SELECT c FROM Bid c where c.job.id = :id")
	List<Bid> findByJob_JobId(@Param("id") int id);
	 
	boolean existsByBidAmountAndJob_JobId(double amount, int jobId);
	List<Bid> findBidByBidAmount(double bidAmount);
	
	//@Query(value = "SELECT item.*, MIN(myBid.bid) AS myBid, (SELECT MIN(lowestBid.bid) AS lowestbid FROM bids lowestBid WHERE lowestBid.item_id = item.item_id GROUP BY lowestBid.item_id) FROM item JOIN bids myBid ON item.item_id = myBid.item_id WHERE myBid.user_id = :user_id GROUP BY item.item_id", nativeQuery = true)

	//@Query(value="SELECT min(bidAmount) from Bid")
	
	//@Query(value="SELECT min(c.bidId) from Bid c where c.jobId = :id")
	
	@Query("SELECT min(c.bidAmount) FROM Bid c JOIN Job t ON c.job=t WHERE t.jobId=:id")
	Integer findLowestAmountByJobId(@Param("id") int id);
	
	//List<Bid> getByBidNameIgnoringCase(String bidAmount);
		//List<Bid> getByBidNameIsStartingWith(String s);
}
