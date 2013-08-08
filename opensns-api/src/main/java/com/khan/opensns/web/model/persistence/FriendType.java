package com.khan.opensns.web.model.persistence;

public enum FriendType {
	FRIEND(0), REQUEST(1), RECIEVE(2), RECOMMEND(3), BLOCK(4), UNUSED(5);
	private int value;
	
	private FriendType(int value) {
		this.value = value;
	}
	
	public static FriendType valueOf(int type) {
		switch (type) {
		case 0:
			return FRIEND;
		case 1:
			return REQUEST;
		case 2:
			return RECIEVE;
		case 3:
			return RECOMMEND;
		case 4:
			return BLOCK;
		case 5:
			return UNUSED;
		default:
			return null;
		}
	}
	
	public int getValue() {
		return value;
	}
}
