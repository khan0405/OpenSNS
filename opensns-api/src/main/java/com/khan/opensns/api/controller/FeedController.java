/**
 * Project		: OpenSNSWeb
 * FileName		: FeedController.java
 * Package		: com.khan.opensns.api.controller
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 7. 1. 오전 11:18:42
 */
package com.khan.opensns.api.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.khan.opensns.api.annotation.Api;
import com.khan.opensns.api.service.FeedService;
import com.khan.opensns.api.service.UserService;
import com.khan.opensns.dao.ListOrder;
import com.khan.opensns.dto.BodyResponse;
import com.khan.opensns.dto.DataResponse;
import com.khan.opensns.model.Feed;
import com.khan.opensns.model.FeedContent;
import com.khan.opensns.model.FeedLike;
import com.khan.opensns.model.FeedReply;
import com.khan.opensns.model.User;
import com.khan.opensns.model.persistence.FeedType;
import com.khan.opensns.service.UserNotFoundException;
import com.khan.opensns.util.StringUtil;
import com.khan.opensns.vo.FeedDetailVo;
import com.khan.opensns.vo.FeedLikeVo;
import com.khan.opensns.vo.FeedReplyVo;
import com.khan.opensns.vo.FeedVo;

/**
 * <PRE>
 * @class	: com.khan.opensns.api.controller.FeedController
 * @file	: FeedController.java
 * @brief	: 
 * @author	: KHAN
 * @date	: 2013. 7. 1. 오전 11:18:42
 * </PRE>
 */
@Controller
@RequestMapping(value={"/api/feed"})
public class FeedController extends BaseController {
	
	@Autowired
	private FeedService feedService;
	
	@Autowired
	private UserService userService;

	@Api(auth=true)
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView createFeed(
			@RequestHeader String authKey,
			@RequestParam String message,
			@RequestParam Integer readPerm,
			
			MultipartRequest request) throws Exception {
		
		User user = userService.loadUserByAuthKey(authKey);
		
		Feed feed = feedService.saveFeed(user, message, readPerm);
		
		List<String> files = feedService.saveFeedImages(feed, request);
		
		Set<FeedContent> contents = new HashSet<FeedContent>();
		for (String file : files) {
			FeedContent content = new FeedContent(FeedType.IMAGE, file, feed);
			contents.add(content);
		}
		
		feedService.saveFeedContents(contents);
		
		feed.setContents(contents);
		
		return emptyResponse();
	}
	
	@Api(auth=true)
	@RequestMapping(value="/newsfeed", method = RequestMethod.GET)
	public @ResponseBody BodyResponse getNewsFeed(
			@RequestHeader String authKey, 
			@RequestParam(defaultValue="0") Long lastId,
			@RequestParam(defaultValue="REFRESH") String order,
			@RequestParam(defaultValue="30") Integer size) throws UserNotFoundException {
		log.info("/api/feed/newsfeed");
		User user = userService.loadUserByAuthKey(authKey);
		List<FeedVo> newsFeeds = FeedVo.transferVo(feedService.getNewsFeeds(user, lastId, ListOrder.valueOf(order), size));
		return new DataResponse(newsFeeds);
	}
	
	@RequestMapping(value="/popfeed", method = RequestMethod.GET)
	public @ResponseBody BodyResponse getPopFeed(
			@RequestParam(defaultValue="1") Integer page, 
			@RequestParam(defaultValue="30") Integer size) throws UserNotFoundException {
		
		List<FeedVo> popFeeds = FeedVo.transferVo(feedService.getPopularFeeds(page - 1, size));
		return new DataResponse(popFeeds);
	}
	
	@RequestMapping(value = "/{feedId}", method = RequestMethod.GET)
	public @ResponseBody FeedDetailVo getFeed(@PathVariable Long feedId) {
		Feed feed = feedService.getFeed(feedId);
		FeedVo feedVo = FeedVo.transferVo(new FeedVo(), feed);
		List<FeedLikeVo> likes = FeedLikeVo.transferVo(feed.getLikes());
		List<FeedReplyVo> replies = FeedReplyVo.transferVo(feed.getReplies());
		FeedDetailVo feedDetail = new FeedDetailVo(feedVo, likes, replies);
		
		return feedDetail;
	}
	
	@Api(auth=true)
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value = "/{feedId}/like", method = RequestMethod.POST)
	public ModelAndView likeFeed(@RequestHeader String authKey, @PathVariable Long feedId, @RequestParam Integer type) throws Exception {
		User user = userService.loadUserByAuthKey(authKey); 
		Feed feed = feedService.getFeed(feedId);
		FeedLike like = new FeedLike(type, feed, user);
		feed.addLike(like);
		
		feedService.saveFeedLike(like);
		
		return emptyResponse();
	}
	
	@Api(auth=true)
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value = "/{feedId}/like/{id}", method = RequestMethod.DELETE)
	public ModelAndView cancelLike(@RequestHeader String authKey, @PathVariable Long feedId, @PathVariable Long id) throws Exception {
		FeedLike like = feedService.getFeedLike(id);
		
		if (authKey.equals(like.getUser().getAuthKey())) {
			feedService.cancelFeedLike(id);
		}
		
		return emptyResponse();
	}
	
	@Api(auth=true)
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value = "/{feedId}/reply", method = RequestMethod.POST)
	public ModelAndView replyFeed(@RequestHeader String authKey, @PathVariable Long feedId, @RequestParam Integer type, @RequestParam String message) throws Exception {
		User user = userService.loadUserByAuthKey(authKey); 
		Feed feed = feedService.getFeed(feedId);
		FeedReply reply = new FeedReply(type, message, feed, user);
		feed.addReply(reply);
		
		feedService.saveFeedReply(reply);
		
		return emptyResponse();
	}
	
	@Api(auth=true)
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value = "/{feedId}/reply/{id}", method = RequestMethod.DELETE)
	public ModelAndView removeReply(@RequestHeader String authKey, @PathVariable Long feedId, @PathVariable Long id) throws Exception {
		FeedReply reply = feedService.getFeedReply(id);
		
		if (authKey.equals(reply.getUser().getAuthKey())) {
			feedService.removeReply(reply.getId());
		}
		
		return emptyResponse();
	}
	
	
}
