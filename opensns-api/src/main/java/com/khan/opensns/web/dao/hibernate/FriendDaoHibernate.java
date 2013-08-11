package com.khan.opensns.web.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khan.opensns.dao.FriendDao;
import com.khan.opensns.model.Friend;
import com.khan.opensns.model.User;
import com.khan.opensns.model.persistence.FriendType;

@Repository
public class FriendDaoHibernate extends GenericDaoHibernate<Friend, Friend.Id> implements FriendDao {
	public FriendDaoHibernate() {
		super(Friend.class);
	}

	@Override @SuppressWarnings("unchecked")
	public List<Friend> getByFriendType(User user, FriendType type) {
		List<Friend> friends = (List<Friend>) getSession()
				.createQuery("from Friend f where f.id.userId = :userId and f.type = :type")
				.setLong("userId", user.getId())
				.setInteger("type", type.getValue())
				.list();
		return friends;
	}

	@Override @SuppressWarnings("unchecked")
	public List<Friend> getByFriendType(User user, FriendType type, int page, int size) {
		List<Friend> friends = (List<Friend>) getSession()
				.createQuery("from Friend f where f.id.userId = :userId and f.type = :type")
				.setLong("userId", user.getId())
				.setInteger("type", type.getValue())
				.setFirstResult(page * size)
				.setMaxResults(size)
				.setCacheable(true)
				.list();
		return friends;
	}

	@Override @SuppressWarnings("unchecked")
	public List<Friend> getFriendRequestsById(Long userId, Long friendId) {
		Friend.Id uId = new Friend.Id(userId, friendId);
		Friend.Id fId = new Friend.Id(friendId, userId);
		List<Friend> friends = (List<Friend>) getSession()
				.createQuery("from Friend f where f.id = :uId or f.id = fId")
				.setEntity("uId", uId)
				.setEntity("fId", fId)
				.setCacheable(true)
				.list();
		return friends;
	};
}
