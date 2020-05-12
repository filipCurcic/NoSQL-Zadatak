package com.example.zadatak.controllers;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.zadatak.models.Korisnik;
import com.example.zadatak.services.ReadFileService;

@RestController
@RequestMapping("/read")
public class ReadFileController {
	
	@Autowired
	private ReadFileService readFileService;

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ResponseEntity<Korisnik> uploadFile(@ModelAttribute("file") Korisnik korisnik) throws JSONException {
		boolean isFlag = readFileService.saveReadFile(korisnik.getFile());
	return new ResponseEntity<Korisnik>(korisnik, HttpStatus.CREATED);
	}
}
