package com.mikelcuenca.app.service.infrastructure;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.mikelcuenca.app._model.infrastructure.ErrorInfo;
import com.mikelcuenca.app.utils.Messages;
import com.mikelcuenca.app.utils.StackTraceManager;

public class CustomMappingExceptionResolver extends SimpleMappingExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger(CustomMappingExceptionResolver.class);

	@Autowired
	ErrorInfo errorInfo;

	@Autowired
	Messages messages;
	
	public CustomMappingExceptionResolver() {
			    
	}
	
	@Override
	public String buildLogMessage(Exception e, HttpServletRequest request) {
		return "MVC exception: " + e.getLocalizedMessage();
	}
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
	    HttpServletResponse response, Object handler, Exception e) {

		ModelAndView mav = super.doResolveException(request, response, handler, e);
		
		errorInfo.setError(messages.get("exception.resolve.unexpected"));
		errorInfo.setMessageForUser(e.getMessage());
		errorInfo.setNameClass(e.getClass().toString());
		errorInfo.setStackTrace(StackTraceManager.getStackTrace(e));
		errorInfo.setUri(request.getRequestURI());
	      
	    mav.addObject("errorInfo", errorInfo);
		mav.addObject("url", request.getRequestURL());
	    
		logger.info(messages.get("exception.resolve.unexpected"));
		logger.info(buildLogMessage(e, request));
		logger.info(StackTraceManager.getStackTrace(e));
	    
		return mav;
	}
}
