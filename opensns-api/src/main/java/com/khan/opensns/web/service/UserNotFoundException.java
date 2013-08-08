/**
 * Project		: BookMarkerWeb
 * FileName		: UserNotFoundException.java
 * Package		: com.khan.bookmarker.service
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 6. 25. 오전 10:28:55
 */
package com.khan.opensns.web.service;

/**
 * <PRE>
 * @class	: com.khan.bookmarker.service.UserNotFoundException
 * @file	: UserNotFoundException.java
 * @brief	: 
 * @author	: KHAN
 * @date	: 2013. 6. 25. 오전 10:28:55
 * </PRE>
 */
public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = -2980789085837285582L;
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
	public UserNotFoundException(String message, Throwable e) {
		super(message, e);
	}
	
	public UserNotFoundException(Throwable e) {
		super(e);
	}
}
