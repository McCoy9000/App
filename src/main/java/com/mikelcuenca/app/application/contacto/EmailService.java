package com.mikelcuenca.app.application.contacto;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmailService {

	public List<Email> findAll();
	
	public List<Email> findAll(Email example);
	
	public Page<Email> findAll(Pageable pageable);
	
	public Page<Email> findAll(Email example, Pageable pageable);

	public Email add(Email email);

	public Email update(Email email);

	public Email delete(Email email);
	
	public Email delete(BigInteger id);
}
