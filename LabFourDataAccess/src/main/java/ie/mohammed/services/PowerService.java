package ie.mohammed.services;

import java.util.List;

import ie.mohammed.domain.Power;

public interface PowerService {

	int CountThePowers();
	int CountPowersStartingWithLetter(char letter);
	int PowerByName(String powerName);
	List<Power> findAllPowers();
	Power findPower(int powerId);
	
	//void generateReport(String fileName);
	//void insertPower(String powerName);
	void savePower(String powerName);
	
	int changePowerName(String oldName, String newName);
}
