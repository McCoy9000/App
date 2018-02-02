package com.mikelcuenca.app.utilidades;

import java.util.UUID;

public class UIDGenerationUtils {

	public static String generateUID() {
		return UUID.randomUUID().toString();	
	}

}
