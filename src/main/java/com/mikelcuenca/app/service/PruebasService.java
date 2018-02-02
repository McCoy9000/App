package com.mikelcuenca.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mikelcuenca.app._model.infrastructure._exceptions.GenericException;
import com.mikelcuenca.app.persistence.application.usuario.UsuarioRepository;


@Component
public class PruebasService {

	private static final Logger logger = LoggerFactory.getLogger(PruebasService.class);

	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	public void hacerPruebas() throws Exception {
		
		int first = usuarioRepository.findFirst();
		logger.info(String.valueOf(first));
		throw new GenericException();
		
	}

}
