package com.mikelcuenca.app.application.profile;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProfileRepository extends JpaRepository<Profile, BigInteger> {

	public Profile getByProfileName(String profileNombre);

	@Query("SELECT 1 FROM Profile WHERE rownum = 1")
	public Object findFirst();
}
