package ie.mohammed.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import ie.mohammed.entities.Town;

public interface TownDao extends JpaRepository<Town, Integer> {
	List<Town> findAllByOrderByTownNameAsc();
	List<Town> findByCounty_CountyName(String countyName);
	List<Town> findByCounty_CountyId(int countyId);
	List<Town> getByTownNameIgnoringCase(String townName);
	List<Town> getByTownNameIsStartingWith(String s);
	boolean existsByTownNameAndCounty_CountyId(String townName, int countyId);
	List<Town> findTownByTownName(String townName);
}
