package com.pradeep.springboot.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.internal.CustomizerRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.springboot.rest.model.User;
import com.pradeep.springboot.rest.service.UserService;
import com.pradeep.springboot.rest.util.RestCustomError;

/**
 * 
 * @author Pradeep
 *
 */

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> allUsers(){
		List<User> users = userService.getAllUsers();
		if(users.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/id/{userId}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserById(@PathVariable("userId") long id){
		User user = userService.getUserById(id);
		
		if(user == null){
			return new ResponseEntity<RestCustomError>(new RestCustomError("User with id: "+id+" not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/name/{userName}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserByName(@PathVariable("userName") String name){
		User user = userService.getUserByName(name);
		if(user != null){
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<RestCustomError>(new RestCustomError("user with name : "+name+" not found"), HttpStatus.NOT_FOUND);
	}
	@RequestMapping(value = "/create/user", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user){
		
		if(userService.isUserExists(user)){
			return new ResponseEntity<RestCustomError>(new RestCustomError("user with name : "+user.getName()+" already exists."), HttpStatus.CONFLICT);
		}
		userService.saveUser(user);
		return new ResponseEntity<RestCustomError>(new RestCustomError("user with name : "+user.getName()+" created successfuly."), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update/user", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody User user){
		userService.updateUser(user);
		return new ResponseEntity<>(new RestCustomError("User with name : "+user.getName()+" updated successfully."), HttpStatus.OK);
	}
	@RequestMapping(value = "/delete/users", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAllUsers(){
		userService.deleteAllUsers();
		return new ResponseEntity<RestCustomError>(new RestCustomError("All users deleted successfully."), HttpStatus.OK);
	}
	
}
