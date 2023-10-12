package org.pritam.restCountries.controller;

import org.pritam.restCountries.entity.User;
import org.pritam.restCountries.services.UserService;
import org.pritam.restCountries.model.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
@Api(tags = "1. User Endpoints", description = "Contain Info On Endpoints For User Creation, Modification And Deletion.")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestBody User user){
		try {
			if(user.getUsername()==null)
				return new ResponseEntity<Object>(new Error(400,"username attribute is null."),HttpStatus.BAD_REQUEST);
			else if(user.getPassword()==null)
				return new ResponseEntity<Object>(new Error(400,"password attribute is null."),HttpStatus.BAD_REQUEST);
			userService.saveUser(user);
		}
		catch(Exception e) {
			return new ResponseEntity<Object>(new Error(500,e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>("New User Successfully Created.",HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestBody User user){
		try {
			if(user.getUsername()==null)
				return new ResponseEntity<Object>(new Error(400,"username attribute is null."),HttpStatus.BAD_REQUEST);
			else if(user.getPassword()==null)
				return new ResponseEntity<Object>(new Error(400,"password attribute is null."),HttpStatus.BAD_REQUEST);
			else if(userService.getUserByUsername(user.getUsername())==null)
				return new ResponseEntity<Object>(new Error(404,"No such user exists."),HttpStatus.NOT_FOUND);
			userService.saveUser(user);
		}
		catch(Exception e) {
			return new ResponseEntity<Object>(new Error(500,e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>("User Successfully Updated.",HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{username}")
	public ResponseEntity<Object> delete(@PathVariable("username") String username){
		try {
			if(username==null)
				return new ResponseEntity<Object>(new Error(400,"username is missing in the URL."),HttpStatus.BAD_REQUEST);
			else if(userService.getUserByUsername(username)==null)
				return new ResponseEntity<Object>(new Error(404,"No such user exists."),HttpStatus.NOT_FOUND);
			userService.deleteUserByUserName(username);
		}
		catch(Exception e) {
			return new ResponseEntity<Object>(new Error(500,e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>("User Successfully Deleted.",HttpStatus.OK);
	}
}
