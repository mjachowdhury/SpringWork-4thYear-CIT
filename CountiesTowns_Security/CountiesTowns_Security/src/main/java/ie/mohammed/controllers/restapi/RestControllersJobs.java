package ie.mohammed.controllers.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ie.mohammed.entities.Job;
import ie.mohammed.services.JobService;

@RestController
@RequestMapping("/api")
public class RestControllersJobs {

	@Autowired
	private JobService jobService;
	
	@PreAuthorize("hasRole('API')")
	@GetMapping("/jobs")
	public List<Job> myRestJobs(){
		return jobService.listAllJobs();
	}
	
	@PreAuthorize("hasRole('API')")
	@GetMapping("/job/{id}")
	public Job myRestJob(@PathVariable("id") int id){
		return jobService.findJob(id);
	}
	
	@PreAuthorize("hasRole('API')")
	@GetMapping("/jobsordered")
	public List<Job> myRestJobsOrdered(){
		return jobService.listInAlphabeticalOrder();
	}
	
	@PreAuthorize("hasRole('API')")
	@PostMapping("/jobs")
	public List<Job> myRestJobsAsOrdered(){
		return jobService.listAllJobs();
	}
	
	@PreAuthorize("hasRole('API')")
	@DeleteMapping("/job/{jobName}")
	public ResponseEntity<Job> deleteJobByName(@PathVariable(name="jobName") String jobName){
		Job job = jobService.findByName(jobName);
		if ( job == null ){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
        jobService.deleteJob(job);
		return new ResponseEntity<>(job, HttpStatus.OK);
	}
}
