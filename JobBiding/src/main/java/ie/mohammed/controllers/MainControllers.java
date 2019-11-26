package ie.mohammed.controllers;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ie.mohammed.services.MyUserService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainControllers {
	
	@Autowired
	private MyUserService userService;
	
	@GetMapping(value= {"/", "/index"})
	public String handleIndexRequest(Model model, Principal user) {
		log.info("USER: {}", user);
		if (user != null) {
			String email =  user.getName();
			String firstname = userService.findByEmail(email).getFirstname();
			model.addAttribute("firstname", firstname);
		}
		return "index";
	}
}