package com.khan.opensns.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.khan.opensns.web.model.User;
import com.khan.opensns.web.service.ApiUrlService;
import com.khan.opensns.web.service.BadParameterException;
import com.khan.opensns.web.service.UserNotFoundException;
import com.khan.opensns.web.service.UserService;

@Component(value="authInterceptor")
public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ApiUrlService apiUrlService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (apiUrlService.isSecurityApi(request)) {
			log.info("Security path : {}", request.getRequestURI());
			String authKey = request.getHeader("authKey");
			if (authKey == null)
				throw new BadParameterException("Auth Key is Required!");
			try {
				User user = userService.loadUserByAuthKey(authKey);
				if (user != null)
					return true;
			} catch (UserNotFoundException e) {
				log.debug("User not found....", e);
				throw new BadCredentialsException("Invalid Auth key");
			}
		}
		return true;
	}

}
