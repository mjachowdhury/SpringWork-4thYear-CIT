package ie.mohammed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.mohammed.dao.MyApiUserDao;
import ie.mohammed.entities.MyApiUser;
@Service
public class MyApiUserServiceImplementation implements MyApiUserService{

	@Autowired
	private MyApiUserDao myApiUserDao;
	
	@Override
	public MyApiUser save(MyApiUser newApiUser) {
		if (myApiUserDao.existsByUserEmail(newApiUser.getUserEmail()))
				return null;
		return myApiUserDao.save(newApiUser);
	}
	
	
}
