package com.example.Personas.Clases.Personal;

import com.example.Login.Clases.Usuario;
import com.example.Login.Enums.Rol;

public class Limpíeza extends Usuario {

    public Limpíeza(String username, String passwordHash, Rol tipoIngreso, String nombreApellido, int dni, char sexo) {
        super(username, passwordHash, tipoIngreso, nombreApellido, dni, sexo);
    }

    public Limpíeza() {
    }

    public String limpiarHabitacion(Habitacion habitacion){

    }
}
