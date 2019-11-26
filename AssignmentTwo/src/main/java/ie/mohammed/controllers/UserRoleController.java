package ie.mohammed.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ie.mohammed.services.UserService;

@Controller
@RestController
@RequestMapping(value = "/api/userRoles")
public class UserRoleController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(params = "username", method = RequestMethod.GET)
    ResponseEntity findUserRoles(@RequestParam("username") String username) {
        if (userService.findByUserName(username).isPresent()) {
             return ResponseEntity.ok(userService.findByUserName(username).get().getRoles());
        }
         return ResponseEntity.notFound().build();
    }

}
