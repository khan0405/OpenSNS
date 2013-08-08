package com.khan.opensns.web.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

import com.khan.opensns.web.model.Feed;

@JsonAutoDetect(value=JsonMethod.FIELD)
public class FeedVo implements Serializable {

	@JsonIgnore private static final long serialVersionUID = -3626921061586673264L;
	
	@JsonProperty private Long id; 
	@JsonProperty private String message;
	@JsonProperty private Integer readPerm;
	@JsonProperty private Date createDate;
	
	@JsonProperty private List<FeedContentVo> contents;
	
	@JsonProperty private Integer replies;
	@JsonProperty private Integer likes;
	
	public FeedVo() {}
	
	public FeedVo(Long id, String message, Integer readPerm, Date createDate, Integer replies, Integer likes) {
		setId(id);
		setMessage(message);
		setReadPerm(readPerm);
		setCreateDate(createDate);
		setReplies(replies);
		setLikes(likes);
	}
	
	public FeedVo(Feed feed) {
		transferVo(this, feed);
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
	public Integer getReadPerm() {
		return readPerm;
	}
	public void setReadPerm(Integer readPerm) {
		this.readPerm = readPerm;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public List<FeedContentVo> getContents() {
		return contents;
	}
	public void setContents(List<FeedContentVo> contents) {
		this.contents = contents;
	}
	public Integer getReplies() {
		return replies;
	}
	public void setReplies(Integer replies) {
		this.replies = replies;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	
	public static FeedVo transferVo(FeedVo vo, Feed feed) {
		vo.setId(feed.getId());
		vo.setMessage(feed.getMessage());
		vo.setReadPerm(feed.getReadPerm());
		vo.setCreateDate(feed.getCreateDate());
		vo.setReplies(feed.getReplies().size());
		vo.setLikes(feed.getLikes().size());
		
		return vo;
	}
	
	public static List<FeedVo> transferVo(Collection<Feed> feeds) {
		if (feeds == null)
			return null;
		List<FeedVo> vos = new ArrayList<FeedVo>();
		for (Feed feed : feeds) {
			FeedVo vo = new FeedVo();
			vos.add(transferVo(vo, feed));
		}
		return vos;
	}
}
