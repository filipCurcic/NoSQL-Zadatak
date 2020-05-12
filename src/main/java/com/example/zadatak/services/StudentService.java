package com.example.zadatak.services;

import java.security.AuthProvider;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.zadatak.models.Korisnik;
import com.example.zadatak.models.Student;
import com.example.zadatak.repositories.KorisnikRepository;
import com.example.zadatak.repositories.PermissionRepository;
import com.example.zadatak.repositories.StudentRepository;


@Service
public class StudentService {
	@Autowired
	private StudentRepository studRep;
	
	@Autowired
	private KorisnikService korServis;
	
	@Autowired
	private KorisnikRepository korRep;
	
	@Autowired
	private PermissionRepository permRep;
	
	public Iterable<Student> getAll() {
		return studRep.findAll();
	}
	
	public Optional<Student> getById(String id){
		return studRep.findById(id);
	}
	
	public HttpStatus addStudent(Student student) {
		
		Optional<Korisnik> accountData = korRep.findByEmail(student.getKorisnik().getEmail());
		
		if(accountData.isPresent()) {
			return HttpStatus.IM_USED;
		} else {

		
			korServis.addStudentAccountData(student.getKorisnik());
			studRep.save(student);
			
			return HttpStatus.CREATED;
		}
			
	}
	
	public void editStudent(String id, Student student) {
			
			Optional<Student> s = studRep.findById(id);
			
			if(s.isPresent()) {
				student.setId(s.get().getId());
				korServis.editKorisnik(s.get().getKorisnik().getId(), s.get().getKorisnik());
				
				studRep.save(student);
			}
		}
	
	public void deleteStudent(String id) {
		Optional<Student> s = studRep.findById(id);
		
		if (s.isPresent()) {
			korServis.deleteKorisnik(s.get().getKorisnik().getId());
			studRep.delete(s.get());
		}
		
	}



	
	
	
}
