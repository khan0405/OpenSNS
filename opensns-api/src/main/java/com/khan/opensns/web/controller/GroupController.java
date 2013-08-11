package com.khan.opensns.web.controller;

import java.util.List;

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

import com.khan.opensns.dto.BodyResponse;
import com.khan.opensns.dto.DataResponse;
import com.khan.opensns.model.Group;
import com.khan.opensns.model.User;
import com.khan.opensns.service.GroupService;
import com.khan.opensns.service.UserService;
import com.khan.opensns.vo.GroupVo;

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
	public @ResponseBody BodyResponse findGroup(@RequestHeader String authKey, @RequestParam String searchText) throws Exception {
		User user = userService.loadUserByAuthKey(authKey);
		
		List<Group> groups = groupService.findGroup(user, searchText);
		
		DataResponse response = new DataResponse(groups);
		
		return response;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody BodyResponse getRecommandGroups(@RequestHeader String authKey,@RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="30") Integer size) throws Exception {
		User user = userService.loadUserByAuthKey(authKey);
		
		List<Group> groups = groupService.getRecommandGroups(user, page, size);
		
		return new DataResponse(groups);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody BodyResponse getGroupList(@RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="30") Integer size) throws Exception {
		List<Group> groups = groupService.getGroups(page, size);
		
		return new DataResponse(groups);
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
