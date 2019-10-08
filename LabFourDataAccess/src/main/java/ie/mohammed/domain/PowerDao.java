package ie.mohammed.domain;

import java.util.List;

public interface PowerDao {
	
	int getPowerCount();
	int getStartingWithLetterCount(char letter);
	int getPowerByName(String powerName);
	List<Power> findAll();
	Power findById(int powerId);
	boolean isIdInTable(int id);
	void generateReportOfPowers(String fileName);
	void insertPower(String powerName);
	void addAPower(String powerName, String powerDescription);
	int changePowerName(String oldName, String newName);
	

}
