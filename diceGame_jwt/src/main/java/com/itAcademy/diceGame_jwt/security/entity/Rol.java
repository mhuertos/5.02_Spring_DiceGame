package com.itAcademy.diceGame_jwt.security.entity;

import javax.persistence.*;

import com.itAcademy.diceGame_jwt.security.enums.RolNombre;

@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    
    //Se indica que va a ser un Enum de tipo String
    @Enumerated(EnumType.STRING)
    private RolNombre rolNombre;

    public Rol() {
    }

    public Rol(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RolNombre getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
}
