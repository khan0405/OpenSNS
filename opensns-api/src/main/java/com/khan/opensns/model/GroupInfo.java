package com.khan.opensns.model;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the opensns_user_info database table.
 * 
 */
public class GroupInfo implements Serializable {

	private static final long serialVersionUID = 72613325154362380L;

	private Long id;

	private boolean pause;

	private boolean open;

	private boolean searchEnable;

	private String backImage;

	private String backImageThumb;

	private String profileImage;

	private String profileImageThumb;

	private Date updateDate;
	
	private Group group;

	public GroupInfo() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBackImage() {
		return this.backImage;
	}

	public void setBackImage(String backImage) {
		this.backImage = backImage;
	}

	public String getBackImageThumb() {
		return this.backImageThumb;
	}

	public void setBackImageThumb(String backImageThumb) {
		this.backImageThumb = backImageThumb;
	}

	public boolean getPause() {
		return this.pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public String getProfileImage() {
		return this.profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getProfileImageThumb() {
		return this.profileImageThumb;
	}

	public void setProfileImageThumb(String profileImageThumb) {
		this.profileImageThumb = profileImageThumb;
	}

	public boolean getOpen() {
		return this.open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean getSearchEnable() {
		return this.searchEnable;
	}

	public void setSearchEnable(boolean searchEnable) {
		this.searchEnable = searchEnable;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

}