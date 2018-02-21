package com.mikelcuenca.app.application.partida;

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
public class PartidaServiceImpl implements PartidaService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PartidaServiceImpl.class);

	@Autowired
	PartidaRepository partidaRepository;
	
	@Autowired
	Messages messages;
	
	ExampleMatcher matcher = ExampleMatcher.matching().withIncludeNullValues();
	
	@Override
	public List<Partida> findAll() {
		return partidaRepository.findAll();
	}
	
	@Override
	public Page<Partida> findAll(Pageable pageable) {
		return partidaRepository.findAll(pageable);
	}

	@Override
	public List<Partida> findAll(Partida partida) {
		Example<Partida> example = Example.of(partida, matcher);		
		return partidaRepository.findAll(example);
	}

	@Override
	public Page<Partida> findAll(Partida partida, Pageable pageable) {
		if (partida == null) {
			return partidaRepository.findAll(pageable);
		}
		Example<Partida> example = Example.of(partida, matcher);		
		return partidaRepository.findAll(example, pageable);
	}

	@Override
	public Partida add(Partida partida) {
		return partidaRepository.saveAndFlush(partida);
	}
	
	@Override
	public Partida update(Partida partida) {
		Optional<Partida> target;
		try {
			target = partidaRepository.findById(partida.getPartidaId());
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("partida.error.actualizar.inexistente"), e);
		}
		if (target.isPresent()) {
			return partidaRepository.saveAndFlush(partida);
		}
		throw new GenericException(messages.get("partida.error.actualizar.inexistente"), new Throwable());
	}
	
	@Override
	public Partida delete(Partida partida) {
		return this.delete(partida.getPartidaId());
	}
	
	@Override 
	public Partida delete(BigInteger id) {
		Optional<Partida> target; 
		try {
			target = partidaRepository.findById(id); 
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("partida.error.actualizar.inexistente"), e);
		}
		Partida partidaBorrado;
		if (target.isPresent()) {
			partidaBorrado = target.get();
			partidaRepository.delete(partidaBorrado);
			return target.get();
		} 
		throw new GenericException(messages.get("partida.error.actualizar.inexistente"), new Throwable());
	}
}
