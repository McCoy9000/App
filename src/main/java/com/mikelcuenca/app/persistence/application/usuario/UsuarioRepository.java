package com.mikelcuenca.app.persistence.application.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mikelcuenca.app._model.application.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Usuario getByUsername(String username);
	
	public int findFirst();
}
