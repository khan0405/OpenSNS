package com.khan.opensns.model.persistence;

public enum FeedType {
	TEXT(0), IMAGE(1), MOVIE(2), LINK(3), LOCATION(4);
	private int value;
	
	private FeedType(int value) {
		this.value = value;
	}
	
	public static FeedType valueOf(int type) {
		switch (type) {
		case 0:
			return TEXT;
		case 1:
			return IMAGE;
			
		case 2:
			return MOVIE;
		case 3:
			return LINK;
		case 4:
			return LOCATION;
		default:
			return null;
		}
	}
	
	public int getValue() {
		return value;
	}

}
