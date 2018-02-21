package com.mikelcuenca.app.application.imagen;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mikelcuenca.app.infastructure._exception.model.GenericException;
import com.mikelcuenca.app.infastructure.utils.Messages;

@Service
public class ImagenServiceImpl implements ImagenService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ImagenServiceImpl.class);

	@Autowired
	ImagenRepository imagenRepository;
	
	@Autowired
	Messages messages;
	
	ExampleMatcher matcher = ExampleMatcher.matching().withIncludeNullValues();
	
	@Override
	public List<Imagen> findAll() {
		return imagenRepository.findAll();
	}
	
	@Override
	public Page<Imagen> findAll(Pageable pageable) {
		return imagenRepository.findAll(pageable);
	}

	@Override
	public List<Imagen> findAll(Imagen imagen) {
		Example<Imagen> example = Example.of(imagen, matcher);		
		return imagenRepository.findAll(example);
	}

	@Override
	public Page<Imagen> findAll(Imagen imagen, Pageable pageable) {
		if (imagen == null) {
			return imagenRepository.findAll(pageable);
		}
		Example<Imagen> example = Example.of(imagen, matcher);		
		return imagenRepository.findAll(example, pageable);
	}

	@Override
	public Imagen add(Imagen imagen) {
		return imagenRepository.saveAndFlush(imagen);
	}
	
	@Override
	public Imagen update(Imagen imagen) {
		Optional<Imagen> target;
		try {
			target = imagenRepository.findById(imagen.getImagenId());
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("imagen.error.actualizar.inexistente"), e);
		}
		if (target.isPresent()) {
			return imagenRepository.saveAndFlush(imagen);
		}
		throw new GenericException(messages.get("imagen.error.actualizar.inexistente"), new Throwable());
	}
	
	@Override
	public Imagen delete(Imagen imagen) {
		return this.delete(imagen.getImagenId());
	}
	
	@Override 
	public Imagen delete(BigInteger id) {
		Optional<Imagen> target; 
		try {
			target = imagenRepository.findById(id); 
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("imagen.error.actualizar.inexistente"), e);
		}
		Imagen imagenBorrado;
		if (target.isPresent()) {
			imagenBorrado = target.get();
			imagenRepository.delete(imagenBorrado);
			return target.get();
		} 
		throw new GenericException(messages.get("imagen.error.actualizar.inexistente"), new Throwable());
	}
}
