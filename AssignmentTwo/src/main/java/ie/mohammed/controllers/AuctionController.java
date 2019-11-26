package ie.mohammed.controllers;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ie.mohammed.model.Auction;
import ie.mohammed.model.Category;
import ie.mohammed.model.Job;
import ie.mohammed.services.AuctionService;
import ie.mohammed.services.CategoryService;
import ie.mohammed.services.JobService;
import ie.mohammed.services.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * Rest controller. Implement auction api to manage auctions. Map all /auctions
 * requests
 */
@Controller
@Slf4j
@RestController
@RequestMapping(value = "/api/auctions")
public class AuctionController {

	@Autowired
	private AuctionService auctionService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	@Autowired
	private JobService jobService;

	/**
	 * Map /auctions GET requests Find all auctions
	 * 
	 * @return - JSON with all auctions
	 */

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity findAll() {
		return ResponseEntity.ok(auctionService.findAll());
	}

	@GetMapping("/auctions")
	public String showAuctions(Model model) {
		List<Auction> auctions = auctionService.findAll();
		model.addAttribute("auctions", auctions);
		log.info("Number of Auctions: {}", auctions.size());
		return "auctions";
	}
	
	/**
	 * Map /auctions/id GET requests Find auction by id If auction not found -
	 * response with NotFound status
	 * 
	 * @param auctionId
	 * @return - JSON with found auction
	 */
	@RequestMapping(value = "/{auctionId:[\\d]+}", method = RequestMethod.GET)
	ResponseEntity findById(@PathVariable int auctionId) {
		if (auctionService.findById(auctionId).isPresent()) {
			return ResponseEntity.ok(auctionService.findById(auctionId).get());
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Map /auctions?finished= GET requests Find finished or not auctions
	 * 
	 * @param finished - true or false
	 * @return - JSON with found auctions
	 */

	@RequestMapping(params = "finished", method = RequestMethod.GET)
	ResponseEntity findOngoing(@RequestParam("finished") boolean finished) {
		return ResponseEntity.ok(auctionService.findFinished(finished));
	}

	/**
	 * Map /auctions?category= GET requests Find auctions by category If category
	 * not found - response with NotFound status
	 * 
	 * @param category
	 * @return - JSON with found auctions
	 */
	@RequestMapping(params = "category", method = RequestMethod.GET)
	ResponseEntity findByCategory(@RequestParam("category") String category) {
		if (categoryService.findByName(category).isPresent()) {
			return ResponseEntity.ok(auctionService.findByCategoryName(category));
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Map /auctions?search= GET requests Find auctions with searchTag in product
	 * name Search tag case-sensitive and found only whole word
	 * 
	 * @param search
	 * @return - JSON with found auctions
	 */
	@RequestMapping(params = "search", method = RequestMethod.GET)
	ResponseEntity findByProductNameContains(@RequestParam("search") String search) {
		return ResponseEntity.ok(auctionService.findByProductNameContains(search));
	}

	/**
	 * Map /auctions?user= GET requests Find auctions by username If user not found
	 * - response with NotFound status
	 * 
	 * @param userName
	 * @return - JSON with found auctions
	 */
	@RequestMapping(params = "user", method = RequestMethod.GET)
	ResponseEntity findByUserName(@RequestParam("user") String userName) {
		if (userService.findByUserName(userName).isPresent()) {
			return ResponseEntity.ok(auctionService.findByUserName(userName));
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Map /auctions?endBefore= GET requests Find auctions what end before input
	 * date
	 * 
	 * @param date
	 * @return - JSON with found auctions
	 */
	@RequestMapping(params = "endBefore", method = RequestMethod.GET)
	ResponseEntity findByEndTime(@RequestParam("endBefore") @DateTimeFormat(pattern = "dd.MM.yyyyhh:mm") Date date) {
		return ResponseEntity.ok(auctionService.findByEndTimeLessThan(date));
	}

	/**
	 * Map /auctions POST requests Save auction and job If user or category
	 * specified in body not found - response with UnprocessableEntity status
	 * 
	 * @param auction - requested body with auction JSON
	 * @return - link to created auction with JSON in body.
	 */

	@RequestMapping(params = "duration", method = RequestMethod.POST)
	ResponseEntity save(@RequestBody Auction auction, @RequestParam("duration") int duration) {

		if (!userService.findByUserName(auction.getOwner_name()).isPresent()
				|| !categoryService.findByName(auction.getJob().getCategory_name()).isPresent()) {

			return ResponseEntity.unprocessableEntity().build();
		}
		if (auction.getJob().getId() != 0 && !jobService.findById(auction.getJob().getId()).isPresent()) {

			return ResponseEntity.unprocessableEntity().build();
		}
		if (auction.getJob().getId() == 0) {
			auction.getJob().setCategory(new Category(auction.getJob().getCategory_name()));

			Job newProduct = jobService.save(auction.getJob());

			newProduct = jobService.save(newProduct);

			auction.getJob().setId(newProduct.getId());
		}

		Auction result = new Auction();

		result.setOwner(userService.findByUserName(auction.getOwner_name()).get());
		result.setJob(auction.getJob());
		result.setCreateTime(new Date());
		result.setEndTime(Date.from(LocalDateTime.now().plusDays(duration).atZone(ZoneId.systemDefault()).toInstant()));
		result.setDescription(auction.getDescription());
		result.setFinished(auction.getFinished());

		result = auctionService.save(result);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{auctionId}")
				.buildAndExpand(result.getId()).toUri();

		return ResponseEntity.created(location).body(result);
	}

	/**
	 * Map /auctions/id DELETE requests Delete auction by id If auction not found -
	 * response with NotFound status
	 * 
	 * @param auctionId
	 * @return - status Ok
	 */
	@RequestMapping(value = "/{auctionId:[\\d]+}", method = RequestMethod.DELETE)
	ResponseEntity delete(@PathVariable int auctionId) {
		if (auctionService.findById(auctionId).isPresent()) {
			auctionService.deleteById(auctionId);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Map /auctions/id?finish= PUT requests Change auction state to finished If
	 * auction not found - response with NotFound status
	 * 
	 * @param auctionId
	 * @param finish
	 * @return - JSON with changed auction
	 */
	@RequestMapping(value = "/{auctionId:[\\d]+}", params = "finish", method = RequestMethod.PUT)
	ResponseEntity finish(@PathVariable int auctionId, @RequestParam Boolean finish) {
		if (!auctionService.findById(auctionId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		if (finish) {
			Auction auction = auctionService.findById(auctionId).get();
			auction.setFinished(finish);
			auctionService.save(auction);
		}
		return ResponseEntity.ok(auctionService.findById(auctionId).get());
	}

}
