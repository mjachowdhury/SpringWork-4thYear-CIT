package ie.mohammed.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.mohammed.model.User;

/**
 * Repository for data management in a table "users"
 */

public interface UserDao extends JpaRepository<User, String> {
	Optional<User> findByUserName(String userName);
	
	List<User> findByEnabled(Boolean enabled);

}
