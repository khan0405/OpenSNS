package com.khan.opensns.web.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.khan.opensns.web.dao.FeedLikeDao;
import com.khan.opensns.web.model.FeedLike;

@Repository
public class FeedLikeDaoHibernate extends GenericDaoHibernate<FeedLike, Long> implements FeedLikeDao {

	public FeedLikeDaoHibernate() {
		super(FeedLike.class);
	}
	
}
