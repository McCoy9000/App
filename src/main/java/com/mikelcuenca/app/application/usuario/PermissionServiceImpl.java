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
public class PermissionServiceImpl implements PermissionService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

	@Autowired
	PermissionRepository permissionRepository;
	
	@Autowired
	Messages messages;
	
	ExampleMatcher matcher = ExampleMatcher.matching().withIncludeNullValues();
	
	@Override
	public List<Permission> findAll() {
		return permissionRepository.findAll();
	}
	
	@Override
	public Page<Permission> findAll(Pageable pageable) {
		return permissionRepository.findAll(pageable);
	}

	@Override
	public List<Permission> findAll(Permission permission) {
		Example<Permission> example = Example.of(permission, matcher);		
		return permissionRepository.findAll(example);
	}

	@Override
	public Page<Permission> findAll(Permission permission, Pageable pageable) {
		if (permission == null) {
			return permissionRepository.findAll(pageable);
		}
		Example<Permission> example = Example.of(permission, matcher);		
		return permissionRepository.findAll(example, pageable);
	}

	@Override
	public Permission add(Permission permission) {
		return permissionRepository.saveAndFlush(permission);
	}
	
	@Override
	public Permission update(Permission permission) {
		Optional<Permission> target;
		try {
			target = permissionRepository.findById(permission.getPermissionId());
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("permission.error.actualizar.inexistente"), e);
		}
		if (target.isPresent()) {
			return permissionRepository.saveAndFlush(permission);
		}
		throw new GenericException(messages.get("permission.error.actualizar.inexistente"), new Throwable());
	}
	
	@Override
	public Permission delete(Permission permission) {
		return this.delete(permission.getPermissionId());
	}
	
	@Override 
	public Permission delete(BigInteger id) {
		Optional<Permission> target; 
		try {
			target = permissionRepository.findById(id); 
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("permission.error.actualizar.inexistente"), e);
		}
		Permission permissionBorrado;
		if (target.isPresent()) {
			permissionBorrado = target.get();
			permissionRepository.delete(permissionBorrado);
			return target.get();
		} 
		throw new GenericException(messages.get("permission.error.actualizar.inexistente"), new Throwable());
	}
}
