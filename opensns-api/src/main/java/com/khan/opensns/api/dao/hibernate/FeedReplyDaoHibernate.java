package com.khan.opensns.api.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.khan.opensns.api.dao.FeedReplyDao;
import com.khan.opensns.model.FeedReply;

@Repository
public class FeedReplyDaoHibernate extends GenericDaoHibernate<FeedReply, Long> implements FeedReplyDao {

	public FeedReplyDaoHibernate() {
		super(FeedReply.class);
	}
	
}
