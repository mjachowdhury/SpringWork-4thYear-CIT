package ie.mohammed.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.mohammed.model.User;
import ie.mohammed.model.UserRole;

/**
 * Repository for data management in a table "user_roles"
 */

public interface UserRoleDao extends JpaRepository<UserRole, Integer> {

	Optional<UserRole> findByUserAndRole(User user, String role);

    void deleteByUserAndRole(User user, String role);
}
