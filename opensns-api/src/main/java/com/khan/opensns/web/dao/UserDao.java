package com.khan.opensns.web.dao;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.khan.opensns.web.model.User;
import com.khan.opensns.web.service.UserNotFoundException;

public interface UserDao extends GenericDao<User, Long> {
	public User loadByUsername(String username) throws UsernameNotFoundException;

	public User loadByName(String name) throws UserNotFoundException;

	public User loadByAuthKey(String authKey) throws UserNotFoundException;
}
