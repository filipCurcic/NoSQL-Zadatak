package com.example.zadatak.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.zadatak.models.Administrator;


@Repository
public interface AdministratorRepository extends MongoRepository<Administrator, String> {

}