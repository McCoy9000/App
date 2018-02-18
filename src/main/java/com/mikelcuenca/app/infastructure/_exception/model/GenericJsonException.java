package com.mikelcuenca.app.infastructure._exception.model;

@SuppressWarnings("serial")
public class GenericJsonException extends GenericException{

	public GenericJsonException( String message, Throwable ex) {
		super(message, ex);
	}
	
	public GenericJsonException(Throwable ex) {
		super(ex);
	}
}
