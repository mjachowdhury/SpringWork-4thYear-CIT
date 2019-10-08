package ie.mohammed.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.mohammed.domain.Power;
import ie.mohammed.domain.PowerDao;

@Service
public class PowerServiceImp implements PowerService {

	@Autowired
	PowerDao powerDao;

	public int changePowerName(String oldName, String newName) {
		if (powerDao.getPowerByName(oldName) == 1)
			return powerDao.changePowerName(oldName, newName);
		return 0;
	}

	public int CountThePowers() {
		return powerDao.getPowerCount();
	}

	public int CountPowersStartingWithLetter(char letter) {
		return powerDao.getStartingWithLetterCount(Character.toUpperCase(letter));
	}

	public int PowerByName(String powerName) {
		return powerDao.getPowerByName(powerName);
	}

	public List<Power> findAllPowers() {
		return powerDao.findAll();
	}

	public Power findPower(int powerId) {
		return powerDao.findById(powerId);
	}

	public void generateReport(String fileName) {
		powerDao.generateReportOfPowers(fileName);
	}

	/*
	 * public void insertPower(String powerName) { }
	 */

	public void savePower(String powerName) {
		if (powerDao.getPowerByName(powerName) == 0) {
			powerDao.insertPower(powerName);
		}

	}
}
