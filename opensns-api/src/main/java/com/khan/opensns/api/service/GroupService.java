package com.khan.opensns.api.service;

import java.util.List;

import com.khan.opensns.model.Group;
import com.khan.opensns.model.User;
import com.khan.opensns.vo.GroupVo;

public interface GroupService {
	
	public Group getGroup(Long id);
	
	public Group saveGroup(User owner, GroupVo group);
	
	public Group saveGroup(Group group);
	
	public void removeGroup(Long id);
	
	public List<Group> findGroup(User user, String searchText);
	
	public List<Group> getGroups(Integer page, Integer size);
	
	public List<Group> getUserGroups(User user, Integer page, Integer size);
	
	public List<Group> getRecommandGroups(User user, Integer page, Integer size);
}
