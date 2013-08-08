package com.khan.opensns.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.khan.opensns.web.dto.BodyResponse;
import com.khan.opensns.web.dto.ResponseError;
import com.khan.opensns.web.service.BadParameterException;

public class BaseController {
	
	protected Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Exception handler
	 * 
	 * @param throwable 	exception object.
	 * @param response		response for error
	 * @return BodyResponse view. 
	 */
	@ExceptionHandler
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody BodyResponse handleException(Throwable throwable, HttpServletResponse response) {
		log.warn("Handle Exception....", throwable);
		return createResponseError(throwable.getClass().getSimpleName(), throwable.getMessage());
	}
	
	@ExceptionHandler(AuthorizationServiceException.class)
	@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
	public @ResponseBody BodyResponse handleAuthorizationServiceException(Throwable throwable, HttpServletResponse response) {
		log.warn("Handle Exception....", throwable);
		return createResponseError(throwable.getClass().getSimpleName(), throwable.getMessage());
	}
	
	@ExceptionHandler(BadParameterException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public @ResponseBody BodyResponse handleBadParameterException(Throwable throwable, HttpServletResponse response) {
		log.warn("Handle Exception....", throwable);
		return createResponseError(throwable.getClass().getSimpleName(), throwable.getMessage());
	}
	
	/**
	 * Create Error response view.
	 * 
	 * @param error 			error name.
	 * @param description		description for error.
	 * @param response			response for error.
	 * 
	 * @return Error BodyResponse view. 
	 */
	public BodyResponse createResponseError(String error, String description) {
		return new ResponseError(error, description);
	}
	
	public ModelAndView emptyResponse() {
		return new ModelAndView("empty");
	}
}
