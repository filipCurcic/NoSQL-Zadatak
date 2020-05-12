package com.example.zadatak.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.zadatak.models.Korisnik;


@Repository
public interface KorisnikRepository extends MongoRepository<Korisnik, String> {
	
	public Optional<Korisnik> findByIme(String ime);
	public Optional<Korisnik> findByEmail(String email);
	public Optional<Korisnik> findById(String id);


}