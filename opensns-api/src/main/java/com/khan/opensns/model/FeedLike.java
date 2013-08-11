/**
 * Project		: OpenSNSWeb
 * FileName		: FeedLike.java
 * Package		: com.khan.opensns.web.model
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 6. 26. 오후 7:08:33
 */
package com.khan.opensns.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * <PRE>
 * @class	: com.khan.opensns.web.model.FeedReply
 * @file	: FeedLike.java
 * @brief	: 
 * @author	: KHAN
 * @date	: 2013. 6. 26. 오후 7:08:33
 * </PRE>
 */
public class FeedLike implements Serializable {
	
	private static final long serialVersionUID = 3827924381983825942L;

	private Long id;
	
	private Feed feed;
	
	private User user;
	
	private Integer type;
	
	private Date createDate;
	
	public FeedLike() {}
	
	public FeedLike(Integer type, Feed feed, User user) {
		setType(type);
		setFeed(feed);
		setUser(user);
		setCreateDate(new Date());
	}
	
	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id Set to id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return id
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param id Set to id
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return feed
	 */
	public Feed getFeed() {
		return feed;
	}

	/**
	 * @param feed Set to feed
	 */
	public void setFeed(Feed feed) {
		if (feed != null) {
			Set<FeedLike> likes = feed.getLikes();
			if (likes.isEmpty()) {
				likes.add(this);
			} else {
				for (FeedLike like : likes) {
					if (like.getUser().getId() == getUser().getId()) {
						setId(like.getId());
						break;
					}
				}
			}
		}
		this.feed = feed;
	}

	/**
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user Set to user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
