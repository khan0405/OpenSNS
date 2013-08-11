package com.khan.opensns.web.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khan.opensns.dao.FeedDao;
import com.khan.opensns.model.Feed;
import com.khan.opensns.model.Group;
import com.khan.opensns.model.User;

@Repository
public class FeedDaoHibernate extends GenericDaoHibernate<Feed, Long> implements FeedDao {
	
	public FeedDaoHibernate() {
		super(Feed.class);
	}

	@Override @SuppressWarnings("unchecked")
	public List<Feed> getByUser(User user, int page, int size) {
		List<Feed> feeds = (List<Feed>) getSession()
				.createQuery("from Feed f where f.id.userId = :userId or f.toUser.id = :userId order by f.createDate desc")
				.setLong("userId", user.getId())
				.setFirstResult(page * size)
				.setMaxResults(size)
				.setCacheable(true)
				.list();
		return feeds;
	}

	@Override @SuppressWarnings("unchecked")
	public List<Feed> getByGroup(Group group, int page, int size) {
		List<Feed> feeds = (List<Feed>) getSession()
				.createQuery("from Feed f where f.toGroup.id = :groupId order by f.createDate desc")
				.setLong("groupId", group.getId())
				.setFirstResult(page * size)
				.setMaxResults(size)
				.setCacheable(true)
				.list();
		return feeds;
	}

	@Override @SuppressWarnings("unchecked")
	public List<Feed> getPopular(int page, int size) {
		List<Feed> feeds = (List<Feed>) getSession()
				.createQuery("from Feed f join f.replies r join f.likes l group by f.id order by count(r.id) + count(l.id) desc, f.createDate desc")
				.setFirstResult(page * size)
				.setMaxResults(size)
				.setCacheable(true)
				.list();
		return feeds;
	}

	@Override @SuppressWarnings("unchecked")
	public List<Feed> getNewsFeed(User user, int page, int size) {
		List<Feed> feeds = (List<Feed>) getSession()
				.createQuery("select distinct f from Feed f join f.user.friends friend where f.readPerm <= 2 and (friend.friend.id=:userId or f.user.id = :userId) order by f.createDate desc")
				.setLong("userId", user.getId())
				.setFirstResult(page * size)
				.setMaxResults(size)
				.setCacheable(true)
				.list();
		return feeds;
	}

	@Override @SuppressWarnings("unchecked")
	public List<Feed> getUserFeed(User user, User target, int page, int size) {
		List<Feed> feeds = (List<Feed>) getSession()
				.createQuery("from Feed f join f.user.friends friend where ((f.readPerm = 2 and friend.user.id=:userId) or (f.readPerm = 1)) and (f.id.userId = :targetId or f.toUser.id = :targetId) order by f.createDate desc")
				.setLong("userId", user.getId())
				.setLong("targetId", target.getId())
				.setFirstResult(page * size)
				.setMaxResults(size)
				.setCacheable(true)
				.list();
		return feeds;
	}
	
	
}
