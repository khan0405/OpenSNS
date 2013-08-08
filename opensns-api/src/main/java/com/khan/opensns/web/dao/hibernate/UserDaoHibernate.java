package com.khan.opensns.web.dao.hibernate;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khan.opensns.web.dao.UserDao;
import com.khan.opensns.web.model.User;
import com.khan.opensns.web.service.UserNotFoundException;

@Repository
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements UserDao {
	
	public UserDaoHibernate() {
		super(User.class);
	}

    @Transactional(readOnly=true)
	@Override @SuppressWarnings("unchecked")
	public User loadByUsername(String username) throws UsernameNotFoundException {
		List<User> users = (List<User>)getSession().createQuery("from User u where u.username = :username")
				.setString("username", username)
				.setCacheable(true)
				.list();
		if (users == null || users.isEmpty()) {
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}
		return users.get(0);
	}

    @Transactional(readOnly=true)
	@Override @SuppressWarnings("unchecked")
	public User loadByName(String name) throws UserNotFoundException {
		List<User> users = (List<User>)getSession().createQuery("from User u where u.name = :name")
				.setString("name", name)
				.setCacheable(true)
				.list();
		if (users == null || users.isEmpty()) {
			throw new UserNotFoundException("User '" + name + "' not found");
		}
		return users.get(0);
	}

    @Transactional(readOnly=true)
	@Override @SuppressWarnings("unchecked")
	public User loadByAuthKey(String authKey) throws UserNotFoundException {
		List<User> users = (List<User>)getSession().createQuery("from User u where u.authKey = :authKey")
				.setString("authKey", authKey)
				.setCacheable(true)
				.list();
		if (users == null || users.isEmpty()) {
			throw new UserNotFoundException("User '" + authKey + "' not found");
		}
		return users.get(0);
	}
}
