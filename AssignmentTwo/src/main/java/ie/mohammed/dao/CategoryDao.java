package ie.mohammed.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.mohammed.model.Category;

/**
 * Repository for data management in a table "categories"
 */

public interface CategoryDao extends JpaRepository<Category, String> {

	Optional<Category> findByName(String name);
	
}
