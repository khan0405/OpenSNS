/**
 * Project		: OpenSNSWeb
 * FileName		: FeedReply.java
 * Package		: com.khan.opensns.web.model
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 6. 26. 오후 7:08:33
 */
package com.khan.opensns.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <PRE>
 * @class	: com.khan.opensns.web.model.FeedReply
 * @file	: FeedReply.java
 * @brief	: 
 * @author	: KHAN
 * @date	: 2013. 6. 26. 오후 7:08:33
 * </PRE>
 */
public class FeedReply implements Serializable {
	
	private static final long serialVersionUID = 9159931245827210272L;

	private Long id;
	
	private Integer type;

	private String message;
	
	private Feed feed;
	
	private User user;
	
	private Date createDate;
	
	public FeedReply() {}
	
	public FeedReply(Integer type, String message, Feed feed, User user) {
		setType(type);
		setMessage(message);
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
	 * @return type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type Set to type
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message Set to message
	 */
	public void setMessage(String message) {
		this.message = message;
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
