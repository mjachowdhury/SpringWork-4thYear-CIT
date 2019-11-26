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

import ie.mohammed.entities.County;
import ie.mohammed.entities.MyUser;
import ie.mohammed.formobjects.CountyForm;
import ie.mohammed.services.CountyService;
import ie.mohammed.services.MyUserService;

@Controller
public class CountyController {
	@Autowired
	private CountyService countyService;
	
	@Autowired
	private MyUserService userService;
	
	@GetMapping("/counties") 
	public String showCounties(Model model)
	{
		List<County> counties = countyService.listInAlphabeticalOrder();
		model.addAttribute("counties", counties);
		return "counties";
	}
	
	@GetMapping("/county/{id}") 
	public String showCounty(@PathVariable(name="id") int id, Model model)
	{
		if (countyService.existsByCountyId(id)) {
			County county = countyService.findCounty(id);
			model.addAttribute("county", county);
			return "county";
		}
		model.addAttribute("id", id);
		return "notfounderror";
	}
	
	@GetMapping("/newcounty")
	public String addNewCountyForm(Model model)
	{
		model.addAttribute("aCountyForm", new CountyForm());
		return "newcounty";
	}
	
	@PostMapping("/newcounty")
	public String addNewCountySave(@Valid @ModelAttribute("aCountyForm") CountyForm countyForm, BindingResult binding, RedirectAttributes redirectAttributes, Principal user)
	{
		if (binding.hasErrors())
			return "newcounty";
		MyUser creator = userService.findByEmail(user.getName());
		County county = new County(countyForm.getCountyName(), creator);
		County newCounty = countyService.save(county);
		if (newCounty != null)
			return "redirect:county/"+newCounty.getCountyId();
		else
			// starts again with empty form (new object)
			redirectAttributes.addFlashAttribute("duplicate", true);
			return "redirect:newcounty";	
	}
	

}
