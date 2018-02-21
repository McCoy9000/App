package com.mikelcuenca.app.application.contacto;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mikelcuenca.app.application.imagen.Imagen;

@RepositoryRestResource
public interface EmailRepository extends JpaRepository<Email, BigInteger> {

	public Imagen getByEmailAddress(String emailAddress);

	@Query("SELECT 1 FROM Email WHERE rownum = 1")
	public Object findFirst();
}
