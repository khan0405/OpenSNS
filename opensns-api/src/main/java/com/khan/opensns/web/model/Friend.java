/**
 * Project		: OpenSNSWeb
 * FileName		: Friend.java
 * Package		: com.khan.opensns.web.model
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 6. 26. 오후 7:11:44
 */
package com.khan.opensns.web.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.khan.opensns.web.model.persistence.FriendType;

/**
 * <PRE>
 * @class	: com.khan.opensns.web.model.Friend
 * @file	: Friend.java
 * @brief	: 
 * @author	: KHAN
 * @date	: 2013. 6. 26. 오후 7:11:44
 * </PRE>
 */
public class Friend implements Serializable, Cloneable {

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 5329342113363382024L;

	private Id id;

	private Integer type;

	private User user;

	private User friend;

	private Date createDate;

	private Date updateDate;

	public Friend() { }

	public Friend(User user, User friend, FriendType type) {
		this(user, friend, type.getValue());
	}

	public Friend(User user, User friend, Integer type) {
		setUser(user);
		setFriend(friend);
		setId(new Id(user.getId(), friend.getId()));
		setType(type);
		setCreateDate(new Date());
		setUpdateDate(new Date());
	}

	/**
	 * @return createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            Set to createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate
	 *            Set to updateDate
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return id
	 */
	public Id getId() {
		return id;
	}

	/**
	 * @param id
	 *            Set to id
	 */
	public void setId(Id id) {
		this.id = id;
	}

	/**
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            Set to user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return friend
	 */
	public User getFriend() {
		return friend;
	}

	/**
	 * @param friend
	 *            Set to friend
	 */
	public void setFriend(User friend) {
		this.friend = friend;
	}

	/**
	 * @param type
	 *            Set to type
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @param type
	 *            Set to type
	 */
	public void setType(FriendType type) {
		this.type = type.getValue();
	}

	public void setType(int type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}

	public FriendType getFriendType() {
		return FriendType.valueOf(type);
	}

	@Override
	public Friend clone() {
		Friend f = null;
		try {
			f = (Friend) super.clone();
		} catch (Exception e) {
			System.out.println("can't clone friend");
		}
		return f;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

	public static class Id implements Serializable {
		
		private static final long serialVersionUID = 2556716685181682347L;
		
		private Long userId;
		
		private Long friendId;

		public Id() {}

		public Id(Long userId, Long friendId) {
			setUserId(userId);
			setFriendId(friendId);
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public Long getFriendId() {
			return friendId;
		}

		public void setFriendId(Long friendId) {
			this.friendId = friendId;
		}

		public boolean equals(Object o) {
			if (o instanceof Id) {
				Id id = (Id) o;
				return id.userId == userId && id.friendId == friendId;
			}

			return false;
		}
	}

}
