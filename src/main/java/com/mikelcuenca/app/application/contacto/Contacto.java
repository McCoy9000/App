package com.mikelcuenca.app.application.contacto;

public abstract class Contacto {

	 enum TipoContacto {
		EMAIL,
		DIRECCION_POSTAL,
		TELEFONO,
		URL
	}

	protected TipoContacto tipoContacto;
	protected Boolean contactoPrincipal;
	public abstract Boolean isPrincipal();
	public abstract void setPrincipal(Boolean Principal);
	public abstract String getContactoCompleto();
	
}
