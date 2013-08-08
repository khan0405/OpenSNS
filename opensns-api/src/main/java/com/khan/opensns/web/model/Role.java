/**
 * Project		: opensnsWeb
 * FileName		: Role.java
 * Package		: com.khan.opensns.model
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 6. 25. 오전 10:14:36
 */
package com.khan.opensns.web.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 3633610376937153187L;

	private Integer id;
	
	private String name;
	
	private String description;

    private Set<User> users = new HashSet<User>();

	public Role() {}
	
	public Role(Integer id, String name, String description) {
		setId(id);
		setName(name);
		setDescription(description);
	}
	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id Set to id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name Set to name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * @param users Set to users
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description Set to description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAuthority() {
		return getName();
	}

}
