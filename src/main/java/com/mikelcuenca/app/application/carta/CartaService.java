package com.mikelcuenca.app.application.carta;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartaService {

	public List<Carta> findAll();
	
	public List<Carta> findAll(Carta example);
	
	public Page<Carta> findAll(Pageable pageable);
	
	public Page<Carta> findAll(Carta example, Pageable pageable);

	public Carta add(Carta carta);

	public Carta update(Carta carta);

	public Carta delete(Carta carta);
	
	public Carta delete(BigInteger id);
}
