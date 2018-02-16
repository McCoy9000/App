package com.mikelcuenca.app._model.application.generic;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mikelcuenca.app.control.PruebasController;

@Embeddable
public class Email implements Serializable {

	private static final long serialVersionUID = 5620593760401949871L;
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PruebasController.class);

	private String emailAddress;

	protected Email() {
		
	}
	
	protected Email(String emailAddress) {
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
}
