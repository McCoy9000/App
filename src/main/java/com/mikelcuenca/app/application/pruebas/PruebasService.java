package com.mikelcuenca.app.application.pruebas;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mikelcuenca.app.application.usuario.UsuarioRepository;


@Service
public class PruebasService {

	private static final Logger logger = LoggerFactory.getLogger(PruebasService.class);

	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	public void hacerPruebas() throws Exception {
		
		int first = (int)usuarioRepository.findFirst();
		logger.info(String.valueOf(first));
		throw new FileNotFoundException();
		
	}

}
