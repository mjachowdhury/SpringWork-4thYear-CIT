package ie.mohammed.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ie.mohammed.entities.Job;

public interface JobDao extends JpaRepository<Job, Integer> {
	Job findByJobName(String jobName);

	boolean existsByJobName(String jobName);

	List<Job> findAllByOrderByJobNameAsc();

	// List<Job> findByIsActive(boolean isActive);

	@Query("SELECT c.jobName FROM Job c where c.jobId = :id")
	String findNameOfJobById(@Param("id") int id);

	@Query("SELECT c FROM Job c JOIN Bid t ON t.job=c WHERE t.bidAmount=:bidAmount")
	List<Job> findJobsWithBidAmount(@Param("bidAmount") double bidAmount);
	
	@Transactional
	@Modifying
	@Query("UPDATE Job job SET job.isActiveJob = 0 WHERE job.jobId=:id")
	void closeJobByJodId(@Param("id") int id);
}
