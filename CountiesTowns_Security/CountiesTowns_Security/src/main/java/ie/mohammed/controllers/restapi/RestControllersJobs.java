package ie.mohammed.controllers.restapi;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ie.mohammed.entities.Bid;
import ie.mohammed.entities.Job;
import ie.mohammed.entities.MyUser;
import ie.mohammed.services.BidService;
import ie.mohammed.services.JobService;
import ie.mohammed.services.MyUserService;

@RestController
@RequestMapping("/api")
public class RestControllersJobs {

	@Autowired
	private JobService jobService;

	@Autowired
	private BidService bidService;
	
	@Autowired
	private MyUserService myUserService;

	@PreAuthorize("hasRole('API')")
	@GetMapping("/jobs")
	public List<Job> myRestJobs() {
		return jobService.listAllJobs();
	}

	@PreAuthorize("hasRole('API')")
	@GetMapping("/job/{id}")
	public Job myRestJob(@PathVariable("id") int id) {
		return jobService.findJob(id);
	}

	@PreAuthorize("hasRole('API')")
	@GetMapping("/jobsordered")
	public List<Job> myRestJobsOrdered() {
		return jobService.listInAlphabeticalOrder();
	}

	@PreAuthorize("hasRole('API')")
	@PostMapping("/jobs")
	public List<Job> myRestJobsAsOrdered() {
		return jobService.listAllJobs();
	}

	@PreAuthorize("hasRole('API')")
	@DeleteMapping("/job/{jobName}")
	public ResponseEntity<Job> deleteJobByName(@PathVariable(name = "jobName") String jobName) {
		Job job = jobService.findByName(jobName);
		if (job == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		jobService.deleteJob(job);
		return new ResponseEntity<>(job, HttpStatus.OK);
	}

	// Bids Part

	@PreAuthorize("hasRole('API')")
	@GetMapping("/bids")
	public List<Bid> myRestBids() {
		return bidService.listAllBids();
	}

	@PreAuthorize("hasRole('API')")
	@GetMapping("/bid/{id}")
	public Bid myRestBid(@PathVariable("id") int id) {
		return bidService.findBid(id);
	}

	@PreAuthorize("hasRole('API')")
	@GetMapping("/bidsordered")
	public List<Bid> myRestBidsOrdered() {
		return bidService.findBidsAlphabticalOrder();
	}

	@PreAuthorize("hasRole('API')")
	@PostMapping("/bids")
	public List<Bid> myRestBidsAsOrdered() {
		return bidService.listAllBids();
	}

	@PreAuthorize("hasRole('API')")
	@PostMapping("/job")
	public ResponseEntity<Job> addNewCountySave(@RequestBody Map<String, String> stringToJson) {
		MyUser myUser = myUserService.findByEmail("user.one@cit.ie");
		Job newJob = jobService.save(new Job(stringToJson.get("jobName"), stringToJson.get("jobDescription"), LocalDate.now(),1, myUser));
		 
		if (newJob == null)
			return new ResponseEntity<>(newJob, HttpStatus.CONFLICT);
		return new ResponseEntity<>(newJob, HttpStatus.CREATED);
	}
}
