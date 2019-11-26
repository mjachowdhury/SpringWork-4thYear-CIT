package ie.mohammed.services;

import java.util.List;

import ie.mohammed.entities.County;

public interface CountyService {
	
	County findCounty(int id);
	boolean deleteCounty(County county);
	boolean deleteCounty(int id);
	County findByName(String countyName);
	boolean existsByCountyId(int countyId);
	String findCountyName(int id);
	List<County> findCountiesWithTownName(String townName);
	County save(County county);
	
	List<County> listInAlphabeticalOrder();
	List<County> listAllCounties();
}
