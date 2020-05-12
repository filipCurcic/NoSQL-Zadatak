package com.example.zadatak.repositories;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.zadatak.models.Permission;


@Repository
public interface PermissionRepository extends MongoRepository<Permission, String>{
	Optional<Permission> getByAuthority(String authority);
}
