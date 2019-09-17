package com.ksquareinc.sso1909.service;


import com.ksquareinc.sso1909.domain.User;
import com.ksquareinc.sso1909.domain.enums.RoleEnum;
import com.ksquareinc.sso1909.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService, Serializable {

	@Autowired
	private UserRepository userRepository;
	
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	
        User user = userRepository.findByUsername(userName);
        if(user == null){
            throw new UsernameNotFoundException("UserName "+userName+" not found");
        }
        return new com.ksquareinc.sso1909.domain.UserDetails(user);
    }


}
