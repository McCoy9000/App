package com.mikelcuenca.app.application.carta;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.mikelcuenca.app.application.generic.Nombre;

public class Baraja {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger barajaId;
	@Column
	private Nombre barajaNombre;
	@Column
	private String barajaDescripcion;
	@ManyToMany
	@JoinTable(name="BARAJAS_CARTAS")
	private Carta cartas;
	
	protected Baraja() {
		
	}
	
	public Baraja of() {
		return new Baraja();
	}

	public BigInteger getBarajaId() {
		return barajaId;
	}

	public void setBarajaId(BigInteger barajaId) {
		this.barajaId = barajaId;
	}

	public Nombre getBarajaNombre() {
		return barajaNombre;
	}

	public void setBarajaNombre(Nombre barajaNombre) {
		this.barajaNombre = barajaNombre;
	}

	public String getBarajaDescripcion() {
		return barajaDescripcion;
	}

	public void setBarajaDescripcion(String barajaDescripcion) {
		this.barajaDescripcion = barajaDescripcion;
	}

	public Carta getCartas() {
		return cartas;
	}

	public void setCartas(Carta cartas) {
		this.cartas = cartas;
	}
	
	
}
