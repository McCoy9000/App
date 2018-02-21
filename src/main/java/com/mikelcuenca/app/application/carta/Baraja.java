package com.mikelcuenca.app.application.carta;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.mikelcuenca.app.application.generic.Nombre;
import com.mikelcuenca.app.application.generic.NombrePersona;

@Entity
public class Baraja {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger barajaId;
	@NotNull
	@Column(nullable=false, unique=true)
	private UUID codCarta = UUID.randomUUID();
	@Embedded
	private NombrePersona barajaNombre;
	@Column
	private String barajaDescripcion;
	@ManyToMany
	@JoinTable(name="BARAJAS_CARTAS")
	private List<Carta> cartas;
	
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

	public void setBarajaNombre(NombrePersona barajaNombre) {
		this.barajaNombre = barajaNombre;
	}

	public String getBarajaDescripcion() {
		return barajaDescripcion;
	}

	public void setBarajaDescripcion(String barajaDescripcion) {
		this.barajaDescripcion = barajaDescripcion;
	}

	public List<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(ArrayList<Carta> cartas) {
		this.cartas = cartas;
	}
	
	
}
