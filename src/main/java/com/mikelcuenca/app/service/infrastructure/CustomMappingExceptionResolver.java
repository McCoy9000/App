package com.mikelcuenca.app.service.infrastructure;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.mikelcuenca.app._model.infrastructure.ErrorInfo;
import com.mikelcuenca.app.utilidades.StackTraceManager;

public class CustomMappingExceptionResolver extends SimpleMappingExceptionResolver {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(CustomMappingExceptionResolver.class);

	@Autowired
	ErrorInfo errorInfo;
	
	public CustomMappingExceptionResolver() {
			    
	}
	
	@Override
	public String buildLogMessage(Exception e, HttpServletRequest req) {
		return "MVC exception: " + e.getLocalizedMessage();
	}
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
	    HttpServletResponse response, Object handler, Exception e) {

		ModelAndView mav = super.doResolveException(request, response, handler, e);
		
		errorInfo.setError("Error inesperado");
		errorInfo.setMessageForUser(e.getMessage());
		errorInfo.setNameClass(e.getClass().toString());
		errorInfo.setStackTrace(StackTraceManager.getStackTrace(e));
		errorInfo.setUri(request.getRequestURI());
	      
	    mav.addObject("errorInfo", errorInfo);
		mav.addObject("url", request.getRequestURL());
	    
	    return mav;
	}
}
