package com.example.zadatak.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.zadatak.models.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String>{

}
