package ie.mohammed.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ie.mohammed.services.AuctionService;
import ie.mohammed.services.BidService;
import ie.mohammed.services.UserService;

@RestController
@RequestMapping(value = "/api/bets")
public class Bidcontroller {
	
	@Autowired
	private BidService bidService;
	
	@Autowired
	private AuctionService auctionService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * Map /bids GETrequest
	 * Find all bids
	 * @return - JSON with found bids
	 */
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity findAll() {
		return ResponseEntity.ok(bidService.findAll());
	}

	
	/**
	 * Map / bids?id= GET requests
	 * Find bid by id
	 * If bid not found - response with NotFound status
	 * @param id
	 * @return -JSON with found bid
	 */
	
	@RequestMapping(params = "id", method = RequestMethod.GET)
	ResponseEntity findById(@RequestParam("id") int id) {
		if(bidService.findById(id).isPresent()) {
			return ResponseEntity.ok(bidService.findById(id).get());
		}
		return ResponseEntity.notFound().build();
	}
	
	
	/**
	 * Map /bids?auctionId=GET request
	 * Find bids by auction id
	 * If auction not found - response with NotFound status
	 * @param auctionId
	 * @return -Json with found bids
	 */
	
	@RequestMapping(params = "auctionId", method = RequestMethod.GET)
	ResponseEntity findByAuctionId (@RequestParam("auctionId") int auctionId){
		if(auctionService.findById(auctionId).isPresent()) {
			return ResponseEntity.ok(bidService.findById(auctionId));
		}
		return ResponseEntity.notFound().build();
	}
}
