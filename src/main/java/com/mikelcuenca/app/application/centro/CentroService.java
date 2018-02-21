package com.mikelcuenca.app.application.centro;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CentroService {

	public List<Centro> findAll();
	
	public List<Centro> findAll(Centro example);
	
	public Page<Centro> findAll(Pageable pageable);
	
	public Page<Centro> findAll(Centro example, Pageable pageable);

	public Centro add(Centro centro);

	public Centro update(Centro centro);

	public Centro delete(Centro centro);
	
	public Centro delete(BigInteger id);
}
