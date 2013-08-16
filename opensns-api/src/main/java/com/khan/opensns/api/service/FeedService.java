/**
 * Project		: OpenSNSWeb
 * FileName		: FeedService.java
 * Package		: com.khan.opensns.web.service
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 7. 1. 오후 4:45:28
 */
package com.khan.opensns.api.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartRequest;

import com.khan.opensns.dao.ListOrder;
import com.khan.opensns.model.Feed;
import com.khan.opensns.model.FeedContent;
import com.khan.opensns.model.FeedLike;
import com.khan.opensns.model.FeedReply;
import com.khan.opensns.model.User;
import com.khan.opensns.service.UserNotFoundException;
import com.khan.opensns.vo.FeedLikeVo;
import com.khan.opensns.vo.FeedReplyVo;

/**
 * <PRE>
 * @class	: com.khan.opensns.web.service.FeedService
 * @file	: FeedService.java
 * @brief	: 
 * @author	: KHAN
 * @date	: 2013. 7. 1. 오후 4:45:28
 * </PRE>
 */
public interface FeedService {

	public Feed saveFeed(User user, String message, Integer readPerm);
	
	public FeedContent saveFeedContent(Integer type, String content, Feed feed);
	
	public List<String> saveFeedImages(Feed feed, MultipartRequest request) throws IOException;
	
	public Feed saveFeed(Feed feed);
	
	public FeedContent saveFeedContent(FeedContent content);
	
	public Set<FeedContent> saveFeedContents(Set<FeedContent> content);
	
	public Feed getFeed(Long id);
	
	public List<Feed> getPopularFeeds(Integer page, Integer size);
	
	public List<Feed> getNewsFeeds(User user, Long lastId, ListOrder order, Integer size);
	
	public List<Feed> getUserFeeds(User user, String targetName, Long lastId, ListOrder order, Integer size) throws UserNotFoundException;
	

	public FeedReply getFeedReply(Long id);
	
	public FeedReply saveFeedReply(FeedReply reply);
	
	public FeedReplyVo saveReply(Long feedId, User user, FeedReplyVo replyVo);
	
	public void removeReply(Long replyId);
	
	public FeedLike getFeedLike(Long id);
	
	public FeedLike saveFeedLike(FeedLike like);
	
	public FeedLikeVo saveFeedLike(Long feedId, User user, FeedLikeVo likeVo);
	
	public void cancelFeedLike(Long likeId);
	
}
