package com.khan.opensns.api.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.khan.opensns.api.dao.GroupUserDao;
import com.khan.opensns.model.GroupUser;

@Repository
public class GroupUserDaoHibernate extends GenericDaoHibernate<GroupUser, GroupUser.Id> implements GroupUserDao {
	public GroupUserDaoHibernate() {
		super(GroupUser.class);
	}
}
