package com.khan.opensns.web.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.khan.opensns.dao.UserPropertyDao;
import com.khan.opensns.model.UserProperty;

@Repository
public class UserPropertyDaoHibernate extends GenericDaoHibernate<UserProperty, Long> implements UserPropertyDao {

	public UserPropertyDaoHibernate() {
		super(UserProperty.class);
	}

}
