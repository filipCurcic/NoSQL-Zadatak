package com.example.zadatak.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.zadatak.models.Administrator;
import com.example.zadatak.services.AdministratorService;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {
	@Autowired
	private AdministratorService administratorService;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Administrator>> getAll() {
        return new ResponseEntity<Iterable<Administrator>>(administratorService.getAll(), HttpStatus.OK);
    }
	


}