package com.mikelcuenca.app.application.carta;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
import com.mikelcuenca.app.application.imagen.Imagen;

@Entity
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
	private HashSet<String> opciones;
	@ManyToMany(mappedBy="cartas")
	private Set<Mazo> mazos;
	@ManyToMany(mappedBy="cartas")
	private Set<Baraja> barajas;
	
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

	public void setImagenes(HashMap<String, Imagen> imagenes) {
		this.imagenes = imagenes;
	}

	public Set<String> getOpciones() {
		return opciones;
	}

	public void setOpciones(HashSet<String> opciones) {
		this.opciones = opciones;
	}

	public Set<Mazo> getMazos() {
		return mazos;
	}

	public void setMazos(Set<Mazo> mazos) {
		this.mazos = mazos;
	}

	public Set<Baraja> getBarajas() {
		return barajas;
	}

	public void setBarajas(Set<Baraja> barajas) {
		this.barajas = barajas;
	}
	
}
