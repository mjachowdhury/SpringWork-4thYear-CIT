package ie.mohammed.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ie.mohammed.model.Category;
import ie.mohammed.services.CategoryService;

@Controller
@RestController
@RequestMapping(value = "/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	/**
     * Map /categories GET requests
     * Find all categories
     * @return - JSON with found categories
     */
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }
	
    /**
     * Map /categories POST requests
     * Save category
     * If category with specified name is present yet - response with UnprocessableEntity status
     * @param category
     * @return - JSON with saved category
     */
    
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity save(@RequestBody Category category) {
    	if(categoryService.findByName(category.getName()).isPresent()) {
    		return ResponseEntity.unprocessableEntity().build();
    		
    	}
    	return ResponseEntity.ok(categoryService.save(category));    	 
    }

    /**
     * Map /categories?delete= DELETE requests
     * Delete category by name
     * If category not found - response with NotFound status
     * @param category
     * @return - status Ok
     */
    @RequestMapping(params = "delete", method = RequestMethod.DELETE)
    ResponseEntity delete(@RequestParam("delete") String category) {
         if (categoryService.findByName(category).isPresent()) {
            categoryService.deleteByName(category);
             return ResponseEntity.ok().build();
        }
         return ResponseEntity.notFound().build();
    }
}
