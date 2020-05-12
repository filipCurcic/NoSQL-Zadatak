package com.example.zadatak.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.zadatak.models.Student;
import com.example.zadatak.services.ExportDataService;
import com.example.zadatak.services.StudentService;
import com.example.zadatak.utils.ExcelGenerator;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ExportDataService exportDataService;
	
	@Autowired
	private KorisnikController kc;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Student>> getAll() {
        return new ResponseEntity<Iterable<Student>>(studentService.getAll(), HttpStatus.OK);
    }
	

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Student> getById(@PathVariable String id) {
        Optional<Student> student = studentService.getById(id);
        if(student.isPresent()) {
            return new ResponseEntity<Student>(student.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
    }
	
    @RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Student> addStudent(@RequestBody Student student){
    	studentService.addStudent(student);
		return new ResponseEntity<Student>(student, HttpStatus.CREATED);
	}

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
   	public ResponseEntity<Student> editStudent(@PathVariable String id, @RequestBody Student student){
   		studentService.editStudent(id, student);
   		kc.editKorisnik(student.getKorisnik().getId(), student.getKorisnik());
   		return new ResponseEntity<Student>(student, HttpStatus.OK);
   	}
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Student> removeStudent(@PathVariable String id) {
        try {
        	studentService.deleteStudent(id);
        }catch (Exception e) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/download/student.xlsx", method=RequestMethod.GET)
    public ResponseEntity<InputStreamResource> excelCustomersReport() throws IOException {
        List<Student> customers = (List<Student>) studentService.getAll();
    
    ByteArrayInputStream in = ExcelGenerator.customersToExcel(customers);
    
    HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=student.xlsx");
    
     return ResponseEntity
                  .ok()
                  .headers(headers)
                  .body(new InputStreamResource(in));
    }

    @RequestMapping(value="/download/{flag}/{fileType}/{fileName}", method=RequestMethod.GET)
	public ResponseEntity<InputStreamResource> downloadsFiles(@PathVariable("flag") int flag,@PathVariable("fileType") String fileType,@PathVariable("fileName") String fileName) throws IOException{
	    List<?> objects=new ArrayList<>();
	    if(flag==1) {
	        objects=exportDataService.getData();
	    }
	    ByteArrayInputStream in = exportDataService.downloadsFiles(objects,fileType);
	    HttpHeaders headers = new HttpHeaders();
	    if(fileType.equals("Excel")) {
	        headers.add("Content-Disposition", "attachment; filename="+fileName+".xlsx");
	    }else if(fileType.equals("Pdf")){
	        headers.add("Content-Disposition", "attachment; filename="+fileName+".pdf");
	    }else if(fileType.equals("Csv")) {
	        headers.add("Content-Disposition", "attachment; filename="+fileName+".csv");
	    }
	    return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
}
