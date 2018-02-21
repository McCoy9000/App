package com.mikelcuenca.app.application.contacto;

import java.io.Serializable;

import javax.persistence.Column;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Email extends Contacto implements Serializable {

	private static final long serialVersionUID = 5620593760401949871L;
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(Contacto.class);
	
	@Column
	private String emailAddress;

	
	protected Email() {
		this.contactoTipo = TipoContacto.EMAIL;
	}
	
	protected Email(String emailAddress) {
		this.contactoTipo = TipoContacto.EMAIL;
		this.emailAddress = emailAddress;
	}
	
		
	public static Email of(String emailAddress) {
		return new Email(emailAddress);
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String toString() {
		return this.emailAddress;
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
		return this.emailAddress;
	}
}
