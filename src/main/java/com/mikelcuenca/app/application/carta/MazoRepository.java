package com.mikelcuenca.app.application.carta;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MazoRepository extends JpaRepository<Mazo, BigInteger> {
	
	public Mazo getByMazoId();
	
	@Query("SELECT 1 FROM Mazo WHERE rownum = 1")
	public Object findFirst();
}
