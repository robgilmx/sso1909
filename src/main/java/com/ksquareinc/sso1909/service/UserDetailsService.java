package com.ksquareinc.sso1909.service;


import com.ksquareinc.sso1909.domain.User;
import com.ksquareinc.sso1909.domain.enums.RoleEnum;
import com.ksquareinc.sso1909.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	
        User user = userRepository.findByUsername(userName);
        if(user == null){
            throw new UsernameNotFoundException("UserName "+userName+" not found");
        }
        return getUserDetails(user);
    }

    private UserDetails getUserDetails(User user) {
        return new UserDetails() {
            private Collection<? extends GrantedAuthority> translate(List<RoleEnum> roles) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                for (RoleEnum role : roles) {
                    String name = role.toString().toUpperCase();
                    if (!name.startsWith("ROLE_")) {
                        name = "ROLE_" + name;
                    }
                    authorities.add(new SimpleGrantedAuthority(name));
                }
                return authorities;
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return translate(user.getRoles());
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return user.isLocked();
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return user.isEnabled();
            }

        };
    }

}
