package com.mikelcuenca.app.application.usuario;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;

public interface UsuarioService {

	public List<Usuario> findAll();
	
	public List<Usuario> findAll(Usuario example);
	
	public Page<Usuario> findAll(Pageable pageable);
	
	public Page<Usuario> findAll(Usuario example, Pageable pageable);

	public Usuario add(Usuario usuario);

	public Usuario update(Usuario usuario);

	public Usuario delete(Usuario usuario);
	
	public Usuario delete(BigInteger id);
	
	public void grantAuthority(Usuario usuario, GrantedAuthority authority);
}
