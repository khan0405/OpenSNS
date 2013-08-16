/**
 * Project		: BookMarkerWeb
 * FileName		: RoleService.java
 * Package		: com.khan.bookmarker.service
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 6. 25. 오후 1:09:23
 */
package com.khan.opensns.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khan.opensns.api.dao.RoleDao;
import com.khan.opensns.api.service.RoleService;
import com.khan.opensns.model.Role;

/**
 * <PRE>
 * @class	: com.khan.bookmarker.service.RoleService
 * @file	: RoleService.java
 * @brief	: 
 * @author	: KHAN
 * @date	: 2013. 6. 25. 오후 1:09:23
 * </PRE>
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Autowired
    private RoleDao roleDao;

    public List<Role> getRoles() {
        return roleDao.getAll();
    }

    public Role getRole(String rolename) {
        return roleDao.getRoleByName(rolename);
    }

	@Transactional
    public Role saveRole(Role role) {
        return roleDao.save(role);
    }

	@Transactional
    public void removeRole(String name) {
    	Role role = getRole(name);
    	roleDao.delete(role);
    }

}
