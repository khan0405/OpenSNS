/**
 * Project		: OpenSNSWeb
 * FileName		: FeedController.java
 * Package		: com.khan.opensns.web.controller
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 7. 1. 오전 11:18:42
 */
package com.khan.opensns.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khan.opensns.dto.BodyResponse;
import com.khan.opensns.dto.DataResponse;
import com.khan.opensns.model.User;
import com.khan.opensns.service.FeedService;
import com.khan.opensns.service.UserNotFoundException;
import com.khan.opensns.service.UserService;
import com.khan.opensns.vo.FeedVo;
import com.khan.opensns.web.annotation.Api;

/**
 * <PRE>
 * @class	: com.khan.opensns.web.controller.FeedController
 * @file	: FeedController.java
 * @brief	: 
 * @author	: KHAN
 * @date	: 2013. 7. 1. 오전 11:18:42
 * </PRE>
 */
@Controller
@RequestMapping(value={"/api/{name}/feed"})
public class UserFeedController extends BaseController {
	
	@Autowired
	private FeedService feedService;
	
	@Autowired
	private UserService userService;
	
	@Api(auth=true)
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody BodyResponse getUserFeeds(
			@RequestHeader String authKey, 
			@PathVariable String name, 
			@RequestParam(defaultValue="1") Integer page, 
			@RequestParam(defaultValue="30") Integer size) throws UserNotFoundException {
		
		User user = userService.loadUserByAuthKey(authKey);
		List<FeedVo> userFeeds = FeedVo.transferVo(feedService.getUserFeeds(user, name, page, size));
		return new DataResponse(userFeeds);
	}
	
}
