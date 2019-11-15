package ie.mohammed.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ie.mohammed.dao.UserDao;
import ie.mohammed.dao.UserRoleDao;
import ie.mohammed.model.User;
import ie.mohammed.model.UserRole;
import ie.mohammed.services.UserService;

/**
 * Implementing the UserService interface
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public List<User> findAll() {

		return userDao.findAll();
	}

	@Override
	public Optional<User> findByUserName(String userName) {

		return userDao.findByUserName(userName);
	}

	@Override
	public List<User> findByEnabled(Boolean enabled) {

		return userDao.findByEnabled(enabled);
	}

	@Override
	public User save(User user) {

		return userDao.save(user);
	}

	@Override
	public void deleteByUserName(String userName) {
		userDao.deleteById(userName);

	}

	@Override
	public void enable(Boolean enable, String userName) {
		User user = findByUserName(userName).get();
		if (!userRoleDao.findByUserAndRole(user, "ROLE_USER").isPresent()) {
			UserRole userRole = new UserRole("ROLE_USER");
			userRole.setUser(user);
			userRoleDao.save(userRole);
		}

	}

	@Override
	public void promote(String userName) {
		User user = userDao.findByUserName(userName).get();
		if (!userRoleDao.findByUserAndRole(user, "ROLE_MANAGER").isPresent()) {
			UserRole role = new UserRole("ROLE_MANAGER");
			role.setUser(user);
			userRoleDao.save(role);
		}

	}

	@Override
	public void demote(String userName) {
		User user = userDao.findByUserName(userName).get();
		if (userRoleDao.findByUserAndRole(user, "ROLE_MANAGER").isPresent()) {
			userRoleDao.deleteByUserAndRole(user, "ROLE_MANAGER");
		}
	}

}
