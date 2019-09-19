package com.ksquareinc.sso1909.domain.dto;

import com.ksquareinc.sso1909.domain.User;
import com.ksquareinc.sso1909.domain.enums.RoleEnum;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {

	private Long id;

	private String username;

	private List<RoleEnum> roles;

	private Boolean locked = false;

	private Boolean enabled = true;

    private static final long serialVersionUID = 1L;

	public UserDTO() {
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.roles = user.getRoles();
		this.locked = user.isLocked();
		this.enabled = user.isEnabled();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() { 
		return username; 
	} 

	public void setUsername(String username) { 
		this.username = username; 
	} 

	public List<RoleEnum> getRoles() {
		return roles; 
	} 

	public void setRoles(List<RoleEnum> roles) {
		this.roles = roles; 
	}

	public Boolean isLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
