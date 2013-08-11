package com.khan.opensns.model;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {
	
	private static final long serialVersionUID = -5577480353365336084L;

	private Long id;

	private boolean pause;

	private boolean push;

	private String backImage;

	private String backImageThumb;

	private String profileImage;

	private String profileImageThumb;

	private boolean searchEnable;

	private Date updateDate;
	
	private User user;

	public UserInfo() {
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

	public boolean getPush() {
		return this.push;
	}

	public void setPush(boolean push) {
		this.push = push;
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
		this.id = user.getId();
	}

}