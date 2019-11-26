package ie.mohammed.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ie.mohammed.entities.County;

public interface CountyDao extends JpaRepository<County, Integer> {
	County findByCountyName(String countyName);
	boolean existsByCountyName(String countyName);
	List<County> findAllByOrderByCountyNameAsc();
	
	@Query("SELECT c.countyName FROM County c where c.countyId = :id") 
	String findNameOfCountyById(@Param("id") int id);

	@Query("SELECT c FROM County c JOIN Town t ON t.county=c WHERE t.townName=:townName")
	List<County> findCountiesWithTownName(@Param("townName") String townName);
}
