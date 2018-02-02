/**
 * 
 */
package com.mikelcuenca.app._model.infrastructure;

import com.mikelcuenca.app._model.infrastructure._exceptions.GenericException;
import com.mikelcuenca.app._model.infrastructure._exceptions.GenericJsonException;

/**
 * Model de clase para mostrar datos de los errores
 * @author BICUGUAL
 *
 */
public class ErrorInfo {

	private String messageForUser;
	private String error;
	private String nameClass;
	private String uri;
	private String stacktrace;

	public ErrorInfo() {
		super();
	}
	
	public ErrorInfo(String messageForUser, String error, String nameClass, String uri, String stacktrace) {
		super();
		this.messageForUser = messageForUser;
		this.error=error;
		this.nameClass = nameClass;
		this.uri = uri;
		this.stacktrace = stacktrace;
	}

	public ErrorInfo(GenericException ex, String uri) {
		super();
		this.messageForUser = ex.getMessageForUser();
		this.error=ex.getError();
		this.nameClass=ex.getExceptionClassName();
		this.stacktrace=ex.getStacktrace();
		this.uri = uri;
	}
	
	public ErrorInfo(GenericJsonException ex, String uri) {
		super();
		this.messageForUser = ex.getMessageForUser();
		this.error=ex.getError();
		this.nameClass=ex.getExceptionClassName();
		this.stacktrace=ex.getStacktrace();
		this.uri = uri;
	}

	public String getMessageForUser() {
		return messageForUser;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setMessageForUser(String messageForUser) {
		this.messageForUser = messageForUser;
	}

	public String getNameClass() {
		return nameClass;
	}

	public void setNameClass(String nameClass) {
		this.nameClass = nameClass;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}
	
}
