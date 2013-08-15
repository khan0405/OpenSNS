/**
 * Project		: OpenSNSWeb
 * FileName		: FeedService.java
 * Package		: com.khan.opensns.api.service
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 7. 1. 오후 4:45:28
 */
package com.khan.opensns.api.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.khan.opensns.api.dao.FeedContentDao;
import com.khan.opensns.api.dao.FeedDao;
import com.khan.opensns.api.dao.FeedLikeDao;
import com.khan.opensns.api.dao.FeedReplyDao;
import com.khan.opensns.model.Feed;
import com.khan.opensns.model.FeedContent;
import com.khan.opensns.model.FeedLike;
import com.khan.opensns.model.FeedReply;
import com.khan.opensns.model.Friend;
import com.khan.opensns.model.User;
import com.khan.opensns.model.persistence.FriendType;
import com.khan.opensns.service.FeedService;
import com.khan.opensns.service.FriendService;
import com.khan.opensns.service.ImageService;
import com.khan.opensns.service.UserNotFoundException;
import com.khan.opensns.service.UserService;
import com.khan.opensns.vo.FeedLikeVo;
import com.khan.opensns.vo.FeedReplyVo;
import com.khan.opensns.vo.ImageVo;

/**
 * <PRE>
 * @class	: com.khan.opensns.api.service.FeedService
 * @file	: FeedService.java
 * @brief	: 
 * @author	: KHAN
 * @date	: 2013. 7. 1. 오후 4:45:28
 * </PRE>
 */
@Service("feedService")
public class FeedServiceImpl implements FeedService {
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	private FeedDao feedDao;
	
	@Autowired
	private FeedContentDao feedContentDao;
	
	@Autowired
	private FeedReplyDao feedReplyDao;
	
	@Autowired
	private FeedLikeDao feedLikeDao;

	@Value(value="#{setting['image.feed']}")
	private String FEED_DIR;

	@Value(value="#{setting['image.feed.thumb']}")
	private String FEED_THUMB_DIR;

	@Transactional
	public Feed saveFeed(Feed feed) {
		return feedDao.save(feed);
	}

	@Transactional
	public FeedContent saveFeedContent(FeedContent content) {
		return  feedContentDao.save(content);
	}

	@Override
	@Transactional
	public Feed saveFeed(User user, String message, Integer readPerm) {
		Feed feed = new Feed();
		feed.setUser(user);
		feed.setMessage(message);
		feed.setReadPerm(readPerm);
		feed.setCreateDate(new Date());
		
		return saveFeed(feed);
	}

	@Override
	public FeedContent saveFeedContent(Integer type, String content, Feed feed) {
		FeedContent feedContent = new FeedContent();
		feedContent.setType(type);
		feedContent.setContent(content);
		feedContent.setFeed(feed);
		return saveFeedContent(feedContent);
	}
	
	@Override
	@Transactional
	public Set<FeedContent> saveFeedContents(Set<FeedContent> contents) {
		return (Set<FeedContent>) feedContentDao.save(contents);
	}

	@Override
	public List<String> saveFeedImages(Feed feed, MultipartRequest request) throws IOException {
		List<String> files = new ArrayList<String>();
		
		// image save
		List<MultipartFile> images = request.getFiles("image");
		String filename = feed.getId() + "_" + new Date().toString();
		int idx = 0;
		
		for (MultipartFile image : images) {
			filename = idx < 10 ? filename + "_0" + idx : filename + "_" + idx; 
			ImageVo iVo = imageService.imageSave(FEED_DIR, FEED_THUMB_DIR, filename, image);
			files.add(iVo.getImageSrc());
		}
		
		return files;
	}
	

	@Override
	public Feed getFeed(Long id) {
		return feedDao.getById(id);
	}

	@Override
	public List<Feed> getPopularFeeds(Integer page, Integer size) {
		return feedDao.getPopular(page, size);
	}

	@Override
	public List<Feed> getNewsFeeds(User user, Integer page, Integer size) {
		return feedDao.getNewsFeed(user, page, size);
	}
	
	@Override
	public List<Feed> getUserFeeds(User user, String targetName, Integer page, Integer size) throws UserNotFoundException {
		if (user.getName().equals(targetName)) {
			return feedDao.getByUser(user, page, size);
		} else {
			User targetUser = userService.loadUserByName(targetName);
			
			/* Block user check */
			List<Friend> blockUsers = friendService.getFriend(targetUser.getId(), FriendType.BLOCK);
			for (Friend blockUser : blockUsers) {
				if (blockUser.getFriend().getId() == user.getId()) {
					return null;
				}
			}
			
			return feedDao.getUserFeed(user, targetUser, page, size);
		}
	}

	@Override
	public FeedReply getFeedReply(Long id) {
		return feedReplyDao.getById(id);
	}
	
	@Override
	@Transactional
	public FeedReply saveFeedReply(FeedReply reply) {
		return feedReplyDao.save(reply);
	}

	@Override
	@Transactional
	public FeedReplyVo saveReply(Long feedId, User user, FeedReplyVo replyVo) {
		Feed feed = getFeed(feedId);
		FeedReply reply = new FeedReply(replyVo.getType(), replyVo.getMessage(), feed, user);
		saveFeedReply(reply);
		replyVo.setId(reply.getId());
		return replyVo;
	}

	@Override
	@Transactional
	public void removeReply(Long replyId) {
		feedReplyDao.delete(replyId);
	}

	@Override
	public FeedLike getFeedLike(Long id) {
		return feedLikeDao.getById(id);
	}

	@Override
	@Transactional
	public FeedLike saveFeedLike(FeedLike like) {
		return feedLikeDao.save(like);
	}

	@Override
	@Transactional
	public FeedLikeVo saveFeedLike(Long feedId, User user, FeedLikeVo likeVo) {
		Feed feed = getFeed(feedId);
		FeedLike like = new FeedLike(likeVo.getType(), feed, user);
		saveFeedLike(like);
		likeVo.setId(like.getId());
		return likeVo;
	}

	@Override
	@Transactional
	public void cancelFeedLike(Long likeId) {
		feedLikeDao.delete(likeId);
	}
}
