package com.mikelcuenca.app.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mikelcuenca.app.service.infrastructure.authentication.AuthenticationHolder;

@Controller
public class HomeController {
	
	@Autowired
	AuthenticationHolder authenticationHolder;
	
	@RequestMapping(value = "/")
	public String index() {
		return "home";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = authenticationHolder.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request,response, auth);
		}
		return "redirect:/login?logout";
	}
}
