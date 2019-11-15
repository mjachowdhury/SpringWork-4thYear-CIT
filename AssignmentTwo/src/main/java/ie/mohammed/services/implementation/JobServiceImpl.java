package ie.mohammed.services.implementation;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ie.mohammed.dao.JobDao;
import ie.mohammed.model.Job;
import ie.mohammed.services.JobService;

/**
 * Implementing the JobService interface
 */

@Service("jobService")
@Transactional
public class JobServiceImpl implements JobService {

	@Autowired
	private JobDao jobDao;

	@Override
	public List<Job> findAll() {

		return jobDao.findAll();
	}

	@Override
	public Optional<Job> findById(int id) {

		return jobDao.findById(id);
	}

	@Override
	public Job save(Job product) {

		return jobDao.save(product);
	}

	@Override
	public void deleteById(int id) {
		jobDao.deleteById(id);

	}

}
