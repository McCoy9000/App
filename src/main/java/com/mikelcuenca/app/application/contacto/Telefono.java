package com.mikelcuenca.app.application.contacto;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Telefono extends Contacto {

	@Column
	private String telefonoNumero;
	
	
	protected Telefono() {
		this.contactoTipo = TipoContacto.TELEFONO;
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
