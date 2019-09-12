package com.ksquareinc.sso1909.repository;

import com.ksquareinc.sso1909.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
}
