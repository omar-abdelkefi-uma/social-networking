package com.project.pfe.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import com.project.pfe.validator.PasswordMatches;

import lombok.Data;

/**
 * @author Chinna
 * @since 26/3/18
 */
@Data
@PasswordMatches
public class SignUpRequest {

	private String providerUserId;

	@NotEmpty
	private String displayName;

	@NotEmpty
	private String email;

	private SocialProvider socialProvider;

	@Size(min = 6, message = "{Size.userDto.password}")
	private String password;

	@NotEmpty
	private String matchingPassword;

	private Long userID;

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SocialProvider getSocialProvider() {
		return socialProvider;
	}

	public void setSocialProvider(SocialProvider socialProvider) {
		this.socialProvider = socialProvider;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public SignUpRequest(String providerUserId, String displayName, String email, String password,
			SocialProvider socialProvider) {
		this.providerUserId = providerUserId;
		this.displayName = displayName;
		this.email = email;
		this.password = password;
		this.socialProvider = socialProvider;
	}

	public static Builder getBuilder() {
		return new Builder();
	}

	public static class Builder {
		private String providerUserID;
		private String displayName;
		private String email;
		private String password;
		private SocialProvider socialProvider;

		public Builder() {
			super();
		}

		public Builder addProviderUserID(final String userID) {
			this.providerUserID = userID;
			return this;
		}

		public Builder addDisplayName(final String displayName) {
			this.displayName = displayName;
			return this;
		}

		public Builder addEmail(final String email) {
			this.email = email;
			return this;
		}

		public Builder addPassword(final String password) {
			this.password = password;
			return this;
		}

		public Builder addSocialProvider(final SocialProvider socialProvider) {
			this.socialProvider = socialProvider;
			return this;
		}

		public SignUpRequest build() {
			return new SignUpRequest(providerUserID, displayName, email, password, socialProvider);
		}

		public Builder(String providerUserID, String displayName, String email, String password,
				SocialProvider socialProvider) {
			super();
			this.providerUserID = providerUserID;
			this.displayName = displayName;
			this.email = email;
			this.password = password;
			this.socialProvider = socialProvider;
		}

		public String getProviderUserID() {
			return providerUserID;
		}

		public void setProviderUserID(String providerUserID) {
			this.providerUserID = providerUserID;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public SocialProvider getSocialProvider() {
			return socialProvider;
		}

		public void setSocialProvider(SocialProvider socialProvider) {
			this.socialProvider = socialProvider;
		}

	}
}
