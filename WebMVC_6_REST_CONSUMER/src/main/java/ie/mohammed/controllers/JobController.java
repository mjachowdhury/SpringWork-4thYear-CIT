package ie.mohammed.controllers;

import java.util.List;
import javax.validation.Valid;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.mohammed.domain.Job;
import ie.mohammed.formobjects.JobForm;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class JobController {
	

	@GetMapping("/showjob/{id}")
	public String showjobs(@PathVariable(name="id") int id, Model model)
	{
		try {
			RestTemplate restTemplate = new RestTemplate();
			// Retrieves the Json data as an object; needs the request URL, the response type and the data
			Job job = restTemplate.getForObject("http://localhost:8080/api/jobid/{id}", Job.class, id);
			model.addAttribute("job", job);
			return "job";
		}
		catch (HttpClientErrorException ex) {	// all 4xx errors codes
			model.addAttribute("exception", ex);
			return "notfound";
		}
		
	}

	@GetMapping("/showalljobs")
	public String showAlljobs(Model model)
	{
		RestTemplate restTemplate = new RestTemplate();
		String URL = "http://localhost:8080/api/jobs";
		// Send the API request for the list jobs, returned as Json and mapped to a list of Job objects. 
		// The ResponseEntity includes the HTTP responses as well as the raw JSON data which is converted into a list of Job objects.
		///new ParameterizedTypeReference<List<Job>>(){}  allows us to send any type to the request, but we must specify a subtype
		ParameterizedTypeReference<List<Job>> listOfjobs = new ParameterizedTypeReference<List<Job>>(){};
		ResponseEntity<List<Job>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null, listOfjobs );
		List<Job> jobs = responseEntity.getBody();
		log.info(responseEntity.getHeaders().toString());
		model.addAttribute("jobs", jobs);
		return "jobs";
	}

	@GetMapping("/newjob")
	public String addjob(Model model)
	{
		model.addAttribute("jobForm", new JobForm());	// form binding
		return "newjob";
	}

	@PostMapping("/newjob")
	public String addjob(@Valid JobForm jobForm, BindingResult binding, RedirectAttributes redirectAttributes) 
	{
		if (binding.hasErrors()) 
			return "newjob";
		try {
			RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:8080/api/job";
			HttpEntity<String> request = new HttpEntity<>(jobForm.getJobName());
			// This example retrieves the status code as well as the newly created Job object retrieved from the database. 
			// We could have used postForObject(url, request, Job.class) which would just return the job and then
			// we could check for null if it was not created but I wanted to show you an alternative system that uses
			// the status codes. 
			ResponseEntity<Job> responseEntity =  restTemplate.exchange(url, HttpMethod.POST, request, Job.class);
			log.info("STATUS CODE: {}", responseEntity.getStatusCode());
			return "redirect:showjob/"+responseEntity.getBody().getJobId();
		}
		catch(HttpClientErrorException ex)
		{
			// comes back with 409 CONFLICT so comes in here to 4XX section
			log.error("STATUS CODE: {}",ex.getStatusCode());
			redirectAttributes.addFlashAttribute("problem", true);
			return "redirect:newjob";
		}
		catch(Exception ex) {
			log.error("STATUS CODE: {}",ex.getMessage());
			return "error";
		}
	}
}
