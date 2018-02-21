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
public class ContactoServiceImpl implements ContactoService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ContactoServiceImpl.class);

	@Autowired
	ContactoRepository contactoRepository;
	
	@Autowired
	Messages messages;
	
	ExampleMatcher matcher = ExampleMatcher.matching().withIncludeNullValues();
	
	@Override
	public List<Contacto> findAll() {
		return contactoRepository.findAll();
	}
	
	@Override
	public Page<Contacto> findAll(Pageable pageable) {
		return contactoRepository.findAll(pageable);
	}

	@Override
	public List<Contacto> findAll(Contacto contacto) {
		Example<Contacto> example = Example.of(contacto, matcher);		
		return contactoRepository.findAll(example);
	}

	@Override
	public Page<Contacto> findAll(Contacto contacto, Pageable pageable) {
		if (contacto == null) {
			return contactoRepository.findAll(pageable);
		}
		Example<Contacto> example = Example.of(contacto, matcher);		
		return contactoRepository.findAll(example, pageable);
	}

	@Override
	public Contacto add(Contacto contacto) {
		return contactoRepository.saveAndFlush(contacto);
	}
	
	@Override
	public Contacto update(Contacto contacto) {
		Optional<Contacto> target;
		try {
			target = contactoRepository.findById(contacto.getContactoId());
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("contacto.error.actualizar.inexistente"), e);
		}
		if (target.isPresent()) {
			return contactoRepository.saveAndFlush(contacto);
		}
		throw new GenericException(messages.get("contacto.error.actualizar.inexistente"), new Throwable());
	}
	
	@Override
	public Contacto delete(Contacto contacto) {
		return this.delete(contacto.getContactoId());
	}
	
	@Override 
	public Contacto delete(BigInteger id) {
		Optional<Contacto> target; 
		try {
			target = contactoRepository.findById(id); 
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("contacto.error.actualizar.inexistente"), e);
		}
		Contacto contactoBorrado;
		if (target.isPresent()) {
			contactoBorrado = target.get();
			contactoRepository.delete(contactoBorrado);
			return target.get();
		} 
		throw new GenericException(messages.get("contacto.error.actualizar.inexistente"), new Throwable());
	}
}
