package ie.mohammed.controllers;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import ie.mohammed.entities.Role;
import ie.mohammed.formobjects.JobForm;
import ie.mohammed.formobjects.RegisterUserForm;
import ie.mohammed.services.JobService;
import ie.mohammed.services.MyUserService;
import ie.mohammed.services.RoleService;

@Controller
public class RegisterUserController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MyUserService myUserService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/newregisteruser")
	public String addNewJobForm(Model model)
	{
		model.addAttribute("aRegisterUserForm", new RegisterUserForm());
		return "newregisteruser";
	}
	
	@PostMapping("/newregisteruser")
	public String addNewJobSave(@Valid @ModelAttribute("aRegisterUserForm") RegisterUserForm registerUserForm, BindingResult binding, RedirectAttributes redirectAttributes)
	{
		Role role = new Role(registerUserForm.getRegisterUserEmail(),"ROLE_ADMIN");
		roleService.save(role);
		
		 
		MyUser myUser = new MyUser(registerUserForm.getRegisterUserEmail(), passwordEncoder.encode(registerUserForm.getRegisterUserPassword()), role, true, registerUserForm.getRegisterUserFirstName(), registerUserForm.getRegisterUserLastName());
		if(myUserService.save(myUser) == null){
			redirectAttributes.addFlashAttribute("duplicate",true);
		}
		return "redirect:login";
		 
	}

}
