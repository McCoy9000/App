package com.mikelcuenca.app.persistence.application.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mikelcuenca.app._model.application.usuario.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role getByRolename(String rolename);
}
