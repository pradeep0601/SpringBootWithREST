package com.pradeep.springboot.rest.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.pradeep.springboot.rest.model.User;

/**
 * 
 * @author Pradeep
 *
 */

@Service
public class UserServiceImpl implements UserService {

	private static List<User> users;
	private static final AtomicLong userIdCounter = new AtomicLong();
	static {
		users = populateUsers();
	}

	@Override
	public User getUserById(long id) {
		for (User user : users) {
			if (id == user.getId()) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User getUserByName(String name) {
		for (User user : users) {
			if (name.equalsIgnoreCase(user.getName())) {
				return user;
			}
		}
		return null;
	}

	@Override
	public List<User> getAllUsers() {

		return users;
	}

	@Override
	public void updateUser(User user) {
		int indexOfUpdatedUser = users.indexOf(user);
		users.set(indexOfUpdatedUser, user);
	}

	@Override
	public void deleteUserById(long id) {
		Iterator<User> itr = users.iterator();
		while (itr.hasNext()) {
			User user = itr.next();
			if (user.getId() == id) {
				itr.remove();
			}
		}

	}

	@Override
	public void deleteAllUsers() {
		users.clear();
	}

	@Override
	public void saveUser(User user) {

		user.setId(userIdCounter.incrementAndGet());
		users.add(user);
	}

	@Override
	public boolean isUserExists(User user) {

		return getUserByName(user.getName()) != null;
	}

	private static List<User> populateUsers() {
		List<User> users = new ArrayList<>();
		users.add(new User(userIdCounter.incrementAndGet(), "Pradeep", 30, 2000));
		users.add(new User(userIdCounter.incrementAndGet(), "Susil", 29, 3490));
		users.add(new User(userIdCounter.incrementAndGet(), "Saurav", 28, 6762));
		users.add(new User(userIdCounter.incrementAndGet(), "Rakesh", 26, 3876));
		return users;
	}
}
