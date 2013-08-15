package com.khan.opensns.api.dao;

import java.util.List;

import com.khan.opensns.model.Friend;
import com.khan.opensns.model.User;
import com.khan.opensns.model.persistence.FriendType;

public interface FriendDao extends BaseDao<Friend, Friend.Id> {
	public List<Friend> getByFriendType(User user, FriendType type);
	public List<Friend> getByFriendType(User user, FriendType type, int page, int size);
	public List<Friend> getFriendRequestsById(Long userId, Long friendId);
}
