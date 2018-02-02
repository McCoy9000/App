/**
 * 
 */
package com.mikelcuenca.app._model.infrastructure._exceptions;

/**
 * Clase general de Excepciones para handlers que devuelven json.
 * @author BICUGUAL
 */
@SuppressWarnings("serial")
public class GenericJsonException extends GenericException{

	public GenericJsonException( String message, Throwable ex) {
		super(message, ex);
	}
	
	public GenericJsonException(Throwable ex) {
		super(ex);
	}
}
