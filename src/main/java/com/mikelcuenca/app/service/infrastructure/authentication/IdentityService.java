package com.mikelcuenca.app.service.infrastructure.authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mikelcuenca.app._model.application.usuario.Permission;
import com.mikelcuenca.app._model.application.usuario.Role;
import com.mikelcuenca.app._model.application.usuario.Usuario;
import com.mikelcuenca.app._model.infrastructure.authentication.Identity;
import com.mikelcuenca.app._model.infrastructure.authentication.Privilege;
import com.mikelcuenca.app.persistence.application.usuario.PermissionRepository;
import com.mikelcuenca.app.persistence.application.usuario.RoleRepository;
import com.mikelcuenca.app.persistence.application.usuario.UsuarioRepository;

@Service
public class IdentityService implements UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PermissionRepository privilegeRepository;
	
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = new Usuario();
		try {
			usuario = usuarioRepository.getByUsername(username);
		} catch (Exception e) {
			throw new UsernameNotFoundException("Error de repositorio al recuperar por username");
		}
		User user = this.buildAnonymousIdentity();
		if(usuario.getUsername() != null || usuario.getUsername() != "" 
				|| usuario.getRoles() != null || usuario.getRoles().size() > 0) {
			user = this.mapUsuarioToUser(usuario);
		}
		return user;
	}
	
	public User buildAnonymousIdentity() {
		User user = new Identity();
		return user;
	}
	
	private User mapUsuarioToUser (Usuario usuario) {
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
				priv.setName(permission.getPermissionname());
				authorities.add(priv);
			}
		}
		User user = new User(username, password, authorities);
		return user;
	}
}
