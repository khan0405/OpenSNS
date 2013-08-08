package com.khan.opensns.web.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ResponseError implements BodyResponse {
	private String error;
	private String description;

	public ResponseError() {
	}

	public ResponseError(String error, String description) {
		setError(error);
		setDescription(description);
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
