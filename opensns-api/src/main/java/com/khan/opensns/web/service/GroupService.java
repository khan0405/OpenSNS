package com.khan.opensns.web.service;

import com.khan.opensns.web.model.Group;
import com.khan.opensns.web.model.User;
import com.khan.opensns.web.vo.GroupVo;

public interface GroupService {
	
	public Group getGroup(Long id);
	
	public Group saveGroup(User owner, GroupVo group);
	
	public Group saveGroup(Group group);
	
	public 
}
