package ie.mohammed.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import ie.mohammed.entities.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {
	boolean existsByUserEmail(String email);
}
