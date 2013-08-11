package com.khan.opensns.web.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.khan.opensns.dao.GroupInfoDao;
import com.khan.opensns.model.GroupInfo;

@Repository
public class GroupInfoDaoHibernate extends GenericDaoHibernate<GroupInfo, Long> implements GroupInfoDao {
	public GroupInfoDaoHibernate() {
		super(GroupInfo.class);
	}
}
