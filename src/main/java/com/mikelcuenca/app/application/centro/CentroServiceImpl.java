package com.mikelcuenca.app.application.centro;

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
public class CentroServiceImpl implements CentroService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CentroServiceImpl.class);

	@Autowired
	CentroRepository centroRepository;
	
	@Autowired
	Messages messages;
	
	ExampleMatcher matcher = ExampleMatcher.matching().withIncludeNullValues();
	
	@Override
	public List<Centro> findAll() {
		return centroRepository.findAll();
	}
	
	@Override
	public Page<Centro> findAll(Pageable pageable) {
		return centroRepository.findAll(pageable);
	}

	@Override
	public List<Centro> findAll(Centro centro) {
		Example<Centro> example = Example.of(centro, matcher);		
		return centroRepository.findAll(example);
	}

	@Override
	public Page<Centro> findAll(Centro centro, Pageable pageable) {
		if (centro == null) {
			return centroRepository.findAll(pageable);
		}
		Example<Centro> example = Example.of(centro, matcher);		
		return centroRepository.findAll(example, pageable);
	}

	@Override
	public Centro add(Centro centro) {
		return centroRepository.saveAndFlush(centro);
	}
	
	@Override
	public Centro update(Centro centro) {
		Optional<Centro> target;
		try {
			target = centroRepository.findById(centro.getCentroId());
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("centro.error.actualizar.inexistente"), e);
		}
		if (target.isPresent()) {
			return centroRepository.saveAndFlush(centro);
		}
		throw new GenericException(messages.get("centro.error.actualizar.inexistente"), new Throwable());
	}
	
	@Override
	public Centro delete(Centro centro) {
		return this.delete(centro.getCentroId());
	}
	
	@Override 
	public Centro delete(BigInteger id) {
		Optional<Centro> target; 
		try {
			target = centroRepository.findById(id); 
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("centro.error.actualizar.inexistente"), e);
		}
		Centro centroBorrado;
		if (target.isPresent()) {
			centroBorrado = target.get();
			centroRepository.delete(centroBorrado);
			return target.get();
		} 
		throw new GenericException(messages.get("centro.error.actualizar.inexistente"), new Throwable());
	}
}
