package com.mikelcuenca.app.application.generic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class URL implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column
	private String urlAddress; 
	
	protected URL() {
	}

	public URL(String urlAddress) {
		this.urlAddress = urlAddress;
	}
	
	public static URL of(String urlAddress) {
		if(urlAddress == null || urlAddress.trim() == "") {
			return new URL("unknownURL");
		}
		return new URL(urlAddress);
	}

	public String getUrlAddress() {
		return urlAddress;
	}

	public void setUrlAddress(String urlAddress) {
		if(urlAddress == null) {
			this.urlAddress = "unknownURL";
		}
		this.urlAddress = urlAddress;
	}
}
