package com.mikelcuenca.app.persistence.application.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mikelcuenca.app._model.application.usuario.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

	public Permission getByPermissionname(String permissionname);
}
