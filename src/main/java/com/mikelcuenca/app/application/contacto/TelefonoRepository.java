package com.mikelcuenca.app.application.contacto;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TelefonoRepository extends JpaRepository<Telefono, BigInteger> {

	public Telefono getByTelefonoNumero(String telefonoNumero);

	@Query("SELECT 1 FROM Telefono WHERE rownum = 1")
	public Object findFirst();
}
