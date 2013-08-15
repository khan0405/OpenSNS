package com.khan.opensns.api.dao;

import java.util.List;

import com.khan.opensns.model.Role;

public interface RoleDao extends BaseDao<Role, Integer> {
	public List<Role> getAll();
	public Role getRoleByName(String name);
	public void deleteRole(String name);
}
