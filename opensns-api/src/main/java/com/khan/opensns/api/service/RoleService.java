/**
 * Project		: BookMarkerWeb
 * FileName		: RoleService.java
 * Package		: com.khan.bookmarker.service
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 6. 25. 오후 1:09:23
 */
package com.khan.opensns.api.service;

import java.util.List;

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
public interface RoleService {
	
    public List<Role> getRoles();

    public Role getRole(String rolename);

    public Role saveRole(Role role);

    public void removeRole(String name);

}
