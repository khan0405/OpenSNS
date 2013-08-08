package com.khan.opensns.web.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.khan.opensns.web.dao.GroupUserDao;
import com.khan.opensns.web.model.GroupUser;

@Repository
public class GroupUserDaoHibernate extends GenericDaoHibernate<GroupUser, GroupUser.Id> implements GroupUserDao {
	public GroupUserDaoHibernate() {
		super(GroupUser.class);
	}
}
