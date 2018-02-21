package com.mikelcuenca.app.application.carta;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MazoService {

	public List<Mazo> findAll();
	
	public List<Mazo> findAll(Mazo example);
	
	public Page<Mazo> findAll(Pageable pageable);
	
	public Page<Mazo> findAll(Mazo example, Pageable pageable);

	public Mazo add(Mazo mazo);

	public Mazo update(Mazo mazo);

	public Mazo delete(Mazo mazo);
	
	public Mazo delete(BigInteger id);
}
