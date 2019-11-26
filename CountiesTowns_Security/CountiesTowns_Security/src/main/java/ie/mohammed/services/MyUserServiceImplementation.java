package ie.mohammed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.mohammed.dao.MyUserDao;
import ie.mohammed.entities.MyUser;
@Service
public class MyUserServiceImplementation implements MyUserService{
	@Autowired
	private MyUserDao myUserDao;
	
	@Override
	public MyUser save(MyUser newUser) {
		if (myUserDao.existsByUserEmail(newUser.getUserEmail()))
				return null;
		return myUserDao.save(newUser);
	}

	@Override
	public MyUser findByEmail(String email) {
		if (myUserDao.existsByUserEmail(email))
			return myUserDao.findByUserEmail(email);
		return null;
	}
	
}
