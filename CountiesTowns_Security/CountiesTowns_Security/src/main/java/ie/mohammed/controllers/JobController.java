package ie.mohammed.controllers;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.mohammed.entities.Job;
import ie.mohammed.entities.MyUser;
import ie.mohammed.formobjects.JobForm;
import ie.mohammed.services.JobService;
import ie.mohammed.services.MyUserService;

@Controller
public class JobController {
	@Autowired
	private JobService jobService;
	
	@Autowired
	private MyUserService userService;
	
	@GetMapping("/jobs") 
	public String showJobs(Model model)
	{
		List<Job> jobs = jobService.listInAlphabeticalOrder();
		model.addAttribute("jobs", jobs);
		return "jobs";
	}
	
	@GetMapping("/job/{id}") 
	public String showJob(@PathVariable(name="id") int id, Model model)
	{
		if (jobService.existsByJobId(id)) {
			Job job = jobService.findJob(id);
			model.addAttribute("job", job);
			return "job";
		}
		model.addAttribute("id", id);
		return "notfounderror";
	}
	
	@GetMapping("/newjob")
	public String addNewJobForm(Model model)
	{
		model.addAttribute("aJobForm", new JobForm());
		return "newjob";
	}
	
	@PostMapping("/newjob")
	public String addNewJobSave(@Valid @ModelAttribute("aJobForm") JobForm jobForm, BindingResult binding, RedirectAttributes redirectAttributes, Principal user)
	{
		if (binding.hasErrors())
			return "newjob";
		MyUser creator = userService.findByEmail(user.getName());
		Job job = new Job(jobForm.getJobName(), creator);
		Job newJob = jobService.save(job);
		if (newJob != null)
			return "redirect:job/"+newJob.getJobId();
		else
			// starts again with empty form (new object)
			redirectAttributes.addFlashAttribute("duplicate", true);
			return "redirect:newjob";	
	}
	

}
