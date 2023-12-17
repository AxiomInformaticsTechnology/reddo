package com.aidos.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_code")
public class OAuthCode {

	@Id
	private String code;

	private Byte[] authentication;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Byte[] getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Byte[] authentication) {
		this.authentication = authentication;
	}

}