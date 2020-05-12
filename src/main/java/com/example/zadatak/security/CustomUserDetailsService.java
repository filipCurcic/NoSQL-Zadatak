package com.example.zadatak.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.zadatak.exeption.ResourceNotFoundException;
import com.example.zadatak.models.Korisnik;
import com.example.zadatak.repositories.KorisnikRepository;
import com.example.zadatak.utils.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
     
    	Korisnik korisnik = korisnikRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));
    	
    	return UserPrincipal.create(korisnik);
    }
    

    @Transactional
    public UserDetails loadUserById(String id) {
    	Korisnik korisnik = korisnikRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    	System.out.println("id");
        return UserPrincipal.create(korisnik);
   }
}