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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.Base64;

import org.pritam.restCountries.model.Error;
import org.pritam.restCountries.model.Token;
import org.pritam.restCountries.security.jwt.JwtService;
@Api(tags = "2. JWT Endpoint", description = "Contains Info On Endpoint For JWT Generation.")
@RestController
@RequestMapping("/v3.1")
public class TokenGenerateController {
	private final String x="pritam";
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@ApiOperation(value = "Generate JWT",notes = "This endpoints should be passed with an authorization header having value as basic authentication token. If the token is valid then it will generate a JWT. The generated JWT will be required to use as Bearer token for Authorization header in all the endpoints available in Country Endpoints section.", produces = "application/json")
	@ApiResponses({
		@ApiResponse(code = 200, message = "The request has been successfully executed", response = Token.class),
		@ApiResponse(code = 400, message = "Authorization header is missing or it is of invalid format.",response = Error.class),
		@ApiResponse(code = 401, message = "The basic token is invalid due to incorrect credentials.",response = Error.class)
	})
	@GetMapping("/generateToken")
	public ResponseEntity<Object> generateToken(@ApiParam(value = "Authorization value should be a valid basic authentication token",defaultValue = "Basic ZGVmYXVsdDpkZWZhdWx0QDEyMzQ=") @RequestHeader(name = "Authorization",required = false) String authorization){
		
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
