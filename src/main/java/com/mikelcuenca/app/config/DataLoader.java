package com.mikelcuenca.app.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mikelcuenca.app._model.application.usuario.Permission;
import com.mikelcuenca.app._model.application.usuario.Role;
import com.mikelcuenca.app._model.application.usuario.Usuario;
import com.mikelcuenca.app.persistence.application.usuario.PermissionRepository;
import com.mikelcuenca.app.persistence.application.usuario.RoleRepository;
import com.mikelcuenca.app.persistence.application.usuario.UsuarioRepository;

//@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PermissionRepository permissionRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		int first = usuarioRepository.findFirst();
		
		if(first == 0) {
			Permission permission = new Permission();
			permission.setPermissionname("ROOT_PERMISSION");
			permission.setDescription("All access");
			permissionRepository.save(permission);
			
			Set<Permission> permissions = new HashSet<Permission>();
			permissions.add(permission);
			
			Role role = new Role();
			role.setRolename("ROLE_ROOT");
			role.setDescription("root role with total access");
			role.setPermissions(permissions);
			roleRepository.save(role);
			
			Set<Role> roles = new HashSet<Role>();
			roles.add(role);
	
			Usuario usuario = new Usuario();
			usuario.setUsername("root");
			usuario.setPassword(encoder.encode("root"));
			usuario.setDescripcion("root user");
			usuario.setRoles(roles);
			usuarioRepository.save(usuario);
		}
	}

}
