package com.khan.opensns.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonAutoDetect(value=JsonMethod.FIELD)
public class LoginVo implements Serializable {
	
	@JsonIgnore
	private static final long serialVersionUID = -7360185651307233549L;
	
	@JsonProperty private String username;
	@JsonProperty private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
