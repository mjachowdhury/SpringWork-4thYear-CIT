package ie.mohammed.controllers;

import java.security.Principal;
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
import ie.mohammed.entities.MyUser;
import ie.mohammed.entities.Bid;
import ie.mohammed.formobjects.BidForm;
import ie.mohammed.services.JobService;
import ie.mohammed.services.MyUserService;
import ie.mohammed.services.BidService;

@Controller
public class BidController {

	@Autowired
	BidService bidService;

	@Autowired
	JobService jobService;

	@Autowired
	MyUserService myUserService;

	private int jobId;

	@GetMapping("/bids")
	public String showBids(Model model, Locale locale) {
		List<Bid> bids = bidService.findBidsAlphabticalOrder();
		model.addAttribute("bids", bids);
		return "bids";
	}

	@GetMapping("/bid")
	public String showBidById(@RequestParam(name = "id") int id, Model model, Locale locale) {
		Bid bid = bidService.findBidById(id);
		if (bid == null) {
			model.addAttribute("id", id);
			return "notfounderror";
		}
		model.addAttribute("bid", bidService.findBidById(id));
		return "bid";
	}

	@GetMapping("/bidsinjob/{id}")
	public String showBidsInJob(@PathVariable(name = "id") int id, Model model, Locale locale) {
		Job job = jobService.findJob(id);
		if (job == null) {
			model.addAttribute("id", id);
			return "notfounderror";
		}
		model.addAttribute("job", job);
		return "bidsInJob";
	}

	@GetMapping("/newbid")
	public String addNewBid(Model model) {
		model.addAttribute("bidForm", new BidForm());
		model.addAttribute("jobs", jobService.listInAlphabeticalOrder());
		return "newbid";
	}

	/*
	 * Send a BidForm object and a list of the jobs to the view.
	 */
	@GetMapping("/newbid/{id}")
	public String addNewBid(@PathVariable(name = "id") int id, Model model) {
		jobId = id;
		model.addAttribute("bidForm", new BidForm());
		model.addAttribute("jobs", jobService.listInAlphabeticalOrder());
		return "newbid";
	}

	 
	@PostMapping("newbid")
	public String addNewBid(Model model, @Valid BidForm bidForm, BindingResult binding,
			RedirectAttributes redirectAttributes, Principal user) {
		  
		if (binding.hasErrors()) {
			model.addAttribute("jobs", jobService.listInAlphabeticalOrder());
			return "newbid";
		}
		 
		MyUser myUserCreator = myUserService.findByEmail(user.getName());
		Bid bid = new Bid(bidForm.getBidAmount(), jobService.findJob(jobId), myUserCreator.getUserEmail());
		if (bidService.save(bid) == null) {
			redirectAttributes.addFlashAttribute("duplicate", true);
			redirectAttributes.addFlashAttribute("bidTooHigh", true);
			return "redirect:/newbid";
		}
		return "redirect:/bid?id=" + bid.getBidId();
	}

}

/*
 * @RequestMapping("/users/{username}/bids") public List<Lot>
 * getAllBids(@PathVariable String username){ return
 * bidService.getAllBids(username); }
 * 
 * @RequestMapping("/users/{username}/bids/{id}") public Lot
 * getBid(@PathVariable String id){ return bidService.getBid(id); }
 * 
 * @RequestMapping(method= RequestMethod.POST, value =
 * "/users/{username}/bids/{amount}") public void addBids(@RequestBody Lot
 * lot, @PathVariable String username, @PathVariable double amount){
 * bidService.addBid(lot,username,amount); }
 * 
 * @RequestMapping(method= RequestMethod.PUT, value =
 * "/users/{username}/bids/{id}/{amount}") public void updateLot(@RequestBody
 * Lot lot, @PathVariable String username,@PathVariable String id, @PathVariable
 * double amount){ bidService.updateBid(lot,amount); }
 */