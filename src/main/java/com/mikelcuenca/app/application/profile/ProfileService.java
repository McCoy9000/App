package com.mikelcuenca.app.application.profile;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfileService {

	public List<Profile> findAll();
	
	public List<Profile> findAll(Profile example);
	
	public Page<Profile> findAll(Pageable pageable);
	
	public Page<Profile> findAll(Profile example, Pageable pageable);

	public Profile add(Profile profile);

	public Profile update(Profile profile);

	public Profile delete(Profile profile);
	
	public Profile delete(BigInteger id);
}
