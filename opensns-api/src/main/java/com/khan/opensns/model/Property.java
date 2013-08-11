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

public class Property implements Serializable {
	
	private static final long serialVersionUID = -471003942862925161L;

	private String name;
	
	private String value;

	public Property() {}
	
	public Property(String name, String value) {
		setName(name);
		setValue(value);
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
