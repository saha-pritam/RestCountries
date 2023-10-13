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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
@Api(tags = "1. User Endpoints", description = "Contain Info On Endpoints For User Creation, Modification And Deletion.")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "Add User",notes = "This endpoints accepts a JSON payload which is of type user and adds the user to the database. Make sure the attributes username and password are mentioned in the payload. The attibute enabled is optional. The default value of enabled is false.",consumes = "application/json", produces = "application/json,text/plain")
	@ApiResponses({
		@ApiResponse(code = 200, message = "The request has been successfully executed", response = String.class),
		@ApiResponse(code = 400, message = "Either username or password attribute is missing from payload.",response = Error.class),
		@ApiResponse(code = 500, message = "It can be any kind of server error.",response = Error.class)
	})
	@PostMapping("/create")
	public ResponseEntity<Object> create(@ApiParam(value = "Please provide user details like username, password and enabled (optional).") @RequestBody User user){
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
	
	@ApiOperation(value = "Update User",notes = "This endpoints accepts a JSON payload which is of type user and modifies the existing user in the database. Make sure the attributes username and password are mentioned in the payload. The attibute enabled is optional. The default value of enabled is false.",consumes = "application/json", produces = "application/json,text/plain")
	@ApiResponses({
		@ApiResponse(code = 200, message = "The request has been successfully executed", response = String.class),
		@ApiResponse(code = 400, message = "Either username or password attribute is missing from payload.",response = Error.class),
		@ApiResponse(code = 404, message = "The username passed in the payload in not available in the database.",response = Error.class),
		@ApiResponse(code = 500, message = "It can be any kind of server error.",response = Error.class)
	})
	@PutMapping("/update")
	public ResponseEntity<Object> update(@ApiParam(value = "Please provide user details like username, password and enabled (optional).") @RequestBody User user){
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
	
	@ApiOperation(value = "Delete User",notes = "This endpoints accepts a path value which is basically the username we went to delete and it deletes the existing user in the database.", produces = "application/json,text/plain")
	@ApiResponses({
		@ApiResponse(code = 200, message = "The request has been successfully executed", response = String.class),
		@ApiResponse(code = 400, message = "Username is missing in the URL.",response = Error.class),
		@ApiResponse(code = 404, message = "The username passed in the payload in not available in the database.",response = Error.class),
		@ApiResponse(code = 500, message = "It can be any kind of server error.",response = Error.class)
	})
	@DeleteMapping("/delete/{username}")
	public ResponseEntity<Object> delete(@ApiParam(defaultValue = "einstein", value = "Please pass a username in the URL.") @PathVariable("username") String username){
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
