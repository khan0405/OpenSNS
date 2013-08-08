package com.khan.opensns.web.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.khan.opensns.web.dao.FeedContentDao;
import com.khan.opensns.web.model.FeedContent;

@Repository
public class FeedContentDaoHibernate extends GenericDaoHibernate<FeedContent, Long> implements FeedContentDao {

	public FeedContentDaoHibernate() {
		super(FeedContent.class);
	}

}
