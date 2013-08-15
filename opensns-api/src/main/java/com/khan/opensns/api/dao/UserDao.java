package com.khan.opensns.api.dao;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.khan.opensns.model.User;
import com.khan.opensns.service.UserNotFoundException;

public interface UserDao extends BaseDao<User, Long> {
	public User loadByUsername(String username) throws UsernameNotFoundException;

	public User loadByName(String name) throws UserNotFoundException;

	public User loadByAuthKey(String authKey) throws UserNotFoundException;
}
