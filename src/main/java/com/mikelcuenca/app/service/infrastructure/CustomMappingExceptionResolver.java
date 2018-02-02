package com.mikelcuenca.app.service.infrastructure;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class CustomMappingExceptionResolver extends SimpleMappingExceptionResolver {

	public CustomMappingExceptionResolver() {
		// Enable logging by providing the name of the logger to use
	    setWarnLogCategory(CustomMappingExceptionResolver.class.getName());
	}
	
	@Override
	public String buildLogMessage(Exception e, HttpServletRequest req) {
		return "MVC exception: " + e.getLocalizedMessage();
	}
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
	    HttpServletResponse response, Object handler, Exception e) {
	    // Call super method to get the ModelAndView
	    ModelAndView mav = super.doResolveException(request, response, handler, e);
	        
	    // Make the full URL available to the view - note ModelAndView uses
	    // addObject() but Model uses addAttribute(). They work the same. 
	    mav.addObject("url", request.getRequestURL());
	    return mav;
	}
}
