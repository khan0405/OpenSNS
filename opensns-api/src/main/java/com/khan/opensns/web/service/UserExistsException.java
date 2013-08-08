/**
 * Project		: BookMarkerWeb
 * FileName		: UserExistsException.java
 * Package		: com.khan.bookmarker.service
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 6. 25. 오후 1:18:26
 */
package com.khan.opensns.web.service;

/**
 * <PRE>
 * @class	: com.khan.bookmarker.service.UserExistsException
 * @file	: UserExistsException.java
 * @brief	: 
 * @author	: KHAN
 * @date	: 2013. 6. 25. 오후 1:18:26
 * </PRE>
 */
public class UserExistsException extends Exception {

	private static final long serialVersionUID = -2269860305059462452L;

	public UserExistsException(String message) {
		super(message);
	}
	
	public UserExistsException(String message, Throwable e) {
		super(message, e);
	}
	
	public UserExistsException(Throwable e) {
		super(e);
	}

}
