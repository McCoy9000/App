package com.mikelcuenca.app.service.infrastructure.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import com.mikelcuenca.app.utilidades.Messages;


@Service
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	public static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);

	@Autowired 
	Messages messages;
	
	
}
