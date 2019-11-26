package ie.mohammed.controllers;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.mohammed.entities.Job;
import ie.mohammed.entities.Bid;
import ie.mohammed.formobjects.BidForm;
import ie.mohammed.services.JobService;
import ie.mohammed.services.BidService;

@Controller
public class BidController {

	@Autowired
	BidService bidService;
	
	@Autowired 
	JobService jobService;
	
	
	  @GetMapping("/bids") public String showBids(Model model, Locale locale) {
	  List<Bid> bids = bidService.findBidsAlphabticalOrder();
	  model.addAttribute("bids", bids); return "bids"; }
	 
	
	
	@GetMapping("/bid")
	public String showBidById(@RequestParam(name="id") int id, Model model, Locale locale)
	{
		Bid bid = bidService.findBidById(id);
		if (bid == null){
			model.addAttribute("id", id);
			return "notfounderror";
		}
		model.addAttribute("bid", bidService.findBidById(id));
		return "bid";
	}
	
	@GetMapping("/bidsinjob/{id}")
	public String showBidsInJob(@PathVariable(name="id") int id, Model model, Locale locale)
	{
		Job job = jobService.findJob(id);
		if ( job == null) {
			model.addAttribute("id", id);
			return "notfounderror";
		}
		model.addAttribute("job", job);
		return "bidsInJob";
	}
	
	/*
	 * Send a BidForm object and a list of the counties to the view.  
	 */
	@GetMapping("newbid")
	public String addNewBid(Model model)
	{
		model.addAttribute("bidForm", new BidForm());
		model.addAttribute("jobs", jobService.listInAlphabeticalOrder());
		return "newbid";
	}

	/*
	 * This is called when the user clicks the submit button on the for to create a new town. 
	 * It is supposed to bring back an object populated with data. However there may have been 
	 * problems binding the user's data to the object. In this case, the user is returned to the
	 * form's view, along with the form's object which is tied to the model already, and error
	 * messages are triggered e.g. Size.townName in messages.properties might be displayed. 
	 * There will be no issue binding the county's ID to the form's field because the data
	 * comes through a pre-populated dropdown list.     
	 */
	@PostMapping("newbid")
	public String addNewBid(Model model, @Valid BidForm bidForm, BindingResult binding, RedirectAttributes redirectAttributes)
	{
		// return to the view with the user's data, and add the list of counties to the model
		// again for the drop down list - the binding to townForm is retained but not the list of counties. 
		if (binding.hasErrors()){
			model.addAttribute("jobs", jobService.listInAlphabeticalOrder());
			return "newbid";
		}
		// Create a Bid object with the new bids's amount and the job's id. 
		// It it already exists, a one-use-only attribute called "duplicate" is created. This attribute could 
		// have any value - its value is not important, only the fact that it has been created is relevant. 
		// If the town does not already exist, it is created and a view with that town is called. 
		Bid bid = new Bid(bidForm.getBidAmount(), jobService.findJob(bidForm.getJobId()));
		if (bidService.save(bid) == null){
			redirectAttributes.addFlashAttribute("duplicate", true);
			return "redirect:/newbid";
		}
		return "redirect:/bid?id="+bid.getBidId();
	}

}
