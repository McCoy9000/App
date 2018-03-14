package com.mikelcuenca.app.application.usuario;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PermissionService {

	public List<Permission> findAll();
	
	public List<Permission> findAll(Permission example);
	
	public Page<Permission> findAll(Pageable pageable);
	
	public Page<Permission> findAll(Permission example, Pageable pageable);

	public Permission add(Permission permission);

	public Permission get(Permission permission);
	
	public Permission update(Permission permission);

	public Permission delete(Permission permission);
	
	public Permission delete(BigInteger id);
}
