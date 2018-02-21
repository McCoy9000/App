package com.mikelcuenca.app.application.contacto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class Email extends Contacto implements Serializable {

	private static final long serialVersionUID = 5620593760401949871L;
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(Contacto.class);
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger emailId;
	@NotNull
	@Column(nullable=false, unique=true)
	private UUID codEmail = UUID.randomUUID();
	@Column
	private String emailAddress;

	
	protected Email() {
		this.tipoContacto = TipoContacto.EMAIL;
	}
	
	protected Email(String emailAddress) {
		this.tipoContacto = TipoContacto.EMAIL;
		this.emailAddress = emailAddress;
	}
	
	
	
	public BigInteger getEmailId() {
		return emailId;
	}

	public UUID getCodEmail() {
		return codEmail;
	}

	public void setCodEmail(UUID codEmail) {
		this.codEmail = codEmail;
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
