package com.ksquareinc.sso1909.domain;

import com.ksquareinc.sso1909.domain.enums.RoleEnum;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity 
@Table(name = "users")
public class User implements Serializable {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	
	private String username;

	@JsonIgnore
	private String password;

	@ElementCollection(targetClass=RoleEnum.class, fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@CollectionTable(name="user_role")
	@Column
	private List<RoleEnum> roles;

	private Boolean locked = false;

	private Boolean enabled = true;

    @Transient
    private static final long serialVersionUID = 1L;

	public User() {
	} 

	public User(String username, String password, List<RoleEnum> roles) {
		this.username = username; 
		this.password = password; 
		this.roles = roles; 
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

	public String getPassword() { 
		return password; 
	} 

	public void setPassword(String password) { 
		this.password = password; 
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
