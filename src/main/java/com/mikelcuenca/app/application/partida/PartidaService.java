package com.mikelcuenca.app.application.partida;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PartidaService {

	public List<Partida> findAll();
	
	public List<Partida> findAll(Partida example);
	
	public Page<Partida> findAll(Pageable pageable);
	
	public Page<Partida> findAll(Partida example, Pageable pageable);

	public Partida add(Partida partida);

	public Partida update(Partida partida);

	public Partida delete(Partida partida);
	
	public Partida delete(BigInteger id);
}
