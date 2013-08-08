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

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <PRE>
 * @class	: com.khan.opensns.model.User
 * @file	: User.java
 * @brief	: 
 * @author	: KHAN
 * @date	: 2013. 6. 25. 오전 10:14:23
 * </PRE>
 */
public class Group implements Serializable {

	private static final long serialVersionUID = -7266188708498913249L;

	private Long id;
	
	private String name;
	
	private String description;

	private User owner;
    
	private Date createDate;

	private Date updateDate;

	private GroupInfo groupInfo;

	private Set<GroupUser> groupUsers = new HashSet<GroupUser>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Set<GroupUser> getGroupUsers() {
		return this.groupUsers;
	}

	public void setGroupUsers(Set<GroupUser> groupUsers) {
		this.groupUsers = groupUsers;
	}

	public GroupUser addUser(GroupUser groupUser) {
		getGroupUsers().add(groupUser);
		groupUser.setGroup(this);

		return groupUser;
	}

	public GroupUser removeUser(GroupUser groupUser) {
		getGroupUsers().remove(groupUser);
		groupUser.setGroup(null);

		return groupUser;
	}

	public GroupInfo getGroupInfo() {
		return groupInfo;
	}

	public void setGroupInfo(GroupInfo groupInfo) {
		this.groupInfo = groupInfo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
