package com.mikelcuenca.app.infastructure.generic.model;

import com.mikelcuenca.app.infastructure._exception.model.GenericException;
import com.mikelcuenca.app.infastructure._exception.model.GenericJsonException;

public class ErrorInfo {

	private String messageForUser;
	private String error;
	private String nameClass;
	private String uri;
	private String stackTrace;

	public ErrorInfo() {
		super();
	}
	
	public ErrorInfo(String messageForUser, String error, String nameClass, String uri, String stacktrace) {
		super();
		this.messageForUser = messageForUser;
		this.error=error;
		this.nameClass = nameClass;
		this.uri = uri;
		this.stackTrace = stacktrace;
	}

	public ErrorInfo(GenericException ex, String uri) {
		super();
		this.messageForUser = ex.getMessageForUser();
		this.error=ex.getError();
		this.nameClass=ex.getExceptionClassName();
		this.stackTrace=ex.getStacktrace();
		this.uri = uri;
	}
	
	public ErrorInfo(GenericJsonException ex, String uri) {
		super();
		this.messageForUser = ex.getMessageForUser();
		this.error=ex.getError();
		this.nameClass=ex.getExceptionClassName();
		this.stackTrace=ex.getStacktrace();
		this.uri = uri;
	}

	@Override
	public String toString() {
		return "ErrorInfo [messageForUser=" + messageForUser + ", error=" + error + ", nameClass=" + nameClass
				+ ", uri=" + uri + ", stackTrace=" + stackTrace + "]";
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

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
	
}
