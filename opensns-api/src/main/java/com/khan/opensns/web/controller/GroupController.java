package com.khan.opensns.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.khan.opensns.web.dto.BodyResponse;
import com.khan.opensns.web.model.Group;
import com.khan.opensns.web.model.User;
import com.khan.opensns.web.service.GroupService;
import com.khan.opensns.web.service.UserService;
import com.khan.opensns.web.vo.GroupVo;

@Controller
@RequestMapping("/api/group")
public class GroupController extends BaseController {
	
	@Autowired
	private GroupService groupService;

	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView createGroup(@RequestHeader String authKey, @RequestBody GroupVo group) throws Exception {
		User owner = userService.loadUserByAuthKey(authKey);
		
		groupService.saveGroup(owner, group);
		
		return emptyResponse();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody BodyResponse getGroupList(@RequestParam Integer page, @RequestParam Integer size) throws Exception {
		
		
		
		return null;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody GroupVo getGroup(@PathVariable Long id) throws Exception {
		Group group = groupService.getGroup(id);
		
		return new GroupVo(group);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ModelAndView updateGroup(@RequestHeader String authKey, @PathVariable Long id, @RequestBody GroupVo vo) throws Exception {
		User user = userService.loadUserByAuthKey(authKey);
		Group group = groupService.getGroup(id);
		
		if (user.getId() != group.getOwner().getId()) {
			throw new AuthorizationServiceException("no permissions");
		}
		
		GroupVo target = new GroupVo(group);
		target.wrap(vo);
		
		groupService.saveGroup(group.getOwner(), target);
		
		return emptyResponse();
	}
	
	
}
