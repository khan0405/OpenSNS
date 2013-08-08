package com.khan.opensns.web.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.khan.opensns.web.dao.FeedReplyDao;
import com.khan.opensns.web.model.FeedReply;

@Repository
public class FeedReplyDaoHibernate extends GenericDaoHibernate<FeedReply, Long> implements FeedReplyDao {

	public FeedReplyDaoHibernate() {
		super(FeedReply.class);
	}
	
}
