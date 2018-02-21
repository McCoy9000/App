package com.mikelcuenca.app.application.centro;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CentroRepository extends JpaRepository<Centro, BigInteger> {

	public Centro getByCentroNombre(String centroNombre);

	@Query("SELECT 1 FROM Centro WHERE rownum = 1")
	public Object findFirst();
}
