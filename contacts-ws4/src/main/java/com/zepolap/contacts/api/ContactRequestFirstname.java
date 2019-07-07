package com.zepolap.contacts.api;

import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ContactRequestFirstname {

	private Long id;
	
	@NotNull(message="El nombre es requerido")
	@Size(min=2, max=30, message="El nombre debe tener entre {min} y {max} caracteres")
	private String firstname;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
}
