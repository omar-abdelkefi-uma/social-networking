package com.project.pfe.security.oauth2.user;

import java.util.Map;

public class GithubOAuth2UserInfo extends OAuth2UserInfo {

	public GithubOAuth2UserInfo(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getId() {
		return ((Integer) attributes.get("id")).toString();
	}

	@Override
	public String getName() {
		return String.valueOf( attributes.get("name"));
	}

	@Override
	public String getEmail() {
		return String.valueOf( attributes.get("email"));
	}

	@Override
	public String getImageUrl() {
		return String.valueOf( attributes.get("avatar_url"));
	}
}