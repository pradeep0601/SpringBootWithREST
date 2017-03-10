package com.pradeep.springboot.rest.service;

import java.util.List;

import com.pradeep.springboot.rest.model.User;

/**
 * 
 * @author Pradeep
 *
 */

public interface UserService {

	public User getUserById(long id);

	public User getUserByName(String name);

	public List<User> getAllUsers();

	public void updateUser(User user);

	public void deleteUserById(long id);

	public void deleteAllUsers();

	public void saveUser(User user);

	public boolean isUserExists(User user);

}
