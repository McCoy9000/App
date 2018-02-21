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
public class BarajaServiceImpl implements BarajaService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BarajaServiceImpl.class);

	@Autowired
	BarajaRepository barajaRepository;
	
	@Autowired
	Messages messages;
	
	ExampleMatcher matcher = ExampleMatcher.matching().withIncludeNullValues();
	
	@Override
	public List<Baraja> findAll() {
		return barajaRepository.findAll();
	}
	
	@Override
	public Page<Baraja> findAll(Pageable pageable) {
		return barajaRepository.findAll(pageable);
	}

	@Override
	public List<Baraja> findAll(Baraja baraja) {
		Example<Baraja> example = Example.of(baraja, matcher);		
		return barajaRepository.findAll(example);
	}

	@Override
	public Page<Baraja> findAll(Baraja baraja, Pageable pageable) {
		if (baraja == null) {
			return barajaRepository.findAll(pageable);
		}
		Example<Baraja> example = Example.of(baraja, matcher);		
		return barajaRepository.findAll(example, pageable);
	}

	@Override
	public Baraja add(Baraja baraja) {
		return barajaRepository.saveAndFlush(baraja);
	}
	
	@Override
	public Baraja update(Baraja baraja) {
		Optional<Baraja> target;
		try {
			target = barajaRepository.findById(baraja.getBarajaId());
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("baraja.error.actualizar.inexistente"), e);
		}
		if (target.isPresent()) {
			return barajaRepository.saveAndFlush(baraja);
		}
		throw new GenericException(messages.get("baraja.error.actualizar.inexistente"), new Throwable());
	}
	
	@Override
	public Baraja delete(Baraja baraja) {
		return this.delete(baraja.getBarajaId());
	}
	
	@Override 
	public Baraja delete(BigInteger id) {
		Optional<Baraja> target; 
		try {
			target = barajaRepository.findById(id); 
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("baraja.error.actualizar.inexistente"), e);
		}
		Baraja barajaBorrada;
		if (target.isPresent()) {
			barajaBorrada = target.get();
			barajaRepository.delete(barajaBorrada);
			return target.get();
		} 
		throw new GenericException(messages.get("baraja.error.actualizar.inexistente"), new Throwable());
	}
}
