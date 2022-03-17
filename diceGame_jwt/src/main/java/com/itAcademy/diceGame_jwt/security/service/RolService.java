package com.itAcademy.diceGame_jwt.security.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itAcademy.diceGame_jwt.security.entity.Rol;
import com.itAcademy.diceGame_jwt.security.enums.RolNombre;
import com.itAcademy.diceGame_jwt.security.repository.RolRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }

    public void save(Rol rol){
        rolRepository.save(rol);
    }

}
