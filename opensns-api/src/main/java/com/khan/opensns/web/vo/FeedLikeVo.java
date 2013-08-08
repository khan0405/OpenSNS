package com.khan.opensns.web.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

import com.khan.opensns.web.model.FeedLike;

@JsonAutoDetect(value=JsonMethod.FIELD)
public class FeedLikeVo implements Serializable {
	
	@JsonIgnore private static final long serialVersionUID = -1679825123813177025L;

	@JsonProperty private Long id;
	@JsonProperty private Integer type;
	@JsonProperty private String name;
	@JsonProperty private String profileImage;
	@JsonProperty private String profileImageThumb;
	
	public FeedLikeVo() {}
	
	public FeedLikeVo(Long id, Integer type, String name, String profileImage, String profileImageThumb) {
		setId(id);
		setType(type);
		setName(name);
		setProfileImage(profileImage);
		setProfileImageThumb(profileImageThumb);
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	public static FeedLikeVo transferVo(FeedLikeVo vo, FeedLike like) {
		vo.setId(like.getId());
		vo.setType(like.getType());
		
		vo.setName(like.getUser().getName());
		vo.setProfileImage(like.getUser().getUserInfo().getProfileImage());
		vo.setProfileImageThumb(like.getUser().getUserInfo().getProfileImageThumb());
		
		return vo;
	}

	public static List<FeedLikeVo> transferVo(Collection<FeedLike> likes) {
		if (likes == null)
			return null;
		List<FeedLikeVo> vos = new ArrayList<FeedLikeVo>();
		for (FeedLike like : likes) {
			FeedLikeVo vo = new FeedLikeVo();
			vos.add(transferVo(vo, like));
		}
		return vos;
	}
}
