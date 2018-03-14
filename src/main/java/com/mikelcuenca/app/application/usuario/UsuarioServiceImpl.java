package com.mikelcuenca.app.application.usuario;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
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
	RoleService roleService;
	
	@Autowired
	PermissionService permissionService;
	
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
	
	public void grantAuthority(Usuario usuario, GrantedAuthority authority) {
		String authname = authority.getAuthority();
		
		if(authname == null) {
			throw new GenericException("error.usuario.complexAuthority", new Throwable());
		}
		
		if(authname.startsWith("ROLE_")) {
			Role rol = Role.of(authname);
			try {
				rol = roleService.get(rol);
			} catch (IncorrectResultSizeDataAccessException e) {
			throw new GenericException("error.usuario.grantAuthority", e);
			} catch (EntityNotFoundException e) {
				throw new GenericException("error.usuario.grantAuthority", e);
			}
			usuario.getRoles().add(rol);
			this.update(usuario);
		} else {
			Permission permission = Permission.of(authname);
			try {
			permission = permissionService.get(permission);
			} catch (GenericException e) {
				throw new GenericException("error.usuario.grantAuthority", e);
			}
			Role rol = Role.of("ROLE_" + usuario.getUsername());
			try {
				rol = roleService.get(rol);
			} catch (GenericException e) {
				throw new GenericException("error.usuario.grantAuthority", e);
			}
			Set<Permission> permissions = new HashSet<Permission>();
			permissions.add(permission);
			rol.setPermissions(permissions);
			roleService.add(rol);
			usuario.getRoles().add(Role.of(authname));
			this.update(usuario);
		}
	}
}
