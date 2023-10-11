package org.pritam.restCountries.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

import org.pritam.restCountries.model.Error;
import org.pritam.restCountries.model.Token;
import org.pritam.restCountries.security.jwt.JwtService;
@RestController
@RequestMapping("/v3.1")
public class TokenGenerateController {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/generateToken")
	public ResponseEntity<Object> generateToken(@RequestHeader(name = "Authorization",required = false) String authorization){
		
		if(authorization==null) {
			return new ResponseEntity<Object>(new Error(400,"Authorization Header Is Missing."),HttpStatus.BAD_REQUEST);
		}
		else if(!authorization.startsWith("Basic ")) {
			return new ResponseEntity<Object>(new Error(400,"Authorization Header Format Is Invalid."),HttpStatus.BAD_REQUEST);
		}
		
		String credentials = new String(Base64.getDecoder().decode(authorization.substring(6)));
		String username= credentials.substring(0,credentials.indexOf(":"));
		String password= credentials.substring(credentials.indexOf(":")+1);

		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		catch(BadCredentialsException e) {
			return new ResponseEntity<Object>(new Error(401,"Invalid Credentials"),HttpStatus.UNAUTHORIZED);
		}
		
		Token token = new Token(jwtService.generateToken(userDetailsService.loadUserByUsername(username)),jwtService.getExpiry());
		return new ResponseEntity<Object>(token,HttpStatus.OK);
	}
}
