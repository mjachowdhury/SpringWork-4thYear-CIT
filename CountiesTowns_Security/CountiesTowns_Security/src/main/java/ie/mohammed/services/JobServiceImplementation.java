package ie.mohammed.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.mohammed.dao.JobDao;
import ie.mohammed.entities.Job;

@Service
public class JobServiceImplementation implements JobService {

	@Autowired 
	private JobDao jobDao;
	
	@Override
	public Job findJob(int id) {
		if (jobDao.existsById(id))
			return jobDao.findById(id).get();
		return null;
	}

	@Override
	public boolean deleteJob(int id) {
		if (jobDao.existsById(id))
		{
			jobDao.deleteById(id);
			return true;
		}
		return false;

	}

	@Override
	public Job findByName(String jobName) {
		if (jobDao.existsByJobName(jobName))
			return jobDao.findByJobName(jobName);
		return null;
	}

	@Override
	public String findJobName(int id) {
		if (jobDao.existsById(id))
			return jobDao.findNameOfJobById(id);
		return null;
	}

	@Override
	public List<Job> findJobsWithBidAmount(double bidAmount) {
		return jobDao.findJobsWithBidAmount(bidAmount);
	}

	@Override
	public Job save(Job job) {
		if (jobDao.existsByJobName(job.getJobName()))
			return null;
		return jobDao.save(job);
	}

	@Override
	public boolean deleteJob(Job job) {
		System.out.println(job);
		if (! jobDao.existsById(job.getJobId()))
			return false;
		jobDao.delete(job);
		System.out.println(jobDao.existsByJobName(job.getJobName()));
		return true;
	}

	@Override
	public List<Job> listAllJobs() {
		return jobDao.findAll();
	}
	
	@Override
	public List<Job> listInAlphabeticalOrder() {
		return jobDao.findAllByOrderByJobNameAsc();		
	}

	@Override
	public boolean existsByJobId(int jobId) {
		return jobDao.existsById(jobId);
	}

	/*
	 * @Override public List<Job> findIsActive(boolean isActive) { return
	 * jobDao.findByIsActive(isActive); }
	 */

	 
		
	
}
