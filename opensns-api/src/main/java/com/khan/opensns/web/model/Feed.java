/**
 * Project		: OpenSNSWeb
 * FileName		: Feed.java
 * Package		: com.khan.opensns.web.model
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 6. 26. 오후 7:08:22
 */
package com.khan.opensns.web.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.khan.opensns.web.model.persistence.ReadPermission;

/**
 * <PRE>
 * @class	: com.khan.opensns.web.model.Feed
 * @file	: Feed.java
 * @brief	: 
 * @author	: KHAN
 * @date	: 2013. 6. 26. 오후 7:08:22
 * </PRE>
 */
public class Feed implements Serializable {
	
	private static final long serialVersionUID = -4469037757094415151L;

	private Long id;
	
	private Integer type;
	
	private String message;
	
	private Integer readPerm;
	
	private Date createDate;

	private User user;
	
	private User toUser;
	
	private Group toGroup;
	
	private Set<FeedContent> contents = new HashSet<FeedContent>();
	
	private Set<FeedReply> replies = new HashSet<FeedReply>();

	private Set<FeedLike> likes = new HashSet<FeedLike>();
	
	public Feed() {}
	
	public Feed(Integer type, String message, FeedContent content, User user, ReadPermission readPerm) {
		this(type, message, content, user, readPerm.getType());
	}
	
	public Feed(Integer type, String message, FeedContent content, User user, Integer readPerm) {
		setType(type);
		setMessage(message);
		addContent(content);
		setUser(user);
		setReadPerm(readPerm);
		setCreateDate(new Date());
	}

	public Feed(Integer type, String message, Set<FeedContent> contents, User user, ReadPermission readPerm) {
		this(type, message, contents, user, readPerm.getType());
	}
	
	public Feed(Integer type, String message, Set<FeedContent> contents, User user, Integer readPerm) {
		setType(type);
		setMessage(message);
		setContents(contents);
		setUser(user);
		setReadPerm(readPerm);
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
	 * @return readPerm
	 */
	public Integer getReadPerm() {
		return readPerm;
	}

	/**
	 * @param readPerm Set to read permission
	 */
	public void setReadPerm(Integer readPerm) {
		this.readPerm = readPerm;
	}

	/**
	 * @return readPerm
	 */
	public ReadPermission getReadPermission() {
		return ReadPermission.valueOf(readPerm);
	}

	/**
	 * @param readPerm Set to read permission
	 */
	public void setReadPerm(ReadPermission readPerm) {
		this.readPerm = readPerm.getType();
	}

	/**
	 * @return createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate Set to createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public Group getToGroup() {
		return toGroup;
	}

	public void setToGroup(Group toGroup) {
		this.toGroup = toGroup;
	}

	public Set<FeedContent> getContents() {
		return this.contents;
	}

	public void setContents(Set<FeedContent> contents) {
		this.contents = contents;
	}

	public FeedContent addContent(FeedContent content) {
		getContents().add(content);
		content.setFeed(this);

		return content;
	}

	public FeedContent removeContent(FeedContent content) {
		getContents().remove(content);
		content.setFeed(null);

		return content;
	}

	public Set<FeedReply> getReplies() {
		return this.replies;
	}

	public void setReplies(Set<FeedReply> replies) {
		this.replies = replies;
	}

	public FeedReply addReply(FeedReply reply) {
		getReplies().add(reply);
		reply.setFeed(this);

		return reply;
	}

	public FeedReply removeReply(FeedReply reply) {
		getReplies().remove(reply);
		reply.setFeed(null);

		return reply;
	}

	public Set<FeedLike> getLikes() {
		return this.likes;
	}

	public void setLikes(Set<FeedLike> likes) {
		this.likes = likes;
	}

	public FeedLike addLike(FeedLike like) {
		getLikes().add(like);
		like.setFeed(this);

		return like;
	}

	public FeedLike removeLike(FeedLike like) {
		getLikes().remove(like);
		like.setFeed(null);

		return like;
	}
}
