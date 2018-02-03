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
@RequestMapping("/login")
public class LoginController {

	@Autowired
	Messages messages;

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(method=RequestMethod.GET)
	public void login(Principal principal, Model model) {
		logger.info(principal.getName() + messages.get("login.log.login"));
		
		
	}
	
}
