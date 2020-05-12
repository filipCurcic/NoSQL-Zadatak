package com.example.zadatak.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.zadatak.models.Permission;
import com.example.zadatak.repositories.PermissionRepository;


@Service
public class PermissionService {
	@Autowired
	private PermissionRepository permRep;
	
	public void addAdministratorPermission(Permission permission) {
		permission.setAuthority("ROLE_ADMINISTRATOR");
		permRep.save(permission);
	}
	
	public void addStudentPermission(Permission permission) {
		permission.setAuthority("ROLE_STUDENT");
		permRep.save(permission);
	}

}
