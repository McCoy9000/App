package com.mikelcuenca.app.application.usuario;

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
public class UsuarioServiceImpl implements UsuarioService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	Messages messages;
	
	ExampleMatcher matcher = ExampleMatcher.matching().withIncludeNullValues();
	
	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	@Override
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

	@Override
	public List<Usuario> findAll(Usuario usuario) {
		Example<Usuario> example = Example.of(usuario, matcher);		
		return usuarioRepository.findAll(example);
	}

	@Override
	public Page<Usuario> findAll(Usuario usuario, Pageable pageable) {
		if (usuario == null) {
			return usuarioRepository.findAll(pageable);
		}
		Example<Usuario> example = Example.of(usuario, matcher);		
		return usuarioRepository.findAll(example, pageable);
	}

	@Override
	public Usuario add(Usuario usuario) {
		return usuarioRepository.saveAndFlush(usuario);
	}
	
	@Override
	public Usuario update(Usuario usuario) {
		Optional<Usuario> target;
		try {
			target = usuarioRepository.findById(usuario.getUsuarioId());
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("usuario.error.actualizar.inexistente"), e);
		}
		if (target.isPresent()) {
			return usuarioRepository.saveAndFlush(usuario);
		}
		throw new GenericException(messages.get("usuario.error.actualizar.inexistente"), new Throwable());
	}
	
	@Override
	public Usuario delete(Usuario usuario) {
		return this.delete(usuario.getUsuarioId());
	}
	
	@Override 
	public Usuario delete(BigInteger id) {
		Optional<Usuario> target; 
		try {
			target = usuarioRepository.findById(id); 
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("usuario.error.actualizar.inexistente"), e);
		}
		Usuario usuarioBorrado;
		if (target.isPresent()) {
			usuarioBorrado = target.get();
			usuarioRepository.delete(usuarioBorrado);
			return target.get();
		} 
		throw new GenericException(messages.get("usuario.error.actualizar.inexistente"), new Throwable());
	}
}
