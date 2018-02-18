package com.mikelcuenca.app.infastructure.authentication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.mikelcuenca.app.infastructure.utils.Messages;


public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	public static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);

	@Autowired 
	Messages messages;
	
	
}
