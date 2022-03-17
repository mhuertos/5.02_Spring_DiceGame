package com.itAcademy.diceGame_jwt.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.itAcademy.diceGame_jwt.security.entity.Rol;
import com.itAcademy.diceGame_jwt.security.enums.RolNombre;
import com.itAcademy.diceGame_jwt.security.service.RolService;

@Component
public class CreateRoles implements CommandLineRunner{
	@Autowired
	RolService rolService;
	
	@Override
	public void run(String... args) throws Exception {
	    Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
	    Rol rolUser = new Rol(RolNombre.ROLE_USER);
	    rolService.save(rolAdmin);
	    rolService.save(rolUser);
	}

}
