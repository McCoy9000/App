package com.mikelcuenca.app.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mikelcuenca.app._model.application.usuario.Permission;
import com.mikelcuenca.app._model.application.usuario.Role;
import com.mikelcuenca.app._model.application.usuario.Usuario;
import com.mikelcuenca.app.persistence.application.usuario.PermissionRepository;
import com.mikelcuenca.app.persistence.application.usuario.RoleRepository;
import com.mikelcuenca.app.persistence.application.usuario.UsuarioRepository;
import com.mikelcuenca.app.utils.Messages;

@Component
public class DataLoader implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PermissionRepository permissionRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	Messages messages;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		List<Permission> permissions = new ArrayList<>();
		List<Role> roles = new ArrayList<>();

		Object first = permissionRepository.findFirst();

		if(first == null) {
			logger.warn(messages.get("startup.bbdd.noPermissions"));
			
			permissions = new ArrayList<Permission>();
			
			String[][] permisos = new String[][]{{messages.get("permissions.root"), messages.get("permissions.root.description")}
												, {messages.get("permissions.admin"), messages.get("permissions.admin.description")}
												, {messages.get("permissions.acess"), messages.get("permissions.access.description")}
												, {messages.get("permissions.search"), messages.get("permissions.search.description")}
												, {messages.get("permissions.read"), messages.get("permissions.read.description")}
												, {messages.get("permissions.write"), messages.get("permissions.write.description")}
												, {messages.get("permissions.update"), messages.get("permissions.update.description")}
												, {messages.get("permissions.delete"), messages.get("permissions.delete.description")}};
				

			for (int i = 0;i<permisos.length;i++) {
				Permission permission = Permission.of();
				permission.setPermissionName(permisos[i][0]);
				permission.setDescription(permisos[i][1]);
				permissions.add(permission);
			}
			permissionRepository.saveAll(permissions);
		}

		first = roleRepository.findFirst();
		
		if(first == null) {
			logger.warn(messages.get("startup.bbdd.noRoles"));
			
			roles = new ArrayList<Role>();
			
			String[][] rols = new String [][] {{messages.get("roles.root"), messages.get("roles.root.description")}
												, {messages.get("roles.admin"), messages.get("roles.admin.description")}
												, {messages.get("roles.user"), messages.get("roles.user.description")}
												, {messages.get("roles.director"), messages.get("roles.director.description")}
												, {messages.get("roles.jugador"), messages.get("roles.jugador.description")}};
			
			
			for (int i = 0;i<rols.length;i++) {
					Role rol = Role.of();
					rol.setRolename(rols[i][0]);
					rol.setDescription(rols[i][1]);
					roles.add(rol);
			}
			
			Set<Permission> perms = new HashSet<Permission>(permissions);
			roles.get(0).setPermissions(perms);
			
			permissions = permissions.subList(1, permissions.size());
			perms = new HashSet<Permission>(permissions);
			roles.get(1).setPermissions(perms);

			permissions = permissions.subList(1, 5);
			perms = new HashSet<Permission>(permissions);
			roles.get(2).setPermissions(perms);
			roles.get(3).setPermissions(perms);
			roles.get(4).setPermissions(perms);			
			
			roleRepository.saveAll(roles);
		}
		
		first = usuarioRepository.findFirst();
		if(first == null) {
			logger.warn(messages.get("startup.bbdd.noUsuarios"));
			
			Usuario usuario = Usuario.of();
			usuario.setUsername("root");
			usuario.setPassword(encoder.encode("root"));
			
			Set<Role> roleSet = new HashSet<Role>();
			roleSet.add(roleRepository.getByRolename("ROLE_ROOT"));
						
			usuario.setRoles(roleSet);
			usuarioRepository.saveAndFlush(usuario);
			
			
			usuario = Usuario.of();
			usuario.setUsername("admin");
			usuario.setPassword(encoder.encode("admin"));
			
			roleSet = new HashSet<Role>();
			roleSet.add(roleRepository.getByRolename("ROLE_ADMIN"));
						
			usuario.setRoles(roleSet);
			usuarioRepository.saveAndFlush(usuario);
			
			
			usuario = Usuario.of();
			usuario.setUsername("user");
			usuario.setPassword(encoder.encode("user"));
			
			roleSet = new HashSet<Role>();
			roleSet.add(roleRepository.getByRolename("ROLE_USER"));
						
			usuario.setRoles(roleSet);
			usuarioRepository.saveAndFlush(usuario);
			
			
			usuario = Usuario.of();
			usuario.setUsername("director");
			usuario.setPassword(encoder.encode("director"));
			
			roleSet = new HashSet<Role>();
			roleSet.add(roleRepository.getByRolename("ROLE_DIRECTOR"));
						
			usuario.setRoles(roleSet);
			usuarioRepository.saveAndFlush(usuario);
			
			
			usuario = Usuario.of();
			usuario.setUsername("jugador");
			usuario.setPassword(encoder.encode("jugador"));
			
			roleSet = new HashSet<Role>();
			roleSet.add(roleRepository.getByRolename("ROLE_JUGADOR"));
						
			usuario.setRoles(roleSet);
			usuarioRepository.saveAndFlush(usuario);
		}
	}
}
