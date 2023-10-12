package org.pritam.restCountries.security;

import org.pritam.restCountries.entity.User;
import org.pritam.restCountries.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Value("${security.default.username}")
	private String defaultUsername;
	@Value("${security.default.password}")
	private String defaultPassword;
	@Value("${security.default.enabled}")
	private boolean defaultEnabled;

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(defaultEnabled && username.equals(defaultUsername))
			return new UserDetailsImpl(new User(defaultUsername,new BCryptPasswordEncoder().encode(defaultPassword),defaultEnabled));
		User user = userService.getUserByUsername(username);
		if(user==null)
			throw new UsernameNotFoundException("Username not found");
		return new UserDetailsImpl(user);
	}
}
