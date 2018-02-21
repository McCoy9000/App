package com.mikelcuenca.app.application.contacto;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ContactoRepository extends JpaRepository<Contacto, BigInteger> {

	@Query("SELECT 1 FROM Contacto WHERE rownum = 1")
	public Object findFirst();
}
