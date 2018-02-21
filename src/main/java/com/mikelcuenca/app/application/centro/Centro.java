package com.mikelcuenca.app.application.centro;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.mikelcuenca.app.application.contacto.Contacto;
import com.mikelcuenca.app.application.generic.Nombre;
import com.mikelcuenca.app.application.partida.Partida;

@Entity
public class Centro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger centroId;
	@NotNull
	@Column(nullable=false, unique=true)
	private UUID codCentro = UUID.randomUUID();
	@Column
	private Nombre centroNombre;
	@Column
	private String centroDescripcion;
	@OneToMany
	private List<Contacto> contactos;
	@OneToMany
	private Set<Partida> partidas;
	
	
	protected Centro() {
		
	}
	
	public Centro of() {
		return new Centro();
	}

	
	public BigInteger getCentroId() {
		return centroId;
	}

	public void setCentroId(BigInteger centroId) {
		this.centroId = centroId;
	}

	public UUID getCodCentro() {
		return codCentro;
	}

	public void setCodCentro(UUID codCentro) {
		this.codCentro = codCentro;
	}

	public Nombre getCentroNombre() {
		return centroNombre;
	}

	public void setCentroNombre(Nombre centroNombre) {
		this.centroNombre = centroNombre;
	}

	public String getCentroDescripcion() {
		return centroDescripcion;
	}

	public void setCentroDescripcion(String centroDescripcion) {
		this.centroDescripcion = centroDescripcion;
	}

	public List<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}

	public Set<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(Set<Partida> partidas) {
		this.partidas = partidas;
	}
	
	
}
