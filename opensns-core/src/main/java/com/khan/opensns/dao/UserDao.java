package com.khan.opensns.dao;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.khan.opensns.model.User;
import com.khan.opensns.service.UserNotFoundException;

public interface UserDao extends GenericDao<User, Long> {
	public User loadByUsername(String username) throws UsernameNotFoundException;

	public User loadByName(String name) throws UserNotFoundException;

	public User loadByAuthKey(String authKey) throws UserNotFoundException;
}
