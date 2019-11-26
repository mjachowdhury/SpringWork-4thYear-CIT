package ie.mohammed.services;

import ie.mohammed.entities.MyUser;

public interface MyUserService {
	MyUser save(MyUser newApiUser);
	MyUser findByEmail(String email);
}
