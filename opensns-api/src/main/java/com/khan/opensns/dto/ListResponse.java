package com.khan.opensns.dto;

public class ListResponse extends DataResponse {
	
	public boolean more = false;

	public ListResponse() {
		super();
	}

	public ListResponse(int result) {
		super(result);
	}

	public ListResponse(Object data) {
		super(data);
	}
	
	public ListResponse(Object data, boolean more) {
		super(data);
		this.more = more;
	}

	public boolean isMore() {
		return more;
	}

	public void setMore(boolean more) {
		this.more = more;
	}
}
