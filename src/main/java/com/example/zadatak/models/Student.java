package com.example.zadatak.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

@Document
public class Student {

	@Id
	private String id;
	
	private Korisnik korisnik;
	
	@Transient
	private MultipartFile file;

	public Student(String id, Korisnik korisnik) {
		super();
		this.id = id;
		this.korisnik = korisnik;
	}
	public Student() {
		
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
	
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", korisnik=" + korisnik + "]";
	}
	
	
	
}
