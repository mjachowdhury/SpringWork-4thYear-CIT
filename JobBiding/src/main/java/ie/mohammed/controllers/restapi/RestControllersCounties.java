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

import ie.mohammed.entities.County;
import ie.mohammed.services.CountyService;

@RestController
@RequestMapping("/api")
public class RestControllersCounties {

	@Autowired
	private CountyService countyService;
	
	@PreAuthorize("hasRole('API')")
	@GetMapping("/counties")
	public List<County> myRestCounties(){
		return countyService.listAllCounties();
	}
	
	@PreAuthorize("hasRole('API')")
	@GetMapping("/county/{id}")
	public County myRestCounty(@PathVariable("id") int id){
		return countyService.findCounty(id);
	}
	
	@PreAuthorize("hasRole('API')")
	@GetMapping("/countiesordered")
	public List<County> myRestCountiesOrdered(){
		return countyService.listInAlphabeticalOrder();
	}
	
	@PreAuthorize("hasRole('API')")
	@PostMapping("/counties")
	public List<County> myRestCountiesAsOrdered(){
		return countyService.listAllCounties();
	}
	
	@PreAuthorize("hasRole('API')")
	@DeleteMapping("/county/{countyName}")
	public ResponseEntity<County> deleteCountyByName(@PathVariable(name="countyName") String countyName){
		County county = countyService.findByName(countyName);
		if ( county == null ){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
        countyService.deleteCounty(county);
		return new ResponseEntity<>(county, HttpStatus.OK);
	}
}
