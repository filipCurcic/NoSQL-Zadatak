package com.example.zadatak.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Permission {
	
	@Id
	private String id;
	
	private String authority;
	
	public Permission() {
		
	}
	
	public Permission(String id, String authority) {
		super();
		this.id = id;
		this.authority = authority;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}


}