package com.mikelcuenca.app.application.partida;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PartidaRepository extends JpaRepository<Partida, BigInteger> {
	
	public Partida getByPartidaId();
	
	@Query("SELECT 1 FROM Partida WHERE rownum = 1")
	public Object findFirst();
}
