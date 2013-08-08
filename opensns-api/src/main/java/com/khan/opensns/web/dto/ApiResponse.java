package com.khan.opensns.web.dto;

public class ApiResponse implements BodyResponse {
	private int result;

	public ApiResponse() {
		this(200);
	}
	
	public ApiResponse(int result) {
		setResult(result);
	}
	
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
}
