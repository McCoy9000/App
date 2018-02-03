package com.mikelcuenca.app.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mikelcuenca.app.service.infrastructure.authentication.AuthenticationHolder;

@Controller
@RequestMapping(value = "/home")
public class HomeController {
	
	@Autowired
	AuthenticationHolder authenticationHolder;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index() {
		return "home";
	}
}
