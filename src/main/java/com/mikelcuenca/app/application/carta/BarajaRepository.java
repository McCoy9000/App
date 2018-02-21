package com.mikelcuenca.app.application.carta;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BarajaRepository extends JpaRepository<Baraja, BigInteger> {
	
//	public Baraja getByBarajaNombre();
	
	@Query("SELECT 1 FROM Baraja WHERE rownum = 1")
	public Object findFirst();
}
