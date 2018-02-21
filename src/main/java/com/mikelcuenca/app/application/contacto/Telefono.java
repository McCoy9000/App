package com.mikelcuenca.app.application.contacto;

import java.math.BigInteger;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Telefono extends Contacto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger telefonoId;
	@NotNull
	@Column(nullable=false, unique=true)
	private UUID codTelefono = UUID.randomUUID();
	@Column
	private String telefonoNumero;
	
	
	protected Telefono() {
		this.tipoContacto = TipoContacto.TELEFONO;
	}
	
	public Telefono of() {
		return new Telefono();
	}
	
	
	@Override
	public Boolean isPrincipal() {
		return this.contactoPrincipal;
	}

	@Override
	public void setPrincipal(Boolean principal) {
		this.contactoPrincipal = principal;
	}

	@Override
	public String getContactoCompleto() {
		return telefonoNumero;
	}
	
	public BigInteger getTelefonoId() {
		return telefonoId;
	}

	public UUID getCodTelefono() {
		return codTelefono;
	}

	public void setCodTelefono(UUID codTelefono) {
		this.codTelefono = codTelefono;
	}

	public void setTelefono(String telefonoNumero) {
		this.telefonoNumero = telefonoNumero;
	}
	
	public String getTelefono() {
		return telefonoNumero;
	}
	
	public String toString() {
		return telefonoNumero;
	}
}
