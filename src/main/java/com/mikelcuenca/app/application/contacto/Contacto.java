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
public abstract class Contacto {

	 enum TipoContacto {
		EMAIL,
		DIRECCION_POSTAL,
		TELEFONO,
		URL
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger contactoId;
	@NotNull
	@Column(nullable=false, unique=true)
	private UUID codContacto = UUID.randomUUID();
	@Column
	protected TipoContacto contactoTipo;
	@Column
	protected Boolean contactoPrincipal;
	
	public abstract Boolean isPrincipal();
	public abstract void setPrincipal(Boolean Principal);
	public abstract String getContactoCompleto();
	
	public BigInteger getContactoId() {
		return contactoId;
	}

	public UUID getCodContacto() {
		return codContacto;
	}

	public void setCodContacto(UUID codContacto) {
		this.codContacto = codContacto;
	}

	
}
