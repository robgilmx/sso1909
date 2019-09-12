package com.ksquareinc.sso1909.service;

import com.ksquareinc.sso1909.domain.User;
import com.ksquareinc.sso1909.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
    PasswordEncoder passwordEncoder;

	@Override
	public List<User> getUsers() {
		return new ArrayList<User>(userRepository.findAll());
	}
	
	@Override
	public User getUser(Long id) {
		Optional<User> optionalUser;
		optionalUser = userRepository.findById(id);
		return (optionalUser.get());
	}

	@Override
	public User getUser(String username) {
		return (userRepository.findByUsername(username));
	}

	@Override
	public User addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return user;
	}

	@Override
	public void deleteUser(String username) {
		userRepository.delete(userRepository.findByUsername(username));		
	}

	@Override
	public User updateUser(String username, User user) {
		User userToUpdate = userRepository.findByUsername(username);
		if(user.getUsername() != null) {
			userToUpdate.setUsername(user.getUsername());
		}
		if(user.getPassword() != null) {
			userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		if(user.getRoles() != null) {
			userToUpdate.setRoles(user.getRoles());
		}
		return (userRepository.save(userToUpdate));
	}
	
	@Override
	public List<String> areUsers(List<String> usernameList){
		List<User> existingUsers = new ArrayList<User>();
		userRepository.findAll().iterator().forEachRemaining(existingUsers::add);
		for (User user : existingUsers) {
			if(usernameList.contains(user.getUsername())) {
				usernameList.remove(user.getUsername());
			}
		}
		return usernameList;
	}


	
}
