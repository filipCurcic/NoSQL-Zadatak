package com.example.zadatak.repositories;

import org.json.JSONObject;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CustomExcelRepository extends MongoRepository<JSONObject, String> {

}
