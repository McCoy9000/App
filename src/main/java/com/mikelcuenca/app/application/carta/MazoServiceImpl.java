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
public class MazoServiceImpl implements MazoService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MazoServiceImpl.class);

	@Autowired
	MazoRepository mazoRepository;
	
	@Autowired
	Messages messages;
	
	ExampleMatcher matcher = ExampleMatcher.matching().withIncludeNullValues();
	
	@Override
	public List<Mazo> findAll() {
		return mazoRepository.findAll();
	}
	
	@Override
	public Page<Mazo> findAll(Pageable pageable) {
		return mazoRepository.findAll(pageable);
	}

	@Override
	public List<Mazo> findAll(Mazo mazo) {
		Example<Mazo> example = Example.of(mazo, matcher);		
		return mazoRepository.findAll(example);
	}

	@Override
	public Page<Mazo> findAll(Mazo mazo, Pageable pageable) {
		if (mazo == null) {
			return mazoRepository.findAll(pageable);
		}
		Example<Mazo> example = Example.of(mazo, matcher);		
		return mazoRepository.findAll(example, pageable);
	}

	@Override
	public Mazo add(Mazo mazo) {
		return mazoRepository.saveAndFlush(mazo);
	}
	
	@Override
	public Mazo update(Mazo mazo) {
		Optional<Mazo> target;
		try {
			target = mazoRepository.findById(mazo.getMazoId());
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("mazo.error.actualizar.inexistente"), e);
		}
		if (target.isPresent()) {
			return mazoRepository.saveAndFlush(mazo);
		}
		throw new GenericException(messages.get("mazo.error.actualizar.inexistente"), new Throwable());
	}
	
	@Override
	public Mazo delete(Mazo mazo) {
		return this.delete(mazo.getMazoId());
	}
	
	@Override 
	public Mazo delete(BigInteger id) {
		Optional<Mazo> target; 
		try {
			target = mazoRepository.findById(id); 
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("mazo.error.actualizar.inexistente"), e);
		}
		Mazo mazoBorrado;
		if (target.isPresent()) {
			mazoBorrado = target.get();
			mazoRepository.delete(mazoBorrado);
			return target.get();
		} 
		throw new GenericException(messages.get("mazo.error.actualizar.inexistente"), new Throwable());
	}
}
