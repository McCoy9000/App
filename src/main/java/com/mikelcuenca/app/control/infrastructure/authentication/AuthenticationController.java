package com.mikelcuenca.app.control.infrastructure.authentication;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mikelcuenca.app.service.infrastructure.authentication.AuthenticationHolder;
import com.mikelcuenca.app.utils.Messages;

@Controller
public class AuthenticationController {

	@Autowired
	AuthenticationHolder authenticationHolder;
	
	@Autowired
	Messages messages;

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	private final String LOGIN_VIEW = "login";
	
	@RequestMapping(value= {"/login"}, method=RequestMethod.GET)
	public String authenticate(Principal principal, Model model) {
		logger.info(messages.get("login.log.login"));
		
		return LOGIN_VIEW;

	}

	/**
	 * Muestra la pargina de acceso denegado cuando el usuario no tiene permisos para ejecutar una accion.
	 * @return pagina de acceso denegado
	 */
	@RequestMapping(value = "/accessDenied", method=RequestMethod.GET)
	public ModelAndView denied(ModelMap model, HttpServletRequest request) {

			logger.info(messages.get("login.log.accessDenied1") + 
					authenticationHolder.getAuthentication().getName() +
					" " + messages.get("login.log.accessDenied2") +
					request.getRequestURI());
			
			return new ModelAndView("accessDenied");
	}
	
	/**
	 * La session caducada redirecciona a /login
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/expiredSession", method=RequestMethod.GET)
	public ModelAndView expiredSession (
		Model model,RedirectAttributes redirectAttributes,HttpServletRequest request, HttpSession sesion) {
		logger.info(messages.get("login.log.expiredSession") + authenticationHolder.getAuthentication().getName());
		
		if (request.getHeader("Referer")!=null) 
			redirectAttributes.addFlashAttribute("expiredSession", true);
		return new ModelAndView("redirect:/login");
	}	
}
