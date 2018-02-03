package com.mikelcuenca.app.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mikelcuenca.app.service.PruebasService;

@Controller
public class PruebasController {

	@Autowired
	PruebasService pruebasService;
	
	@RequestMapping(value = "/pruebas", method=RequestMethod.GET)
	public void correrPruebas() throws Exception {
		try {
			pruebasService.hacerPruebas();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error en las pruebasService", e);
		}
	}
}
