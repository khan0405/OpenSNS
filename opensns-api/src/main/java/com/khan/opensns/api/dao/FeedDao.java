package com.khan.opensns.api.dao;

import java.util.List;

import com.khan.opensns.dao.ListOrder;
import com.khan.opensns.model.Feed;
import com.khan.opensns.model.Group;
import com.khan.opensns.model.User;

public interface FeedDao extends BaseDao<Feed, Long> {
	public List<Feed> getByUser(User user, long lastId, ListOrder order, int size);
	public List<Feed> getByGroup(Group group, int page, int size);
	public List<Feed> getPopular(int page, int size);
	public List<Feed> getNewsFeed(User user, long lastId, ListOrder order, int size);
	public List<Feed> getUserFeed(User user, User target, long lastId, ListOrder order, int size);
}
