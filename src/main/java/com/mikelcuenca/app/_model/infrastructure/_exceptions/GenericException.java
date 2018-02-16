package com.mikelcuenca.app._model.infrastructure._exceptions;

import com.mikelcuenca.app.utils.StackTraceManager;

@SuppressWarnings("serial")
public class GenericException extends RuntimeException {

	private String error;
	private String messageForUser;
	private String exceptionClassName;
	private String stacktrace;

	public GenericException() {
		super();
	}

	public GenericException(String message, Throwable ex) {
		super();
		if (ex!=null){
			this.error=ex.getMessage();
			this.messageForUser = message;
			this.stacktrace = StackTraceManager.getStackTrace(ex);
			this.exceptionClassName = ex.getClass().getName();
		}
	}
	
	public GenericException(Throwable ex) {
		super();
		if (ex!=null){
			this.error=ex.getMessage();
			this.messageForUser = null;
			this.stacktrace = StackTraceManager.getStackTrace(ex);
			this.exceptionClassName = ex.getClass().getName();
		}
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessageForUser() {
		return messageForUser;
	}

	public void setMessageForUser(String messageForUser) {
		this.messageForUser = messageForUser;
	}

	public String getExceptionClassName() {
		return exceptionClassName;
	}

	public void setExceptionClassName(String exceptionClassName) {
		this.exceptionClassName = exceptionClassName;
	}
	
	public String getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}

}
