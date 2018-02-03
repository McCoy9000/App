package com.mikelcuenca.app.control.infrastructure.authentication;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mikelcuenca.app.utilidades.Messages;

@Controller
public class AuthenticationController {

	@Autowired
	Messages messages;

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	private final String LOGIN_VIEW = "login";
	
	@RequestMapping(value= {"/","/login"}, method=RequestMethod.GET)
	public String authenticate(Principal principal, Model model) {
		logger.info(messages.get("login.log.login"));
		
		return LOGIN_VIEW;

	}
	
}
