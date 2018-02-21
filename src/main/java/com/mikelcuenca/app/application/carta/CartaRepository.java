package com.mikelcuenca.app.application.carta;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartaRepository extends JpaRepository<Carta, BigInteger> {
	
//	public Carta getByCartaNombre();
	
	@Query("SELECT 1 FROM Carta WHERE rownum = 1")
	public Object findFirst();
}
