package com.mikelcuenca.app.application.usuario;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UsuarioRepository extends JpaRepository<Usuario, BigInteger> {

	public Usuario getByUsername(String username);

	@Query("SELECT 1 FROM Usuario WHERE rownum = 1")
	public Object findFirst();
}
