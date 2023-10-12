package org.pritam.restCountries.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.pritam.restCountries.entity.User;
import org.pritam.restCountries.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public User saveUser(User user) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return user;
	}
	
	@Transactional
	public User getUserByUsername(String username) {
		Optional<User> optionalUser = userRepository.findById(username);
		if(optionalUser.isPresent())
			return optionalUser.get();
		return null;
	}
	
	@Transactional
	public void deleteUserByUserName(String username) {
		userRepository.deleteById(username);
	}
}
