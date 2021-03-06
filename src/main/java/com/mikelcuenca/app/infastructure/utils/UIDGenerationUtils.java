package com.mikelcuenca.app.infastructure.utils;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class UIDGenerationUtils {

	public static String generateUID() {
		return UUID.randomUUID().toString();	
	}

}
