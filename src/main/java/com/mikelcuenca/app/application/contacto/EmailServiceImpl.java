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
public class EmailServiceImpl implements EmailService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Autowired
	EmailRepository emailRepository;
	
	@Autowired
	Messages messages;
	
	ExampleMatcher matcher = ExampleMatcher.matching().withIncludeNullValues();
	
	@Override
	public List<Email> findAll() {
		return emailRepository.findAll();
	}
	
	@Override
	public Page<Email> findAll(Pageable pageable) {
		return emailRepository.findAll(pageable);
	}

	@Override
	public List<Email> findAll(Email email) {
		Example<Email> example = Example.of(email, matcher);		
		return emailRepository.findAll(example);
	}

	@Override
	public Page<Email> findAll(Email email, Pageable pageable) {
		if (email == null) {
			return emailRepository.findAll(pageable);
		}
		Example<Email> example = Example.of(email, matcher);		
		return emailRepository.findAll(example, pageable);
	}

	@Override
	public Email add(Email email) {
		return emailRepository.saveAndFlush(email);
	}
	
	@Override
	public Email update(Email email) {
		Optional<Email> target;
		try {
			target = emailRepository.findById(email.getEmailId());
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("email.error.actualizar.inexistente"), e);
		}
		if (target.isPresent()) {
			return emailRepository.saveAndFlush(email);
		}
		throw new GenericException(messages.get("email.error.actualizar.inexistente"), new Throwable());
	}
	
	@Override
	public Email delete(Email email) {
		return this.delete(email.getEmailId());
	}
	
	@Override 
	public Email delete(BigInteger id) {
		Optional<Email> target; 
		try {
			target = emailRepository.findById(id); 
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("email.error.actualizar.inexistente"), e);
		}
		Email emailBorrado;
		if (target.isPresent()) {
			emailBorrado = target.get();
			emailRepository.delete(emailBorrado);
			return target.get();
		} 
		throw new GenericException(messages.get("email.error.actualizar.inexistente"), new Throwable());
	}
}
