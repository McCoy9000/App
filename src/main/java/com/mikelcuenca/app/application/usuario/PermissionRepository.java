package com.mikelcuenca.app.application.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

	public Permission getByPermissionName(String permissionName);

	@Query ("SELECT 1 FROM Permission WHERE rownum=1")
	public Object findFirst();
}
