
package com.khan.opensns.dao;

import java.util.List;

import com.khan.opensns.model.Role;

public interface RoleDao extends GenericDao<Role, Integer> {
	public List<Role> getAll();
	public Role getRoleByName(String name);
	public void deleteRole(String name);
}
