package com.mikelcuenca.app.service.infrastructure.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import com.mikelcuenca.app.utils.Messages;

public class CustomAccessDeniedHandler extends AccessDeniedHandlerImpl {

	public static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

	@Autowired
	AuthenticationHolderImpl authenticationHolder;
	
	@Autowired
	Messages messages;
	
	@Override
	public void handle(
		HttpServletRequest request,
		HttpServletResponse response, 
		AccessDeniedException exc) throws IOException, ServletException {
	    
		String referer = request.getHeader("Referer");
		
		/* Determinamos cual es la url anterior. 
		 * Si viene de login (/login) quiere decir
		 * que el usuario se ha autenticado pero no tiene acceso a la página
		 * solicitada previamente.
		 * Direccionamos a un nuevo requestmapping 
		 */

		if ( referer != null && referer.contains("/login")){
			request.setAttribute("accessDenied",true);
			request.getRequestDispatcher("/login").forward(request, response);		
			return;
		}
		
		response.sendRedirect("/accessDenied");
	}
}
