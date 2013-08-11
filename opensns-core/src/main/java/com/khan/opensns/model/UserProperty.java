/**
 * Project		: opensnsWeb
 * FileName		: Role.java
 * Package		: com.khan.opensns.model
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 6. 25. 오전 10:14:36
 */
package com.khan.opensns.model;

import java.io.Serializable;

public class UserProperty implements Serializable {
	
	private static final long serialVersionUID = -471003942862925161L;
	
	private Long id;
	
	private String name;
	
	private String value;
	
	private User user;
	
	public UserProperty() {}
	
	public UserProperty(String name, String value) {
		setName(name);
		setValue(value);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
		this.id = user.getId();
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
	 * @return description
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param description Set to description
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
