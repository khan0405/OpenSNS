package com.khan.opensns.web.service;

public class BadParameterException extends Exception {
	
	private static final long serialVersionUID = -6318215595500701806L;

	public BadParameterException(String message) {
		super(message);
	}
	
	public BadParameterException(String message, Throwable e) {
		super(message, e);
	}
	
	public BadParameterException(Throwable e) {
		super(e);
	}
}
