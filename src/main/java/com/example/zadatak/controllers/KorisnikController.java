package com.example.zadatak.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.zadatak.models.Korisnik;
import com.example.zadatak.models.Student;
import com.example.zadatak.services.KorisnikService;

@RestController
@RequestMapping("/korisnik")
public class KorisnikController {

	@Autowired
	KorisnikService accountDataService;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Korisnik>> getAccountData() {
        return new ResponseEntity<Iterable<Korisnik>>(accountDataService.getAll(), HttpStatus.OK);
    }
	
 
	
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Korisnik> getAccountDataById(@PathVariable String id) {
        Optional<Korisnik> accountData = accountDataService.getById(id);
        if(accountData.isPresent()) {
            return new ResponseEntity<Korisnik>(accountData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Korisnik>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
   	public ResponseEntity<Korisnik> editKorisnik(@PathVariable String id, @RequestBody Korisnik korisnik){
   		accountDataService.editKorisnik(id, korisnik);
   		return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
   	}

}
