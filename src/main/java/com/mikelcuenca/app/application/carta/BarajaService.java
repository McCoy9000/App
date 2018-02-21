package com.mikelcuenca.app.application.carta;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BarajaService {

	public List<Baraja> findAll();
	
	public List<Baraja> findAll(Baraja example);
	
	public Page<Baraja> findAll(Pageable pageable);
	
	public Page<Baraja> findAll(Baraja example, Pageable pageable);

	public Baraja add(Baraja baraja);

	public Baraja update(Baraja baraja);

	public Baraja delete(Baraja baraja);
	
	public Baraja delete(BigInteger id);
}
