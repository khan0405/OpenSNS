/**
 * Project		: OpenSNSWeb
 * FileName		: FriendController.java
 * Package		: com.khan.opensns.api.controller
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 7. 1. 오전 11:18:35
 */
package com.khan.opensns.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.khan.opensns.api.service.FriendService;
import com.khan.opensns.api.service.UserService;
import com.khan.opensns.model.User;
import com.khan.opensns.model.persistence.FriendType;

/**
 * <PRE>
 * @class	: com.khan.opensns.api.controller.FriendController
 * @file	: FriendController.java
 * @brief	: 
 * @author	: KHAN
 * @date	: 2013. 7. 1. 오전 11:18:35
 * </PRE>
 */
@Controller
@RequestMapping("/api")
public class FriendController extends BaseController {
	
	private FriendService friendService;
	
	private UserService userService;
	
	@RequestMapping(value="/user/friend/{name}", method=RequestMethod.POST)
	public ModelAndView requestFriend(@RequestHeader String authKey, @PathVariable String name) throws Exception {
		
		User user = userService.loadUserByAuthKey(authKey);
		
		friendService.requestFriend(user, name);
		
		return emptyResponse();
	}
	
	@RequestMapping(value="/user/friend/{name}", method=RequestMethod.DELETE)
	public ModelAndView breakFriend(@RequestHeader String authKey, @PathVariable String name) throws Exception {
		
		User user = userService.loadUserByAuthKey(authKey);
		
		friendService.breakFriend(user, name);
		
		return emptyResponse();
	}
	
	@RequestMapping(value="/user/friend/{name}", method=RequestMethod.PUT)
	public ModelAndView modifyFriend(@RequestHeader String authKey, @PathVariable String name, @RequestParam Integer type) throws Exception {
		
		User user = userService.loadUserByAuthKey(authKey);
		User friendUser = userService.loadUserByName(name);
		
		switch (FriendType.valueOf(type)) {
		case FRIEND:
			friendService.acceptFriend(user, friendUser);
			break;
		case BLOCK:
			friendService.blockFriend(user, friendUser);
			break;
		default:
			throw new IllegalArgumentException("Invalid friend type.");
		}
		
		return emptyResponse();
	}
}
