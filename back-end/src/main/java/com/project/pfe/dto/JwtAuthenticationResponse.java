package com.project.pfe.dto;

import lombok.Value;

@Value
public class JwtAuthenticationResponse {
	private String accessToken;
	private UserInfo user;
	
	public JwtAuthenticationResponse(String accessToken, UserInfo user) {
		super();
		this.accessToken = accessToken;
		this.user = user;
	}
	public String getAccessToken() {
		return accessToken;
	}
	
	public UserInfo getUser() {
		return user;
	}
	
	
}