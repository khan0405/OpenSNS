/**
 * Project		: opensnsWeb
 * FileName		: User.java
 * Package		: com.khan.opensns.model
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 6. 25. 오전 10:14:23
 */
package com.khan.opensns.web.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails, Cloneable {

	private static final long serialVersionUID = -231966469724511526L;
	
	private Long id;
	
	private String username;

	private String password;

	private String authKey;

	private String name;
	
	private boolean enabled = true;

	private boolean locked = false;

	private Date createDate;

	private Date updateDate;

	private Date lastLoginDate;

    private Set<Role> roles = new HashSet<Role>();

	private UserInfo userInfo;
    
	private UserProperty userProperty;

	private Set<Feed> feeds = new HashSet<Feed>();

	private Set<FeedReply> feedReplies = new HashSet<FeedReply>();

	private Set<Friend> friends = new HashSet<Friend>();

	private Set<Friend> reverseFriends = new HashSet<Friend>();

	/**
	 * @return roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles Set to roles
	 */
	public void setRoles(Set<Role> roles) {
		if (roles != null)
			this.roles = roles;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		if (username != null)
			this.username = username;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		if (password != null)
			this.password = password;
	}
	
	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public UserProperty getUserProperty() {
		return userProperty;
	}

	public void setUserProperty(UserProperty userProperty) {
		this.userProperty = userProperty;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		if (name != null)
			this.name = name;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the enabled.
	 *
	 * @param enabled the new enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * Sets the locked.
	 *
	 * @param locked the new locked
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		if (id != null)
			this.id = id;
	}

	/**
	 * Gets the creates the date.
	 *
	 * @return the creates the date
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the creates the date.
	 *
	 * @param createDate the new creates the date
	 */
	public void setCreateDate(Date createDate) {
		if (createDate != null)
			this.createDate = createDate;
	}

	/**
	 * Gets the update date.
	 *
	 * @return the update date
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * Sets the update date.
	 *
	 * @param updateDate the new update date
	 */
	public void setUpdateDate(Date updateDate) {
		if (updateDate != null)
			this.updateDate = updateDate;
	}

	/**
	 * Gets the last login date.
	 *
	 * @return the last login date
	 */
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	/**
	 * Sets the last login date.
	 *
	 * @param lastLoginDate the new last login date
	 */
	public void setLastLoginDate(Date lastLoginDate) {
		if (lastLoginDate != null)
			this.lastLoginDate = lastLoginDate;
	}
	
	public void addRole(Role role) {
		if (!roles.contains(role))
			roles.add(role);
	}
	
	public void removeRole(Role role) {
		if (roles.contains(role))
			roles.remove(role);
	}
	
	public Set<Feed> getFeeds() {
		return feeds;
	}

	public void setFeeds(Set<Feed> feeds) {
		this.feeds = feeds;
	}
	
	public void addFeed(Feed feed) {
		if (!feeds.contains(feed))
			feeds.add(feed);
	}
	
	public void removeFeed(Feed feed) {
		if (feeds.contains(feed))
			feeds.remove(feed);
	}

	public Set<FeedReply> getFeedReplies() {
		return feedReplies;
	}

	public void setFeedReplies(Set<FeedReply> feedReplies) {
		this.feedReplies = feedReplies;
	}
	
	public void addFeedReply(FeedReply feedReplie) {
		if (!feedReplies.contains(feedReplie))
			feedReplies.add(feedReplie);
	}
	
	public void removeFeedReply(FeedReply feedReplie) {
		if (feedReplies.contains(feedReplie))
			feedReplies.remove(feedReplie);
	}

	public Set<Friend> getFriends() {
		return friends;
	}

	public void setFriends(Set<Friend> friends) {
		this.friends = friends;
	}
	
	public void addFriend(Friend friend) {
		if (!friends.contains(friend))
			friends.add(friend);
	}
	
	public void removeFriend(Friend friend) {
		if (friends.contains(friend))
			friends.remove(friend);
	}

	public Set<Friend> getReverseFriends() {
		return reverseFriends;
	}

	public void setReverseFriends(Set<Friend> reverseFriends) {
		this.reverseFriends = reverseFriends;
	}
	
	public void addReverseFriend(Friend feed) {
		if (!reverseFriends.contains(feed))
			reverseFriends.add(feed);
	}
	
	public void removeReverseFriend(Friend reverseFriend) {
		if (reverseFriends.contains(reverseFriend))
			reverseFriends.remove(reverseFriend);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUsername() {
		return username;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		if (userInfo != null)
			this.userInfo = userInfo;
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
