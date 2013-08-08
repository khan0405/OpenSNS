package com.khan.opensns.web.dao;

import java.util.List;

import com.khan.opensns.web.model.Friend;
import com.khan.opensns.web.model.User;
import com.khan.opensns.web.model.persistence.FriendType;

public interface FriendDao extends GenericDao<Friend, Friend.Id> {
	public List<Friend> getByFriendType(User user, FriendType type);
	public List<Friend> getByFriendType(User user, FriendType type, int page, int size);
	public List<Friend> getFriendRequestsById(Long userId, Long friendId);
}
