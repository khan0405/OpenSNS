package com.khan.opensns.web.service.impl;

import java.lang.annotation.Annotation;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.khan.opensns.service.ApiUrlService;
import com.khan.opensns.web.annotation.Api;

@Service("apiUrlService")
public class ApiUrlServiceImpl implements ApiUrlService {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RequestMappingHandlerMapping handlerMapping;
	
	@Override
	public boolean isSecurityApi(HttpServletRequest request) {
		String lookupPath = handlerMapping.getUrlPathHelper().getLookupPathForRequest(request);
		if (log.isInfoEnabled())
			log.info("lookup path : {}", lookupPath);
		
		for (Entry<RequestMappingInfo, HandlerMethod> entry : handlerMapping.getHandlerMethods().entrySet()) {
			RequestMappingInfo mapping = entry.getKey();
			Set<String> patterns = mapping.getPatternsCondition().getPatterns();
			for (String pattern : patterns) {
				if (matchPattern(pattern, lookupPath)) {
					HandlerMethod method = entry.getValue();
					for (Annotation ann : method.getMethod().getAnnotations()) {
						log.info("{} annotation : {}", method.getMethod().getName(), ann.annotationType().getCanonicalName());
					}
					Api api = method.getMethodAnnotation(Api.class);
					if (api == null)
						log.info("method({})'s api is null", method.getMethod().getName());
					else if (!api.auth()) {
						log.info("method({})'s api auth false");
					}
					return api != null && api.auth();
				}
			}
		}
		log.info("non security path : {}", lookupPath);
		return false;
	}

	private static boolean matchPattern(String pattern, String path) {
		boolean result = false;
		AntPathMatcher matcher = new AntPathMatcher();
		if (matcher.match(pattern, path)) {
			result = true;
		}
		return result;
	}
}
