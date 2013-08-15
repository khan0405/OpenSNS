package com.khan.opensns.api.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.khan.opensns.api.dao.UserInfoDao;
import com.khan.opensns.model.UserInfo;

@Repository
public class UserInfoDaoHibernate extends GenericDaoHibernate<UserInfo, Long> implements UserInfoDao {
	public UserInfoDaoHibernate() {
		super(UserInfo.class);
	}
}
