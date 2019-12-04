package ie.mohammed;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ie.mohammed.entities.Bid;
import ie.mohammed.entities.Job;
import ie.mohammed.services.BidService;
import ie.mohammed.services.JobService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SchedulingTasks {

	@Autowired
	BidService bidService;

	@Autowired
	JobService jobService;

	// At 14:56 every day....
	@Scheduled(cron = "0 0 0 * * *")
	public void closeBids() {
		List<Job> jobs = jobService.listAllJobs();
		for (Job job : jobs)
			jobService.closeJob(job);

	}

}
