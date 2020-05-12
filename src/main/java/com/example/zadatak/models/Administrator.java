package com.example.zadatak.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Administrator {
	
	
	@Id
	private String id;
	
	
	private Korisnik korisnik;
	
	public Administrator() {
		
	}

	public Administrator(String id, Korisnik korisnik) {
		super();
		this.id = id;
		this.korisnik = korisnik;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}



	@Override
	public String toString() {
		return "Administrator [id=" + id + ", korisnik=" + korisnik + "]";
	}
	
	
	
	
}
