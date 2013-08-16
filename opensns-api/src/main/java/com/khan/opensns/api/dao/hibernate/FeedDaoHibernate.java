package com.khan.opensns.api.dao.hibernate;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khan.opensns.api.dao.FeedDao;
import com.khan.opensns.dao.ListOrder;
import com.khan.opensns.model.Feed;
import com.khan.opensns.model.Group;
import com.khan.opensns.model.User;

@Repository
public class FeedDaoHibernate extends GenericDaoHibernate<Feed, Long> implements FeedDao {
	
	public FeedDaoHibernate() {
		super(Feed.class);
	}

	@Override @SuppressWarnings("unchecked")
	public List<Feed> getByUser(User user, long lastId, ListOrder order, int size) {
		StringBuilder hql = new StringBuilder();
		hql .append(" from Feed f ");
		
		String where = " f.user.id = :userId or f.toUser.id = :userId order by f.id ";
		
		if (lastId != 0) {
			hql	.append(" where f.id ")
				.append(order == ListOrder.NEXT ? "<" : ">")
				.append(lastId)
				.append(" and ")
				.append(where)
				.append(order == ListOrder.NEXT ? " desc " : " asc ");
		} else {
			hql.append(" where ").append(where).append(" desc ");
		}
		
		List<Feed> feeds = (List<Feed>) getSession()
				.createQuery(hql.toString())
				.setLong("userId", user.getId())
				.setMaxResults(size)
				.setCacheable(true)
				.list();
		
		if (lastId != 0 && order == ListOrder.REFRESH) {
			Collections.reverse(feeds);
		}
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
	public List<Feed> getNewsFeed(User user, long lastId, ListOrder order, int size) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select distinct f from Feed f join f.user.friends friend ");
		
		String where = " f.readPerm <= 2 and (friend.friend.id=:userId or f.user.id = :userId) order by f.id ";
		
		if (lastId != 0) {
			hql	.append(" where f.id ")
				.append(order == ListOrder.NEXT ? "<" : ">")
				.append(lastId)
				.append(" and ")
				.append(where)
				.append(order == ListOrder.NEXT ? " desc " : " asc ");
		} else {
			hql	.append(" where ").append(where).append(" desc ");
		}
		
		List<Feed> feeds = (List<Feed>) getSession()
				.createQuery(hql.toString())
				.setLong("userId", user.getId())
				.setMaxResults(size)
				.setCacheable(true)
				.list();

		if (lastId != 0 && order == ListOrder.REFRESH) {
			Collections.reverse(feeds);
		}
		return feeds;
	}

	@Override @SuppressWarnings("unchecked")
	public List<Feed> getUserFeed(User user, User target, long lastId, ListOrder order, int size) {
		StringBuilder hql = new StringBuilder();
		hql .append(" select distinct f from Feed f join f.user.friends friend ");
		
		String where = " (f.user.id = :targetId or f.toUser.id = :targetId) and ((f.readPerm <= 4 and friend.user.id=:userId) or (f.readPerm <= 2 and friend.friend.id=:userId) or (f.readPerm = 1)) order by f.id ";
		
		if (lastId != 0) {
			hql .append(" where f.id ")
				.append(order == ListOrder.NEXT ? "<" : ">")
				.append(lastId == 0 ? "" : lastId)
				.append(" and ")
				.append(where)
				.append(order == ListOrder.NEXT ? " desc " : " asc ");
		} else {
			hql	.append(" where ").append(where).append(" desc ");
		}
		
		List<Feed> feeds = (List<Feed>) getSession()
				.createQuery(hql.toString())
				.setLong("userId", user.getId())
				.setLong("targetId", target.getId())
				.setMaxResults(size)
				.setCacheable(true)
				.list();
		
		if (lastId != 0 && order == ListOrder.REFRESH) {
			Collections.reverse(feeds);
		}
		return feeds;
	}
	
}
