package com.khan.opensns.web.vo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonAutoDetect(value=JsonMethod.FIELD)
public class FeedDetailVo implements Serializable {
	
	@JsonIgnore private static final long serialVersionUID = 5778859872792346113L;
	@JsonProperty private FeedVo feed;
	@JsonProperty private List<FeedLikeVo> likes;
	@JsonProperty private List<FeedReplyVo> replies;
	
	public FeedDetailVo() {}
	
	public FeedDetailVo(FeedVo feed, List<FeedLikeVo> likes, List<FeedReplyVo> replies) {
		setFeed(feed);
		setLikes(likes);
		setReplies(replies);
	}

	public FeedVo getFeed() {
		return feed;
	}

	public void setFeed(FeedVo feed) {
		this.feed = feed;
	}

	public List<FeedLikeVo> getLikes() {
		return likes;
	}

	public void setLikes(List<FeedLikeVo> likes) {
		this.likes = likes;
	}

	public List<FeedReplyVo> getReplies() {
		return replies;
	}

	public void setReplies(List<FeedReplyVo> replies) {
		this.replies = replies;
	}
}
