package ie.mohammed.services;

import java.util.List;
import java.util.Optional;

import ie.mohammed.model.Job;

/**
 * Service for data management in a table "job"
 */

public interface JobService {

	/**
     * Find all products
     * @return list of found products
     */
    List<Job> findAll();

    /**
     * Find product by ID
     * @param id
     * @return Optional of found product
     */
    Optional<Job> findById(int id);

    /**
     * Save product in DB
     * @param product
     * @return saved product
     */
    Job save(final Job product);

    /**
     * Delete product by ID
     * @param id
     */
    void deleteById(int id);
    
}
