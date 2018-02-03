package com.mikelcuenca.app.service.infrastructure.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

public class CustomAccessDeniedHandler extends AccessDeniedHandlerImpl {

	public static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

	@Autowired
	AuthenticationHolderImpl authenticationHolder;
	
	@Override
	public void handle(
		HttpServletRequest request,
		HttpServletResponse response, 
		AccessDeniedException exc) throws IOException, ServletException {
	   
		Authentication auth = authenticationHolder.getAuthentication();
		if (auth != null) {
	    logger.warn("User: " + auth.getName() 
	    				+ " attempted to access the protected URL: "
	    					+ request.getRequestURI());
	    }
	
		response.sendRedirect("accessDenied");
	}
}
