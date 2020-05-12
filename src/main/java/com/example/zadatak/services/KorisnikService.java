package com.example.zadatak.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.zadatak.models.Korisnik;
import com.example.zadatak.repositories.KorisnikRepository;


@Service
public class KorisnikService {
	@Autowired
	private KorisnikRepository korRep;

	@Autowired
	private PermissionService permServis;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Iterable<Korisnik> getAll() {
		return korRep.findAll();
	}

	public Optional<Korisnik> getById(String id) {
		return korRep.findById(id);
	}
	
	

	public void editKorisnik(String id, Korisnik kor) {

		Optional<Korisnik> a = korRep.findById(id);

		if (a.isPresent()) {
			kor.setId(a.get().getId());
			kor.setLozinka(passwordEncoder.encode(kor.getLozinka()));

			korRep.save(kor);
		}
	}
	public void deleteKorisnik(String id) {
		Optional<Korisnik> u = korRep.findById(id);
		
		if (u.isPresent()) {
			korRep.delete(u.get());
		}
		
	}
	
	public void addStudentAccountData(Korisnik accountData) {
		accountData.setLozinka(passwordEncoder.encode(accountData.getLozinka()));
		permServis.addStudentPermission(accountData.getPermission());
		korRep.save(accountData);
	}



}
