package ie.mohammed.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ie.mohammed.model.Category;
import ie.mohammed.model.Job;
import ie.mohammed.services.CategoryService;
import ie.mohammed.services.JobService;

/**
 * Rest controller. Implement product api to manage products. Map all /products
 * requests
 */

@Controller
@RestController
@RequestMapping(value = "/api/job")
public class JobController {

	@Autowired
	private JobService jobService;

	@Autowired
	private CategoryService categoryService;

	/**
	 * Map /jobs GET requests Find all products
	 * 
	 * @return - JSON with found job
	 */
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity findAll() {
		return ResponseEntity.ok(jobService.findAll());
	}

	/**
	 * Map /jobs/id GET requests Find product by id If product not found - response
	 * with NotFound status
	 * 
	 * @param productId
	 * @return - JSOn with found job
	 */
	@RequestMapping(value = "/{jobId:[\\d]+}", method = RequestMethod.GET)
	ResponseEntity findById(@PathVariable int jobId) {
		if ((jobService.findById(jobId)).isPresent()) {
			return ResponseEntity.ok(jobService.findById(jobId).get());
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Map /job POST requests Save product If category specified in body not found -
	 * response with UnprocessableEntity status
	 * 
	 * @param job
	 * @return - slink to saved job with JSON in body
	 */
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity save(@RequestBody Job job) {
		if (!categoryService.findByName(job.getCategory_name()).isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		Job result = new Job();

		result.setName(job.getName());
		result.setCategory(new Category(job.getCategory_name()));
		result.setPrice(job.getPrice());
		result.setDescription(job.getDescription());

		result = jobService.save(result);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{productId}")
				.buildAndExpand(result.getId()).toUri();

		return ResponseEntity.created(location).body(result);
	}

	/**
	 * Map /jobs/id DELETE requests Delete product by id If job not found - response
	 * with NotFound status
	 * 
	 * @param jobId
	 * @return - status Ok
	 */
	@RequestMapping(value = "/{jobId:[\\d]+}", method = RequestMethod.DELETE)
	ResponseEntity delete(@PathVariable int jobId) {
		if (jobService.findById(jobId).isPresent()) {
			jobService.deleteById(jobId);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
