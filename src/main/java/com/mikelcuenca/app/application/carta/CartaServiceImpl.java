package com.mikelcuenca.app.application.carta;

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
public class CartaServiceImpl implements CartaService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CartaServiceImpl.class);

	@Autowired
	CartaRepository cartaRepository;
	
	@Autowired
	Messages messages;
	
	ExampleMatcher matcher = ExampleMatcher.matching().withIncludeNullValues();
	
	@Override
	public List<Carta> findAll() {
		return cartaRepository.findAll();
	}
	
	@Override
	public Page<Carta> findAll(Pageable pageable) {
		return cartaRepository.findAll(pageable);
	}

	@Override
	public List<Carta> findAll(Carta carta) {
		Example<Carta> example = Example.of(carta, matcher);		
		return cartaRepository.findAll(example);
	}

	@Override
	public Page<Carta> findAll(Carta carta, Pageable pageable) {
		if (carta == null) {
			return cartaRepository.findAll(pageable);
		}
		Example<Carta> example = Example.of(carta, matcher);		
		return cartaRepository.findAll(example, pageable);
	}

	@Override
	public Carta add(Carta carta) {
		return cartaRepository.saveAndFlush(carta);
	}
	
	@Override
	public Carta update(Carta carta) {
		Optional<Carta> target;
		try {
			target = cartaRepository.findById(carta.getCartaId());
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("carta.error.actualizar.inexistente"), e);
		}
		if (target.isPresent()) {
			return cartaRepository.saveAndFlush(carta);
		}
		throw new GenericException(messages.get("carta.error.actualizar.inexistente"), new Throwable());
	}
	
	@Override
	public Carta delete(Carta carta) {
		return this.delete(carta.getCartaId());
	}
	
	@Override 
	public Carta delete(BigInteger id) {
		Optional<Carta> target; 
		try {
			target = cartaRepository.findById(id); 
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("carta.error.actualizar.inexistente"), e);
		}
		Carta cartaBorrado;
		if (target.isPresent()) {
			cartaBorrado = target.get();
			cartaRepository.delete(cartaBorrado);
			return target.get();
		} 
		throw new GenericException(messages.get("carta.error.actualizar.inexistente"), new Throwable());
	}
}
