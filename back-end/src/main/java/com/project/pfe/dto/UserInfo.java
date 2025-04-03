package com.project.pfe.dto;

import java.util.List;

import lombok.Value;

@Value
public class UserInfo {
	private String id, displayName, email;
	private List<String> roles;
	
	
	public UserInfo(String id, String displayName, String email, List<String> roles) {
		super();
		this.id = id;
		this.displayName = displayName;
		this.email = email;
		this.roles = roles;
	}
	public String getId() {
		return id;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public String getEmail() {
		return email;
	}

	public List<String> getRoles() {
		return roles;
	}

	
}