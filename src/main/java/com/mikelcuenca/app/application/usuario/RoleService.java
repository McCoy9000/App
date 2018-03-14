package com.mikelcuenca.app.application.usuario;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {

	public List<Role> findAll();
	            
	public List<Role> findAll(Role example);
	            
	public Page<Role> findAll(Pageable pageable);
	            
	public Page<Role> findAll(Role example, Pageable pageable);
                 
	public Role add(Role role);
           
	public Role get(Role role);
	       
	public Role update(Role role);
           
	public Role delete(Role role);
	       
	public Role delete(BigInteger id);
	
	public void includePermission(Role role, Permission permission);
}
