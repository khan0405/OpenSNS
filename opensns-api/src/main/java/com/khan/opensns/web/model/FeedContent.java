/**
 * Project		: OpenSNSWeb
 * FileName		: FeedContent.java
 * Package		: com.khan.opensns.web.model
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 7. 1. 오전 11:23:35
 */
package com.khan.opensns.web.model;

import java.io.Serializable;

import com.khan.opensns.web.model.persistence.FeedType;

/**
 * <PRE>
 * @class	: com.khan.opensns.web.model.FeedContent
 * @file	: FeedContent.java
 * @brief	: 
 * @author	: KHAN
 * @date	: 2013. 7. 1. 오전 11:23:35
 * </PRE>
 */
public class FeedContent implements Serializable {
	
	private static final long serialVersionUID = 5620081857323381147L;

	private Long id;
	
	private Integer type;

	private String content;

	private Feed feed;
	
	public FeedContent() {}
	
	public FeedContent(Integer type, String content, Feed feed) {
		setType(type);
		setContent(content);
		setFeed(feed);
	}
	
	public FeedContent(FeedType type, String content, Feed feed) {
		setType(type.getValue());
		setContent(content);
		setFeed(feed);
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
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content Set to content
	 */
	public void setContent(String content) {
		this.content = content;
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
}
