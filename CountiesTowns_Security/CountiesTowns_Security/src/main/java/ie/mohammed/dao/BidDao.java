package ie.mohammed.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import ie.mohammed.entities.Bid;

public interface BidDao extends JpaRepository<Bid, Integer> {
	List<Bid> findAllByOrderByBidAmountAsc();
	List<Bid> findByJob_JobName(String jobName);
	List<Bid> findByJob_JobId(int jobId);
	
	boolean existsByBidAmountAndJob_JobId(Double amount, int jobId);
	List<Bid> findBidByBidAmount(Double bidAmount);
	
	//int findLowestAmountByJobId(int id);
	
	//List<Bid> getByBidNameIgnoringCase(String bidAmount);
		//List<Bid> getByBidNameIsStartingWith(String s);
}
