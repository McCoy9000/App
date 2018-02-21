package com.mikelcuenca.app.application.contacto;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TelefonoService {

	public List<Telefono> findAll();
	
	public List<Telefono> findAll(Telefono example);
	
	public Page<Telefono> findAll(Pageable pageable);
	
	public Page<Telefono> findAll(Telefono example, Pageable pageable);

	public Telefono add(Telefono telefono);

	public Telefono update(Telefono telefono);

	public Telefono delete(Telefono telefono);
	
	public Telefono delete(BigInteger id);
}
