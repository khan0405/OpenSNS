/**
 * Project		: OpenSNSWeb
 * FileName		: UserController.java
 * Package		: com.khan.opensns.web.controller
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 7. 1. 오전 11:18:25
 */
package com.khan.opensns.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.khan.opensns.web.annotation.Api;
import com.khan.opensns.web.dto.BodyResponse;
import com.khan.opensns.web.model.User;
import com.khan.opensns.web.service.FeedService;
import com.khan.opensns.web.service.UserExistsException;
import com.khan.opensns.web.service.UserNotFoundException;
import com.khan.opensns.web.service.UserService;
import com.khan.opensns.web.util.PasswordUtil;
import com.khan.opensns.web.vo.LoginVo;
import com.khan.opensns.web.vo.UserVo;


/**
 * <PRE>
 * @class	: com.khan.opensns.web.controller.UserController
 * @file	: UserController.java
 * @brief	: 
 * @author	: KHAN
 * @date	: 2013. 7. 1. 오전 11:18:25
 * </PRE>
 */
@Controller
@RequestMapping(value="/api/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FeedService feedService;
	
	@Value(value="#{setting['password.algorithm']}")
	private String algorithm;
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView createUser(@RequestBody UserVo user) throws Exception {
		log.info("Create user : {}", user.getUsername());
		
		user.setPassword(PasswordUtil.encodePasswordToBase64(user.getPassword(), algorithm));
		userService.saveUser(user);
		
		return emptyResponse();
	}

	@Api(auth=true)
	@RequestMapping(value="/{name}", method=RequestMethod.GET)
	public @ResponseBody UserVo getUser(@PathVariable String name) throws Exception {
		log.debug("Get user({})'s profile", name);
		
		return new UserVo(userService.loadUserByName(name));
	}
	
	@Api(auth=true)
	@RequestMapping(value="/{name}", method=RequestMethod.PUT)
	public @ResponseBody UserVo updateUser(@RequestHeader String authKey, @PathVariable String name, @RequestBody UserVo user) throws Exception {
		log.debug("Update user({})'s profile", name);
		
		User owner = userService.loadUserByAuthKey(authKey);
		log.debug("Owner({}), user({})", owner.getName(), name);
		if (owner.getName().equals(name)) {
			if (user.getPassword() != null)
				user.setPassword(PasswordUtil.encodePasswordToBase64(user.getPassword(), algorithm));
			UserVo target = new UserVo(owner);
			target.wrap(user);
			log.debug("user({})", user.toString());
			log.debug("target({})", target.toString());
			target.setUser(userService.saveUser(target));
			
			return target;
		} 
		
		throw new AuthorizationServiceException("no permission.");
	}
	
	@Api(auth=true)
	@RequestMapping(value="/{name}", method=RequestMethod.DELETE)
	public ModelAndView deleteUser(@RequestHeader String authKey, @PathVariable String name, @RequestBody LoginVo loginVo) throws Exception {
		log.debug("Delete user({})", name);

		User owner = userService.loadUserByAuthKey(authKey);
		User user = userService.loadUserByName(name);
		
		if (loginVo.getUsername().equals(user.getUsername()) && loginVo.getPassword().equals(user.getPassword()) && owner.getId() == user.getId()) {
			userService.removeUser(user.getId());
		}
		
		return emptyResponse();
	}
	
	/* 이미지 전송 */
	@Api(auth=true)
	@RequestMapping(value="/{name}/profile", method=RequestMethod.POST)
	public ModelAndView postProfileImage(@PathVariable String name, MultipartFile image) throws Exception {
		log.debug("Post {}'s image", name);
		
		User user = userService.loadUserByName(name);
		
		userService.saveUserProfile(user, image);
		
		return emptyResponse();
	}

	@Api(auth=true)
	@RequestMapping(value="/{name}/background", method=RequestMethod.POST)
	public ModelAndView postBackgroundImage(@PathVariable String name, MultipartFile image) throws Exception {
		log.debug("Post {}'s image", name);
		
		User user = userService.loadUserByName(name);
		
		userService.saveUserBackground(user, image);
		
		return emptyResponse();
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler(UserNotFoundException.class)
	public @ResponseBody BodyResponse handleNotFoundException(Throwable throwable, HttpServletResponse response) {
		return createResponseError("user not found", throwable.getMessage());
	}

	@ResponseStatus(value=HttpStatus.CONFLICT)
	@ExceptionHandler(UserExistsException.class)
	public @ResponseBody BodyResponse handleConflictException(Throwable throwable, HttpServletResponse response) {
		return createResponseError("user exists", throwable.getMessage());
	}
	
}
