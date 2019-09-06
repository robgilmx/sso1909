package com.ksquareinc.sso1909.domain;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class UserRole {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;	

	UserRole() { 
	}

	public UserRole(String name) { 
		this.name = name; 
	} 
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() { 
		return name; 
	} 

	public void setName(String name) { 
		this.name = name; 
	} 
}
