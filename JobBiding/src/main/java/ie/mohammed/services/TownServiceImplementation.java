package ie.mohammed.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.mohammed.dao.TownDao;
import ie.mohammed.entities.Town;

@Service
public class TownServiceImplementation implements TownService{

	@Autowired
	private TownDao townDao;
	
	@Override
	public List<Town> findTownsAlphabticalOrder() {
		return  townDao.findAllByOrderByTownNameAsc();
	}

	@Override
	public List<Town> getTownsWithSameName(String townName) {
		return townDao.getByTownNameIgnoringCase(townName);
	}

	@Override
	public Town findTown(int townId) {
		if (townDao.existsById(townId))
			return townDao.findById(townId).get();
		return null;
	}

	@Override
	public Town save(Town town) {
		if (townDao.existsByTownNameAndCounty_CountyId(town.getTownName(), town.getCounty().getCountyId()))
			return null;
		return townDao.save(town);	
	}

	@Override
	public List<Town> findTownByTownName(String townName) {
		return townDao.findTownByTownName(townName);
	}
	
}
