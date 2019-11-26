package ie.mohammed.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.mohammed.entities.MyApiUser;

public interface MyApiUserDao extends JpaRepository<MyApiUser, Integer> {
	boolean existsByUserEmail(String email);
}
