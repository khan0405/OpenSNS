/*
 * @(#)SecurityUtil.java
 *
 * Copyright (c) 2009 Moduad, Inc.
 * All rights reserved.
 */
package com.khan.opensns.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Velocity Spring Secutiry Tool
 * 
 * <pre>
 * Inside your spring-servlet.xml do something like this:
 * 
 * &lt;bean class=&quot;org.springframework.web.servlet.view.velocity.VelocityViewResolver&quot;
 *       p:prefix=&quot;/&quot;
 *       p:suffix=&quot;.vm&quot;
 *       p:cache=&quot;true&quot;
 *       p:exposeSpringMacroHelpers=&quot;true&quot;&gt;
 *     &lt;property name=&quot;attributesMap&quot;&gt;
 *         &lt;map&gt;
 *             &lt;entry key=&quot;authz&quot;&gt;&lt;bean class=&quot;com.moduad.web.util.SecurityUtil&quot; /&gt;&lt;/entry&gt;
 *         &lt;/map&gt;
 *     &lt;/property&gt;
 * &lt;/bean&gt;
 * 
 * At which point you'll have an $authz in your velocity 1.6 templates like this:
 * (this uses a feature not available in velocity &lt; 1.6)
 * 
 * Code:
 *     Welcome back, ${authz.principal}!
 *     #if($authz.allGranted(&quot;ROLE_MODEL&quot;,&quot;ROLE_PLAYING&quot;)) 
 *          ...
 *     #end
 * </pre>
 * 
 * @author Sehwan Noh (sehwan@java2go.net)
 * @version 1.0
 */
public class SecurityUtil {

    /**
     * 
     * @return
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 
     * @return
     */
    public static void setAuthentication(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * 
     * @return
     */
    public static Object getPrincipal() {
        Authentication authentication = getAuthentication();
        if (authentication != null) {
            return authentication.getPrincipal();
        }
        return null;
    }

    /**
     * 
     * @return
     */
    public static String getUsername() {
        Object principal = getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return null;
    }

    /**
     * Is the user granted all of the supplied roles
     * 
     * @param roles a string array of roles
     * @return true if user has all of the listed roles, otherwise false
     */
    public static boolean allGranted(String[] roles) {
        Set<String> userRoles = getUserRoles();
        for (String role : roles) {
            if (userRoles.contains(role))
                continue;
            return false;
        }
        return true;
    }

    /**
     * Is the user granted any of the supplied roles
     * 
     * @param roles a string array of roles
     * @return true if user has any of the listed roles, otherwise false
     */
    public static boolean anyGranted(String[] roles) {
        Set<String> userRoles = getUserRoles();
        for (String role : roles) {
            if (userRoles.contains(role))
                return true;
        }
        return false;
    }

    /**
     * is the user granted none of the supplied roles
     * 
     * @param roles a string array of roles
     * @return true only if none of listed roles are granted
     */
    public static boolean noneGranted(String[] roles) {
        Set<String> userRoles = getUserRoles();
        for (String role : roles) {
            if (userRoles.contains(role))
                return false;
        }
        return true;
    }

    private static Set<String> getUserRoles() {
        Set<String> roles = new HashSet<String>();
        Object principal = getPrincipal();
        if (principal != null && principal instanceof UserDetails) {
        	Collection<? extends GrantedAuthority> gas = ((UserDetails) principal).getAuthorities();
            for (GrantedAuthority ga : gas) {
                roles.add(ga.getAuthority());
            }
        }
        return roles;
    }
}
