package com.mikelcuenca.app.application.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role getByRolename(String rolename);
	
	@Query ("SELECT 1 FROM Role WHERE rownum=1")
	public Object findFirst();
}