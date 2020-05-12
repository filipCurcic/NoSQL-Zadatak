package com.example.zadatak.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.zadatak.models.Korisnik;
import com.example.zadatak.payload.AuthResponse;
import com.example.zadatak.utils.TokenProvider;


@Service
public class LoginService {

	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody Korisnik korisnik) {
		 Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        korisnik.getEmail(),
	                        korisnik.getLozinka()
	                )
	        );
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String token = tokenProvider.createToken(authentication);
		return ResponseEntity.ok(new AuthResponse(token));
	}
}
