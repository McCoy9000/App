package com.mikelcuenca.app.service.infrastructure.authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.mikelcuenca.app._model.application.usuario.Permission;
import com.mikelcuenca.app._model.application.usuario.Role;
import com.mikelcuenca.app._model.application.usuario.Usuario;
import com.mikelcuenca.app._model.infrastructure._exceptions.GenericException;
import com.mikelcuenca.app._model.infrastructure.authentication.Identity;
import com.mikelcuenca.app._model.infrastructure.authentication.Privilege;
import com.mikelcuenca.app.control.PruebasController;
import com.mikelcuenca.app.persistence.application.usuario.PermissionRepository;
import com.mikelcuenca.app.persistence.application.usuario.RoleRepository;
import com.mikelcuenca.app.persistence.application.usuario.UsuarioRepository;

@Service
public class IdentityService implements UserDetailsService {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PruebasController.class);

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PermissionRepository privilegeRepository;
	
	@Override
	public Identity loadUserByUsername(String username) {

		Usuario usuario = Usuario.of();
		try {
			usuario = usuarioRepository.getByUsername(username);
		} catch (Exception e) {
			throw new GenericException("authentication.error.usernamenotfound", e);
		}
		Identity user = this.buildAnonymousIdentity();
		if(usuario != null) {
			if(usuario.getUsername() != null && usuario.getUsername() != "" 
					&& usuario.getRoles() != null && usuario.getRoles().size() > 0) {
				user = this.mapUsuarioToIdentity(usuario);
			}
		}
		return user;
	}
	
	public Identity buildAnonymousIdentity() {
		Identity user = new Identity();
		user.setPassword("AnonymousPassword");
		
		Privilege anonymousPrivilege = new Privilege("ANONYMOUS_PRIVILEGE");
		List<Privilege> anonymousAuthorities = new ArrayList<Privilege>();
		anonymousAuthorities.add(anonymousPrivilege);
		user.setAuthorities(anonymousAuthorities);
		
		return user;
		
	}
	
	private Identity mapUsuarioToIdentity (Usuario usuario) {
		if(usuario == null) {
			return this.buildAnonymousIdentity();
		}
		
		String username = usuario.getUsername();
		String password = usuario.getPassword();
		Set<Role> roles = usuario.getRoles();
		List<Privilege> authorities = new ArrayList<Privilege>();

		for (Role role : roles) {
			Privilege privilege = new Privilege();
			privilege.setName(role.getRolename());
			authorities.add(privilege);
			for(Permission permission : role.getPermissions()) {
				Privilege priv = new Privilege();
				priv.setName(permission.getPermissionName());
				authorities.add(priv);
			}
		}
		
		Identity user = new Identity(username, password, authorities);
		return user;
	}
}
