package com.project.pfe.dto;

import lombok.Value;

@Value
public class ApiResponse {
	private Boolean success;
	private String message;

	public ApiResponse(Boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

}