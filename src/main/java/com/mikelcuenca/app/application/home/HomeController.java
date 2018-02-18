package com.mikelcuenca.app.application.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mikelcuenca.app.infastructure.authentication.service.AuthenticationHolder;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	AuthenticationHolder authenticationHolder;
	
	@RequestMapping(value = {"/", "/home", "/index"}, method=RequestMethod.GET)
	public String index() {
		logger.info("Sirviendo home view");
		return "home";
	}
}