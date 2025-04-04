package com.project.pfe.dto;

/**
 * @author Chinna
 * @since 26/3/18
 */
public enum SocialProvider {

	TWITTER("twitter"), GOOGLE("google"), GITHUB("github"), LOCAL("local");

	private String providerType;

	public String getProviderType() {
		return providerType;
	}

	SocialProvider(final String providerType) {
		this.providerType = providerType;
	}

}
