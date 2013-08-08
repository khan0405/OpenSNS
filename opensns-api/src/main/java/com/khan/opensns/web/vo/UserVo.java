/**
 * Project		: BookMarkerWeb
 * FileName		: UserVO.java
 * Package		: com.khan.bookmarker.model.vo
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 6. 25. 오후 1:30:51
 */
package com.khan.opensns.web.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

import com.khan.opensns.web.model.User;


/**
 * <PRE>
 * @class	: com.khan.bookmarker.model.vo.UserVO
 * @file	: UserVO.java
 * @brief	: 
 * @author	: KHAN
 * @date	: 2013. 6. 25. 오후 1:30:51
 * </PRE>
 */
@JsonAutoDetect(value=JsonMethod.FIELD)
public class UserVo implements Serializable {
	
	@JsonIgnore
	private static final long serialVersionUID = -5710234079014024844L;
	/* User Fields */
	@JsonProperty private String username;
	@JsonProperty private String password;
	@JsonProperty private String name; 
	@JsonProperty private Date lastLoginDate;
	@JsonProperty private Date createDate;
	
	/* User Info Fields */
	@JsonProperty private String backImage;
	@JsonProperty private String backImageThumb;
	@JsonProperty private String profileImage;
	@JsonProperty private String profileImageThumb;
	@JsonProperty private Boolean pause;
	@JsonProperty private Boolean push;
	@JsonProperty private Boolean searchEnable;
	@JsonProperty private Date updateDate;
	
	public UserVo() {}
	
	public UserVo(User user) {
		setUser(user);
	}
	
	public void setUser(User user) {
		setUsername(user.getUsername());
		setPassword(user.getPassword());
		setName(user.getName());
		setLastLoginDate(user.getLastLoginDate());
		setCreateDate(user.getCreateDate());

		setBackImage(user.getUserInfo().getBackImage());
		setBackImageThumb(user.getUserInfo().getBackImageThumb());
		setProfileImage(user.getUserInfo().getProfileImage());
		setProfileImageThumb(user.getUserInfo().getProfileImageThumb());
		setPause(user.getUserInfo().getPause());
		setPush(user.getUserInfo().getPush());
		setSearchEnable(user.getUserInfo().getSearchEnable());
		setUpdateDate(user.getUserInfo().getUpdateDate());
	}
	
	public void wrap(UserVo target) {
		if (!StringUtils.isBlank(target.getUsername()))
			setUsername(target.getUsername());
		if (!StringUtils.isBlank(target.getPassword()))
			setPassword(target.getPassword());
		if (!StringUtils.isBlank(target.getName()))
			setName(target.getName());
		if (target.getLastLoginDate() != null)
			setLastLoginDate(target.getLastLoginDate());
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
		if (target.isPush() != null)
			setPush(target.isPush());
		if (target.isSearchEnable() != null)
			setSearchEnable(target.isSearchEnable());
		if (target.getUpdateDate() != null)
			setUpdateDate(target.getUpdateDate());
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
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

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public Boolean isPush() {
		return push;
	}

	public void setPush(boolean push) {
		this.push = push;
	}

	public Boolean isSearchEnable() {
		return searchEnable;
	}

	public void setSearchEnable(boolean searchEnable) {
		this.searchEnable = searchEnable;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
