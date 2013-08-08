package com.khan.opensns.web.model.persistence;


public enum ReadPermission {
	PUBLIC(1), FRIEND(2), GROUP(3), PRIVATE(4);
	int type;
	ReadPermission(int type) {
		this.type = type;
	}
	
	public static ReadPermission valueOf(int type) {
		switch (type) {
		case 1:
			return PUBLIC;
		case 2:
			return FRIEND;
		case 3:
			return GROUP;
		case 4:
			return PRIVATE;
		default:
			return null;
		}
	}
	
	public int getType() {
		return type;
	}
}
