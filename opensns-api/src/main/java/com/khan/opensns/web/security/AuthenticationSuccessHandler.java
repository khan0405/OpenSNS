/**
 * Project		: OpenSNSWeb
 * FileName		: AuthenticationSuccessHandler.java
 * Package		: com.khan.opensns.web.security
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 6. 27. 오전 10:12:17
 */
package com.khan.opensns.web.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.khan.opensns.web.model.User;
import com.khan.opensns.web.service.UserService;

/**
 * <PRE>
 * @class	: com.khan.opensns.web.security.AuthenticationSuccessHandler
 * @file	: AuthenticationSuccessHandler.java
 * @brief	: 
 * @author	: KHAN
 * @date	: 2013. 6. 27. 오전 10:12:17
 * </PRE>
 */
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * @param request
	 * @param response
	 * @param authentication
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		super.onAuthenticationSuccess(request, response, authentication);
		if (authentication.getPrincipal() instanceof User) {
			User user = (User)authentication.getPrincipal();
			user.setLastLoginDate(new Date());
			try {
				userService.saveUser(user);
			} catch (Exception e) {
				log.error("save user error!!", e);
			}
		}
		log.warn("Principal : {}",ToStringBuilder.reflectionToString(authentication.getPrincipal(), ToStringStyle.MULTI_LINE_STYLE));
	}
	
}
