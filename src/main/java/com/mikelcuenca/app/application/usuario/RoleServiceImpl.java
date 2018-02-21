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
public class RoleServiceImpl implements RoleService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	Messages messages;
	
	ExampleMatcher matcher = ExampleMatcher.matching().withIncludeNullValues();
	
	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}
	
	@Override
	public Page<Role> findAll(Pageable pageable) {
		return roleRepository.findAll(pageable);
	}

	@Override
	public List<Role> findAll(Role role) {
		Example<Role> example = Example.of(role, matcher);		
		return roleRepository.findAll(example);
	}

	@Override
	public Page<Role> findAll(Role role, Pageable pageable) {
		if (role == null) {
			return roleRepository.findAll(pageable);
		}
		Example<Role> example = Example.of(role, matcher);		
		return roleRepository.findAll(example, pageable);
	}

	@Override
	public Role add(Role role) {
		return roleRepository.saveAndFlush(role);
	}
	
	@Override
	public Role update(Role role) {
		Optional<Role> target;
		try {
			target = roleRepository.findById(role.getRoleId());
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("role.error.actualizar.inexistente"), e);
		}
		if (target.isPresent()) {
			return roleRepository.saveAndFlush(role);
		}
		throw new GenericException(messages.get("role.error.actualizar.inexistente"), new Throwable());
	}
	
	@Override
	public Role delete(Role role) {
		return this.delete(role.getRoleId());
	}
	
	@Override 
	public Role delete(BigInteger id) {
		Optional<Role> target; 
		try {
			target = roleRepository.findById(id); 
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("role.error.actualizar.inexistente"), e);
		}
		Role roleBorrado;
		if (target.isPresent()) {
			roleBorrado = target.get();
			roleRepository.delete(roleBorrado);
			return target.get();
		} 
		throw new GenericException(messages.get("role.error.actualizar.inexistente"), new Throwable());
	}
}
