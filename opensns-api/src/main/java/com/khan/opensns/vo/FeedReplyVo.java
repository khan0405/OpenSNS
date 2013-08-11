package com.khan.opensns.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

import com.khan.opensns.model.FeedReply;

@JsonAutoDetect(value=JsonMethod.FIELD)
public class FeedReplyVo implements Serializable {

	@JsonIgnore	private static final long serialVersionUID = 7832859276788927694L;

	@JsonProperty private Long id;
	@JsonProperty private String message;
	@JsonProperty private Integer type;
	@JsonProperty private Date createDate;
	
	@JsonProperty private String name;
	@JsonProperty private String profileImage;
	@JsonProperty private String profileImageThumb;
	
	public FeedReplyVo() { }
	
	public FeedReplyVo(Long id, String message, Integer type, Date createDate, String name, String profileImage, String profileImageThumb) {
		setId(id);
		setMessage(message);
		setType(type);
		setCreateDate(createDate);
		setName(name);
		setProfileImage(profileImage);
		setProfileImageThumb(profileImageThumb);
	}
	
	public FeedReplyVo(FeedReply reply) { 
		transferVo(this, reply);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	
	public static FeedReplyVo transferVo(FeedReplyVo vo, FeedReply reply) {
		vo.setId(reply.getId());
		vo.setMessage(reply.getMessage());
		vo.setType(reply.getType());
		vo.setCreateDate(reply.getCreateDate());
		
		vo.setName(reply.getUser().getName());
		vo.setProfileImage(reply.getUser().getUserInfo().getProfileImage());
		vo.setProfileImageThumb(reply.getUser().getUserInfo().getProfileImageThumb());
		return vo;
	}
	
	public static List<FeedReplyVo> transferVo(Collection<FeedReply> replies) {
		List<FeedReplyVo> vos = new ArrayList<FeedReplyVo>();
		for (FeedReply reply : replies) {
			FeedReplyVo vo = new FeedReplyVo();
			vos.add(transferVo(vo, reply));
		}
		return vos;
	}
}
