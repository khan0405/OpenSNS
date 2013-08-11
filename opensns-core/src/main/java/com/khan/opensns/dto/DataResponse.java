package com.khan.opensns.dto;

public class DataResponse extends ApiResponse {

	private Object data;
	
	public DataResponse() {
		super();
	}
	
	public DataResponse(int result) {
		super(result);
	}
	
	public DataResponse(Object data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
