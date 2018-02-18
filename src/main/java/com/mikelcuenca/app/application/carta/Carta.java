package com.mikelcuenca.app.application.carta;

import java.math.BigInteger;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.mikelcuenca.app.application.mazo.Mazo;

public class Carta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger cartaId;
	@NotNull
	@Column(nullable=false, unique=true)
	private UUID codCarta = UUID.randomUUID();
	@ManyToMany(mappedBy="cartas")
	private Mazo mazos;
	
	protected Carta() {
		
	}
	
	public Carta of() {
		return new Carta();
	}
}
