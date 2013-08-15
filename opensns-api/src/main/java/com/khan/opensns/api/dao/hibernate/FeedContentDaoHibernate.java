package com.khan.opensns.api.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.khan.opensns.api.dao.FeedContentDao;
import com.khan.opensns.model.FeedContent;

@Repository
public class FeedContentDaoHibernate extends GenericDaoHibernate<FeedContent, Long> implements FeedContentDao {

	public FeedContentDaoHibernate() {
		super(FeedContent.class);
	}

}
