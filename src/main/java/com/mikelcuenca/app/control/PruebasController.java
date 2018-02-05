package com.mikelcuenca.app.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mikelcuenca.app._model.infrastructure._exceptions.GenericException;
import com.mikelcuenca.app.service.PruebasService;

@Controller
public class PruebasController {

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
