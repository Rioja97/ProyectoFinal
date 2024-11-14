package com.example.Personas.Clases.Personal;

import com.example.Login.Clases.Usuario;
import com.example.Login.Enums.Rol;
import com.example.Personas.Clases.Pasajero.Pasajero;

public class Recepcionista extends Usuario {

    public Recepcionista(String username, String passwordHash, Rol tipoIngreso, String nombreApellido, int dni, char sexo) {
        super(username, passwordHash, tipoIngreso, nombreApellido, dni, sexo);
    }

    public Recepcionista() {
    }

    public String hacerCheckIn(Pasajero pasajero, Reserva reserva){

    }

    public String hacerCheckOut(Pasajero pasajero, Reserva reserva){

    }
}
