package com.example.zadatak.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.zadatak.models.Administrator;
import com.example.zadatak.repositories.AdministratorRepository;
import com.example.zadatak.repositories.KorisnikRepository;


@Service
public class AdministratorService {

	@Autowired
	private AdministratorRepository adminRepo;
	
	@Autowired
	private KorisnikRepository korRepo;
	
	@Autowired
	private KorisnikService korServis;
	
	@Autowired
	private PermissionService permServis;
	
	public Iterable<Administrator> getAll() {
		return adminRepo.findAll();
	}
	
	public Optional<Administrator> getById(String id) {
		return adminRepo.findById(id);
	}
	
	
}
