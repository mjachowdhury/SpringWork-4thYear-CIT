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

import ie.mohammed.entities.County;
import ie.mohammed.entities.Town;
import ie.mohammed.formobjects.TownForm;
import ie.mohammed.services.CountyService;
import ie.mohammed.services.TownService;

@Controller
public class TownController {

	@Autowired
	TownService townService;
	
	@Autowired 
	CountyService countyService;
	
	@GetMapping("/towns")
	public String showTowns(Model model, Locale locale)
	{
		List<Town> towns = townService.findTownsAlphabticalOrder();
		model.addAttribute("towns", towns);
		return "towns";
	}
	
	
	@GetMapping("/town")
	public String showTownById(@RequestParam(name="id") int id, Model model, Locale locale)
	{
		Town town = townService.findTown(id);
		if (town == null){
			model.addAttribute("id", id);
			return "notfounderror";
		}
		model.addAttribute("town", townService.findTown(id));
		return "town";
	}
	
	@GetMapping("/townsincounty/{id}")
	public String showTownsInCounty(@PathVariable(name="id") int id, Model model, Locale locale)
	{
		County county = countyService.findCounty(id);
		if ( county == null) {
			model.addAttribute("id", id);
			return "notfounderror";
		}
		model.addAttribute("county", county);
		return "townsInCounty";
	}
	
	/*
	 * Send a TownForm object and a list of the counties to the view.  
	 */
	@GetMapping("newtown")
	public String addNewTown(Model model)
	{
		model.addAttribute("townForm", new TownForm());
		model.addAttribute("counties", countyService.listInAlphabeticalOrder());
		return "newtown";
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
	@PostMapping("newtown")
	public String addNewTown(Model model, @Valid TownForm townForm, BindingResult binding, RedirectAttributes redirectAttributes)
	{
		// return to the view with the user's data, and add the list of counties to the model
		// again for the drop down list - the binding to townForm is retained but not the list of counties. 
		if (binding.hasErrors()){
			model.addAttribute("counties", countyService.listInAlphabeticalOrder());
			return "newtown";
		}
		// Create a Town object with the new town's name and the county's id. 
		// It it already exists, a one-use-only attribute called "duplicate" is created. This attribute could 
		// have any value - its value is not important, only the fact that it has been created is relevant. 
		// If the town does not already exist, it is created and a view with that town is called. 
		Town town = new Town(townForm.getTownName(), countyService.findCounty(townForm.getCountyId()));
		if (townService.save(town) == null){
			redirectAttributes.addFlashAttribute("duplicate", true);
			return "redirect:/newtown";
		}
		return "redirect:/town?id="+town.getTownId();
	}

}
