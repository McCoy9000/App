package com.mikelcuenca.app.application.pruebas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mikelcuenca.app.infastructure._exception.model.GenericException;

@Controller
public class PruebasController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PruebasController.class);

	@Autowired
	PruebasService pruebasService;
	
	@RequestMapping(value = "/pruebas", method=RequestMethod.GET)
	public String correrPruebas() throws Exception {
		throw new GenericException("Mensaje en la excepción desde el controlador", new Throwable());
	}
	
	@RequestMapping(value = "/recursoRestringido", method=RequestMethod.GET)
	public String restringirAcceso() {
		return "recursoRestringido";
	}
	
	@RequestMapping(value = "/recursoProhibido", method=RequestMethod.GET)
	public String prohibirAcceso() {
		return "recursoProhibido";
	}
}
