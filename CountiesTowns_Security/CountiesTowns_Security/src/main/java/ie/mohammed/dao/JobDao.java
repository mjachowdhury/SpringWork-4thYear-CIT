package ie.mohammed.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ie.mohammed.entities.Job;

public interface JobDao extends JpaRepository<Job, Integer> {
	Job findByJobName(String jobName);
	boolean existsByJobName(String jobName);
	List<Job> findAllByOrderByJobNameAsc();
	
	@Query("SELECT c.jobName FROM Job c where c.jobId = :id") 
	String findNameOfJobById(@Param("id") int id);

	@Query("SELECT c FROM Job c JOIN Bid t ON t.job=c WHERE t.bidAmount=:bidAmount")
	List<Job> findJobsWithBidAmount(@Param("bidAmount") Double bidAmount);
}
