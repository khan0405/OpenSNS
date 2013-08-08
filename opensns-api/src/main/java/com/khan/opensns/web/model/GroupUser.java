package com.khan.opensns.web.model;

import java.io.Serializable;
import java.util.Date;

public class GroupUser implements Serializable {

	private static final long serialVersionUID = -2492883830001396842L;

	private Id id;

	private User user;
	
	private Group group;
	
	private int type;
	
	private Date createDate;
	
	private Date updateDate;

	public GroupUser() {}
	
	public GroupUser(Group group, User user) {
		setGroup(group);
		setUser(user);
		setId(new Id(group.getId(), user.getId()));
		setCreateDate(new Date());
	}

	public Id getId() {
		return this.id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public static class Id implements Serializable {
		
		private static final long serialVersionUID = -882617455298050507L;

		private Long groupId;
		
		private Long userId;

		public Id() {}

		public Id(Long groupId, Long userId) {
			setUserId(userId);
		}

		public Long getGroupId() {
			return groupId;
		}

		public void setGroupId(Long groupId) {
			this.groupId = groupId;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}
		
		public boolean equals(Object o) {
			if (o instanceof Id) {
				Id id = (Id) o;
				return id.userId == userId && id.groupId == groupId;
			}

			return false;
		}
	}
}