package com.mikelcuenca.app.persistence.application.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mikelcuenca.app._model.application.usuario.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role getByRolename(String rolename);
	
	@Query ("SELECT 1 FROM Role WHERE rownum=1")
	public Object findFirst();
}