package com.prade.sws.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users=new ArrayList<>();
	private static int usersCount = 3;
	// create a static list
	static {
		users.add(new User(1,"Dave",new Date()));
		users.add(new User(2,"Eve",new Date()));
		users.add(new User(3,"Tom",new Date()));
	}
	
	//getAllUser
	public List<User> getAllUsers(){
		return users;
	}
	
	//getAUser
	public User getAUser(int id) {
		for (User user: users)
		{
			if(user.getId()==id) {
				return user;
			}
		}	
		return null;
	}
	
	//createAUser
	public User createAUser(User user) {
		if(user.getId()==null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	//deleteUser
	public User deleteUser(int id) {
	
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user =iterator.next();
			if(user.getId()==id)
			{
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
