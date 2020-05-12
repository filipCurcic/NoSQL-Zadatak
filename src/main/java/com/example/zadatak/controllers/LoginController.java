package com.example.zadatak.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.zadatak.models.Korisnik;
import com.example.zadatak.services.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody Korisnik korisnik ) {
		return loginService.authenticateUser(korisnik);
	}

}
