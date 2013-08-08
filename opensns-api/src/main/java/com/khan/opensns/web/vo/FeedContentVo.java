package com.khan.opensns.web.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonAutoDetect(value=JsonMethod.FIELD)
public class FeedContentVo implements Serializable {
	@JsonIgnore private static final long serialVersionUID = -3907752128606593157L;
	@JsonProperty private String content;
	@JsonProperty private Integer type;
	
	public FeedContentVo() {}
	
	public FeedContentVo(String content, Integer type) {
		setContent(content);
		setType(type);
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
