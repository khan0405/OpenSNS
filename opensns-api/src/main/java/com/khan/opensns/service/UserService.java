/**
 * Project		: BookMarkerWeb
 * FileName		: UserService.java
 * Package		: com.khan.bookmarker.service
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 6. 25. 오전 10:12:09
 */
package com.khan.opensns.service;

import java.io.IOException;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import com.khan.opensns.model.User;
import com.khan.opensns.vo.UserVo;

/**
 * <PRE>
 * @class	: com.khan.bookmarker.service.UserService
 * @file	: UserService.java
 * @brief	: 
 * @author	: KHAN
 * @date	: 2013. 6. 25. 오전 10:12:09
 * </PRE>
 */
public interface UserService extends UserDetailsService {

	public User getUser(Long id);
	
	public User loadUserByName(String name) throws UserNotFoundException;
	
	public User loadUserByAuthKey(String authKey) throws UserNotFoundException;
	
	public User saveUser(User user) throws UserExistsException;

	public User saveUser(UserVo user) throws UserExistsException;

	public void removeUser(Long id) throws UserNotFoundException;

	public void removeUser(String username) throws UserNotFoundException;

	public User saveUserProfile(User user, MultipartFile image) throws IOException;
	
	public User saveUserBackground(User user, MultipartFile image) throws IOException;
}