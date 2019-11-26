package ie.mohammed.services;
import java.util.List;

import ie.mohammed.entities.Town;

public interface TownService {
	List<Town> findTownsAlphabticalOrder();
	List<Town> getTownsWithSameName(String townName);
	List<Town> findTownByTownName(String townName);
	Town findTown(int townId);
	Town save(Town town);
}
