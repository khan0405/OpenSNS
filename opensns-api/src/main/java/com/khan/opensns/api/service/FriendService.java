/**
 * Project		: OpenSNSWeb
 * FileName		: FriendService.java
 * Package		: com.khan.opensns.web.service
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 6. 27. 오후 6:16:32
 */
package com.khan.opensns.api.service;

import java.util.List;

import com.khan.opensns.model.Friend;
import com.khan.opensns.model.User;
import com.khan.opensns.model.persistence.FriendType;
import com.khan.opensns.service.UserNotFoundException;

/**
 * <PRE>
 * @class	: com.khan.opensns.web.service.FriendService
 * @file	: FriendService.java
 * @brief	: 
 * @author	: KHAN
 * @date	: 2013. 6. 27. 오후 6:16:32
 * </PRE>
 */
public interface FriendService {
	
	public Friend saveFriend(Friend friend);
	
	public List<Friend> getFriend(Long userId, FriendType type);
	
	public Friend getFriend(Long userId, Long friendId);
	
	/**
	 * 친구 수락
	 * 
	 * @param user 자기자신
	 * @param friendUser 대상 유저
	 * @return 친구 정보
	 */
	public Friend acceptFriend(User user, User friendUser);
	

	/**
	 * 친구 요청
	 * 
	 * @param user 자기자신
	 * @param friendName 대상 유저의 Name
	 * @return 친구 정보
	 */
	public Friend requestFriend(User user, String friendName) throws UserNotFoundException;

	/**
	 * 친구 요청
	 * 
	 * @param user 자기자신
	 * @param friendId 대상 유저의 id
	 * @return 친구 정보
	 */
	public Friend requestFriend(User user, Long friendId);


	/**
	 * 친구 요청
	 * 
	 * @param user 자기자신
	 * @param friendUser 대상 유저
	 * @return 친구 정보
	 */
	public Friend requestFriend(User user, User friendUser);
	
	/**
	 * 친구 끊기
	 * 
	 * @param user 자기자신
	 * @param friendName 대상 유저의 Name
	 */
	public void breakFriend(User user, String friendName) throws UserNotFoundException;
	
	/**
	 * 친구 끊기
	 * 
	 * @param user 자기자신
	 * @param friendId 대상 유저의 id
	 */
	public void breakFriend(User user, Long friendId);
	
	/**
	 * 친구 끊기
	 * 
	 * @param user 자기자신
	 * @param friendUser 대상 유저
	 */
	public void breakFriend(User user, User friendUser);
	
	/**
	 * 친구 차단
	 * 
	 * @param user 자기 자신
	 * @param friendName 대상 유저의 Name
	 * @return 친구 정보
	 */
	public Friend blockFriend(User user, String friendName) throws UserNotFoundException;
	
	/**
	 * 친구 차단
	 * 
	 * @param user 자기 자신
	 * @param friendId 대상 유저의 id
	 * @return 친구 정보
	 */
	public Friend blockFriend(User user, Long friendId);
	
	/**
	 * 친구 차단
	 * 
	 * @param user 자기 자신
	 * @param friendUser 대상 유저
	 * @return 친구 정보
	 */
	public Friend blockFriend(User user, User friendUser);
	
	/**
	 * 서로의 친구 정보를 가져옴
	 * 
	 * @param userId 자신의 ID
	 * @param friendId 친구의 ID
	 * @return List<Friend> 친구 정보
	 */
	public List<Friend> getFriendRequests(Long userId, Long friendId);
}
