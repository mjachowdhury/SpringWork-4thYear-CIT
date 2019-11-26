package ie.mohammed;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ie.mohammed.entities.Town;
import ie.mohammed.services.TownService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SchedulingTasks {
	
	@Autowired
	TownService townService;
	
	// At 14:56 every day....
	@Scheduled(cron = "0 56 14 * * *")
	public void closeProjects()
	{
		log.info("Hello it is time for tea");
	}
	
	// Every 50000ms print the list of towns
	@Scheduled(fixedRate = 50000)
	public void listProjects() {
		List<Town> towns = townService.findTownsAlphabticalOrder();
		for (Town t: towns)
			log.info(t.getTownName());
	}
}
