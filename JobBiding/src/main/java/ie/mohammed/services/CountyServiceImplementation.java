package ie.mohammed.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.mohammed.dao.CountyDao;
import ie.mohammed.entities.County;

@Service
public class CountyServiceImplementation implements CountyService {

	@Autowired 
	private CountyDao countyDao;
	
	@Override
	public County findCounty(int id) {
		if (countyDao.existsById(id))
			return countyDao.findById(id).get();
		return null;
	}

	@Override
	public boolean deleteCounty(int id) {
		if (countyDao.existsById(id))
		{
			countyDao.deleteById(id);
			return true;
		}
		return false;

	}

	@Override
	public County findByName(String countyName) {
		if (countyDao.existsByCountyName(countyName))
			return countyDao.findByCountyName(countyName);
		return null;
	}

	@Override
	public String findCountyName(int id) {
		if (countyDao.existsById(id))
			return countyDao.findNameOfCountyById(id);
		return null;
	}

	@Override
	public List<County> findCountiesWithTownName(String townName) {
		return countyDao.findCountiesWithTownName(townName);
	}

	@Override
	public County save(County county) {
		if (countyDao.existsByCountyName(county.getCountyName()))
			return null;
		return countyDao.save(county);
	}

	@Override
	public boolean deleteCounty(County county) {
		System.out.println(county);
		if (! countyDao.existsById(county.getCountyId()))
			return false;
		countyDao.delete(county);
		System.out.println(countyDao.existsByCountyName(county.getCountyName()));
		return true;
	}

	@Override
	public List<County> listAllCounties() {
		return countyDao.findAll();
	}
	
	@Override
	public List<County> listInAlphabeticalOrder() {
		return countyDao.findAllByOrderByCountyNameAsc();		
	}

	@Override
	public boolean existsByCountyId(int countyId) {
		return countyDao.existsById(countyId);
	}
}
