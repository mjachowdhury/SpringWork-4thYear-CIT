package ie.mohammed.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.mohammed.model.Job;

/**
 * Repository for data management in a table "products"
 */

public interface JobDao extends JpaRepository<Job, Integer> {
	

}
