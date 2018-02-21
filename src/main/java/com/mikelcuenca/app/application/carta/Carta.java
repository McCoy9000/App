package com.mikelcuenca.app.application.carta;

import java.math.BigInteger;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.mikelcuenca.app.application.generic.Nombre;
import com.mikelcuenca.app.application.imagen.Imagen;

public class Carta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger cartaId;
	@NotNull
	@Column(nullable=false, unique=true)
	private UUID codCarta = UUID.randomUUID();
	@Embedded
	private Nombre cartaNombre;
	@Column
	private String Descripción;
	@ManyToMany
	@JoinTable(name="CARTAS_IMAGENES")
	private Map<String, Imagen> imagenes;
	@Column
	private Map<String, Object> opciones;
	@ManyToMany(mappedBy="cartas")
	private Mazo mazos;
	@ManyToMany(mappedBy="cartas")
	private Baraja barajas;
	
	protected Carta() {
		
	}
	
	public Carta of() {
		return new Carta();
	}

	public BigInteger getCartaId() {
		return cartaId;
	}
	
	public UUID getCodCarta() {
		return codCarta;
	}

	public void setCodCarta(UUID codCarta) {
		this.codCarta = codCarta;
	}

	public Nombre getCartaNombre() {
		return cartaNombre;
	}

	public void setCartaNombre(Nombre cartaNombre) {
		this.cartaNombre = cartaNombre;
	}

	public String getDescripción() {
		return Descripción;
	}

	public void setDescripción(String descripción) {
		Descripción = descripción;
	}

	public Map<String, Imagen> getImagenes() {
		return imagenes;
	}

	public void setImagenes(Map<String, Imagen> imagenes) {
		this.imagenes = imagenes;
	}

	public Map<String, Object> getOpciones() {
		return opciones;
	}

	public void setOpciones(Map<String, Object> opciones) {
		this.opciones = opciones;
	}

	public Mazo getMazos() {
		return mazos;
	}

	public void setMazos(Mazo mazos) {
		this.mazos = mazos;
	}

	public Baraja getBarajas() {
		return barajas;
	}

	public void setBarajas(Baraja barajas) {
		this.barajas = barajas;
	}
	
}
