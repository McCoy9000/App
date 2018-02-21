package com.mikelcuenca.app.application.contacto;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactoService {

	public List<Contacto> findAll();
	
	public List<Contacto> findAll(Contacto example);
	
	public Page<Contacto> findAll(Pageable pageable);
	
	public Page<Contacto> findAll(Contacto example, Pageable pageable);

	public Contacto add(Contacto contacto);

	public Contacto update(Contacto contacto);

	public Contacto delete(Contacto contacto);
	
	public Contacto delete(BigInteger id);
}
