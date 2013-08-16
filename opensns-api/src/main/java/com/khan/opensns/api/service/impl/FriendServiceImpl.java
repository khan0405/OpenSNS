/**
 * Project		: OpenSNSWeb
 * FileName		: FriendService.java
 * Package		: com.khan.opensns.api.service
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 6. 27. 오후 6:16:32
 */
package com.khan.opensns.api.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khan.opensns.api.dao.FriendDao;
import com.khan.opensns.api.service.FriendService;
import com.khan.opensns.api.service.UserService;
import com.khan.opensns.model.Friend;
import com.khan.opensns.model.User;
import com.khan.opensns.model.persistence.FriendType;
import com.khan.opensns.service.UserNotFoundException;

/**
 * <PRE>
 * @class	: com.khan.opensns.api.service.FriendService
 * @file	: FriendService.java
 * @brief	: 
 * @author	: KHAN
 * @date	: 2013. 6. 27. 오후 6:16:32
 * </PRE>
 */
@Service("friendService")
public class FriendServiceImpl implements FriendService {
	
	private static final Logger log = LoggerFactory.getLogger(FriendServiceImpl.class);
	
	/** The friend repository. */
	@Autowired
	private FriendDao friendDao;
	
	/** The user service. */
	@Autowired
	private UserService userService;
	
	public Friend saveFriend(Friend friend) {
		return friendDao.save(friend);
	}
	
	public List<Friend> getFriend(Long userId, FriendType type) {
		return friendDao.getByFriendType(userService.getUser(userId), type);
	}
	
	public Friend getFriend(Long userId, Long friendId) {
		return friendDao.getById(new Friend.Id(userId, friendId));
	}

	@Override
	public Friend acceptFriend(User user, User friendUser) {
		List<Friend> friends = getFriendRequests(user.getId(), friendUser.getId());
		Friend f = null;
		for (Friend friend : friends) {
			friend.setType(FriendType.FRIEND);
			friend.setUpdateDate(new Date());
			if (friend.getUser().getId().equals(user.getId()))
				f = friend;
		}
		
		friendDao.save(friends);
		
		return f;
	}

	@Override
	public Friend requestFriend(User user, String friendName) throws UserNotFoundException {
		User friendUser = userService.loadUserByName(friendName);
		return requestFriend(user, friendUser);
	}

	@Override
	public Friend requestFriend(User user, Long friendId) {
		User friendUser = userService.getUser(friendId);
		return requestFriend(user, friendUser);
	}

	@Override
	public Friend requestFriend(User user, User friendUser) {

		List<Friend> friends = getFriendRequests(user.getId(), friendUser.getId());
		Friend request = null;
		if (friends == null || friends.isEmpty()) {
			request = new Friend(user, friendUser, FriendType.REQUEST);
			friends.add(request);
	
			Friend friend = new Friend(friendUser, user, FriendType.RECIEVE);
			friends.add(friend);
		} else if (friends.size() == 2) {
			for (Friend friend : friends) {
				if (friend.getUser().getId().equals(user.getId())) {
					friend.setType(FriendType.REQUEST);
					friend.setUpdateDate(new Date());
					request = friend;
				} else {
					friend.setType(FriendType.RECIEVE);
					friend.setUpdateDate(new Date());
				}
			}
		} else {
			log.warn("Friend data was Broken... friends data is {}", friends.toString());
			Friend f = null;
			for (Friend friend : friends) {
				if (friend.getUser().getId().equals(user.getId())) {
					friend.setType(FriendType.REQUEST);
					friend.setUpdateDate(new Date());
					request = friend;
				} else if (friend.getUser().getId().equals(friendUser.getId()) && friend.getFriend().getId().equals(user.getId())) {
					friend.setType(FriendType.RECIEVE);
					friend.setUpdateDate(new Date());
					f = friend;
				}
			}
			
			friends.clear();
			
			if (request != null && f != null) {
				friends.add(request);
				friends.add(f);
			} else if (request != null) {
				friends.add(request);
				f = new Friend(friendUser, user, FriendType.RECIEVE);
				friends.add(f);
			} else if (f != null) {
				request = new Friend(user, friendUser, FriendType.REQUEST);
				friends.add(request);
				friends.add(f);
			} else {
				request = new Friend(user, friendUser, FriendType.REQUEST);
				friends.add(request);
		
				f = new Friend(friendUser, user, FriendType.RECIEVE);
				friends.add(f);
			}
		}
		
		friendDao.save(friends);
		
		return request;
	}

	@Override
	public void breakFriend(User user, Long friendId) {
		List<Friend> friends = getFriendRequests(user.getId(), friendId);
		
		for (Friend friend : friends) {
			friend.setType(FriendType.UNUSED);
			friend.setUpdateDate(new Date());
		}
		
		friendDao.save(friends);
	}
	
	@Override
	public void breakFriend(User user, String friendName) throws UserNotFoundException {
		User friendUser = userService.loadUserByName(friendName);
		breakFriend(user, friendUser.getId());
	}

	@Override
	public void breakFriend(User user, User friendUser) {
		breakFriend(user, friendUser.getId());
	}

	@Override
	public Friend blockFriend(User user, Long friendId) {
		
		Friend friend = getFriend(user.getId(), friendId);
		
		friend.setType(FriendType.BLOCK);
		friend.setUpdateDate(new Date());
		
		return friendDao.save(friend);
	}

	@Override
	public Friend blockFriend(User user, String friendName) throws UserNotFoundException {
		User friendUser = userService.loadUserByName(friendName);
		return blockFriend(user, friendUser.getId());
	}

	@Override
	public Friend blockFriend(User user, User friendUser) {
		return blockFriend(user, friendUser.getId());
	}

	@Override
	public List<Friend> getFriendRequests(Long userId, Long friendId) {
		return friendDao.getFriendRequestsById(userId, friendId);
	}
}
