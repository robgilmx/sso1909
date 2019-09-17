package com.ksquareinc.sso1909.service;

import com.ksquareinc.sso1909.domain.User;

import java.io.Serializable;
import java.util.List;


public interface UserService extends Serializable {
	List<User> getUsers();
	User getUser(Long id);
	User getUser(String username);
	User addUser(User user);
	void deleteUser(String username);
	User updateUser(String username, User user);
	List<String> areUsers(List<String> usernameList);
}
