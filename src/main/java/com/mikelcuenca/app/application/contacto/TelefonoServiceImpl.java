package com.mikelcuenca.app.application.contacto;

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
public class TelefonoServiceImpl implements TelefonoService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(TelefonoServiceImpl.class);

	@Autowired
	TelefonoRepository telefonoRepository;
	
	@Autowired
	Messages messages;
	
	ExampleMatcher matcher = ExampleMatcher.matching().withIncludeNullValues();
	
	@Override
	public List<Telefono> findAll() {
		return telefonoRepository.findAll();
	}
	
	@Override
	public Page<Telefono> findAll(Pageable pageable) {
		return telefonoRepository.findAll(pageable);
	}

	@Override
	public List<Telefono> findAll(Telefono telefono) {
		Example<Telefono> example = Example.of(telefono, matcher);		
		return telefonoRepository.findAll(example);
	}

	@Override
	public Page<Telefono> findAll(Telefono telefono, Pageable pageable) {
		if (telefono == null) {
			return telefonoRepository.findAll(pageable);
		}
		Example<Telefono> example = Example.of(telefono, matcher);		
		return telefonoRepository.findAll(example, pageable);
	}

	@Override
	public Telefono add(Telefono telefono) {
		return telefonoRepository.saveAndFlush(telefono);
	}
	
	@Override
	public Telefono update(Telefono telefono) {
		Optional<Telefono> target;
		try {
			target = telefonoRepository.findById(telefono.getTelefonoId());
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("telefono.error.actualizar.inexistente"), e);
		}
		if (target.isPresent()) {
			return telefonoRepository.saveAndFlush(telefono);
		}
		throw new GenericException(messages.get("telefono.error.actualizar.inexistente"), new Throwable());
	}
	
	@Override
	public Telefono delete(Telefono telefono) {
		return this.delete(telefono.getTelefonoId());
	}
	
	@Override 
	public Telefono delete(BigInteger id) {
		Optional<Telefono> target; 
		try {
			target = telefonoRepository.findById(id); 
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("telefono.error.actualizar.inexistente"), e);
		}
		Telefono telefonoBorrado;
		if (target.isPresent()) {
			telefonoBorrado = target.get();
			telefonoRepository.delete(telefonoBorrado);
			return target.get();
		} 
		throw new GenericException(messages.get("telefono.error.actualizar.inexistente"), new Throwable());
	}
}
