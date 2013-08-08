package com.khan.opensns.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody GroupVo getGroup(@PathVariable Long id) throws Exception {
		Group group = groupService.getGroup(id);
		
		return new GroupVo(group);
	}
	
	
}
