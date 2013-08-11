package com.khan.opensns.vo;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonProperty;

import com.khan.opensns.model.Group;
import com.khan.opensns.model.GroupInfo;

public class GroupVo {
	
	@JsonProperty private Long id; 
	@JsonProperty private String name;
	@JsonProperty private String description;
	@JsonProperty private Long ownerId;
	@JsonProperty private Date createDate;
	
	@JsonProperty private String backImage;
	@JsonProperty private String backImageThumb;
	@JsonProperty private String profileImage;
	@JsonProperty private String profileImageThumb;
	@JsonProperty private Boolean pause;
	@JsonProperty private Boolean open;
	@JsonProperty private Boolean searchEnable;
	@JsonProperty private Date updateDate;
	
	public GroupVo() {}
	
	public GroupVo(Group group) {
		setGroup(group);
	}
	
	public void setGroup(Group group) {
		setName(group.getName());
		setDescription(group.getDescription());
		setOwnerId(group.getOwner().getId());
		setCreateDate(group.getCreateDate());

		GroupInfo groupInfo = group.getGroupInfo();
		
		setBackImage(groupInfo.getBackImage());
		setBackImageThumb(groupInfo.getBackImageThumb());
		setProfileImage(groupInfo.getProfileImage());
		setProfileImageThumb(groupInfo.getProfileImageThumb());
		setPause(groupInfo.getPause());
		setOpen(groupInfo.getOpen());
		setSearchEnable(groupInfo.getSearchEnable());
		setUpdateDate(groupInfo.getUpdateDate());
		
	}
	
	public void wrap(GroupVo target) {
		if (!StringUtils.isBlank(target.getName()))
			setName(target.getName());
		if (target.getDescription() != null)
			setDescription(target.getDescription());
		if (target.getOwnerId() != null)
			setOwnerId(target.getOwnerId());
		if (target.getCreateDate() != null)
			setCreateDate(target.getCreateDate());

		if (!StringUtils.isBlank(target.getBackImage()))
			setBackImage(target.getBackImage());
		if (!StringUtils.isBlank(target.getBackImageThumb()))
			setBackImageThumb(target.getBackImageThumb());
		if (!StringUtils.isBlank(target.getProfileImage()))
			setProfileImage(target.getProfileImage());
		if (!StringUtils.isBlank(target.getProfileImageThumb()))
			setProfileImageThumb(target.getProfileImageThumb());
		if (target.isPause() != null)
			setPause(target.isPause());
		if (target.isOpen() != null)
			setOpen(target.isOpen());
		if (target.isSearchEnable() != null)
			setSearchEnable(target.isSearchEnable());
		if (target.getUpdateDate() != null)
			setUpdateDate(target.getUpdateDate());
	}

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

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getBackImage() {
		return backImage;
	}

	public void setBackImage(String backImage) {
		this.backImage = backImage;
	}

	public String getBackImageThumb() {
		return backImageThumb;
	}

	public void setBackImageThumb(String backImageThumb) {
		this.backImageThumb = backImageThumb;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getProfileImageThumb() {
		return profileImageThumb;
	}

	public void setProfileImageThumb(String profileImageThumb) {
		this.profileImageThumb = profileImageThumb;
	}

	public Boolean isPause() {
		return pause;
	}

	public void setPause(Boolean pause) {
		this.pause = pause;
	}

	public Boolean isOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Boolean isSearchEnable() {
		return searchEnable;
	}

	public void setSearchEnable(Boolean searchEnable) {
		this.searchEnable = searchEnable;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
