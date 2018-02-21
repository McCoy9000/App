package com.mikelcuenca.app.application.imagen;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ImagenService {

	public List<Imagen> findAll();
	
	public List<Imagen> findAll(Imagen example);
	
	public Page<Imagen> findAll(Pageable pageable);
	
	public Page<Imagen> findAll(Imagen example, Pageable pageable);

	public Imagen add(Imagen imagen);

	public Imagen update(Imagen imagen);

	public Imagen delete(Imagen imagen);
	
	public Imagen delete(BigInteger id);
}
