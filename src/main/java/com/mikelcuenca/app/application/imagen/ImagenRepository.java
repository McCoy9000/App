package com.mikelcuenca.app.application.imagen;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ImagenRepository extends JpaRepository<Imagen, BigInteger> {

//	public Imagen getByImagenNombre(String imagenNombre);

	@Query("SELECT 1 FROM Imagen WHERE rownum = 1")
	public Object findFirst();
}
