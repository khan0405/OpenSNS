package com.khan.opensns.api.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.khan.opensns.api.dao.GroupDao;
import com.khan.opensns.model.Group;

@Repository
public class GroupDaoHibernate extends GenericDaoHibernate<Group, Long> implements GroupDao {
	public GroupDaoHibernate() {
		super(Group.class);
	}
}
