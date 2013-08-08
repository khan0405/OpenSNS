package com.khan.opensns.web.controller;

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
/**
 * This class for Controller, implement base exception handler methods and empty view method.
 * 
 * @author KHAN
 *
 */
public abstract class BaseController {
	
	protected Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Exception handler
	 * 
	 * @param throwable 	exception object.
	 * @param response		response for error
	 * @return The api error information view. 
	 */
	@ExceptionHandler
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody BodyResponse handleException(Throwable throwable) {
		log.warn("Handle Exception....", throwable);
		return createResponseError(throwable.getClass().getSimpleName(), throwable.getMessage());
	}
	
	/**
	 * Exception handler for Authorization Service
	 * 
	 * @param throwable		exception object
	 * @return The api error information view.
	 */
	@ExceptionHandler(AuthorizationServiceException.class)
	@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
	public @ResponseBody BodyResponse handleAuthorizationServiceException(Throwable throwable) {
		log.warn("Handle Exception....", throwable);
		return createResponseError(throwable.getClass().getSimpleName(), throwable.getMessage());
	}
	
	/**
	 * Exception handler for Bad parameter.
	 * 
	 * @param throwable		exception object
	 * @return The api error information view.
	 */
	@ExceptionHandler(BadParameterException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public @ResponseBody BodyResponse handleBadParameterException(Throwable throwable) {
		log.warn("Handle Exception....", throwable);
		return createResponseError(throwable.getClass().getSimpleName(), throwable.getMessage());
	}
	
	/**
	 * Create Error response view.
	 * 
	 * @param error 			error name.
	 * @param description		description for error.
	 * 
	 * @return Error BodyResponse view. 
	 */
	public BodyResponse createResponseError(String error, String description) {
		return new ResponseError(error, description);
	}
	
	/**
	 * Return empty view to client.
	 * @return empty model view.
	 */
	public ModelAndView emptyResponse() {
		return new ModelAndView("empty");
	}
}
