package com.example.Personas.Clases.Personal;

import com.example.Hotel.Clases.Habitacion;
import com.example.Hotel.Clases.Reserva;
import com.example.Hotel.Clases.enumeradores.Estado;
import com.example.Login.Clases.Usuario;
import com.example.Login.Enums.Rol;
import com.example.Personas.Clases.Pasajero.Pasajero;

import java.util.Random;

public class Personal extends Usuario {

    public Personal(String username, String passwordHash, Rol tipoIngreso, String nombreApellido) {
        super(username, passwordHash, tipoIngreso, nombreApellido);
        this.setTipoIngreso(Rol.PERSONAL_LIMPIEZA);
    }

    public Personal() {
        this.setTipoIngreso(Rol.PERSONAL_LIMPIEZA);
    }

    public String limpiarHabitacion(Habitacion habitacion){
        habitacion.setLimpia(true);

        return "La habitacion se ha limpiado";
    }

    public String repararHabitacion(Habitacion habitacion){
        habitacion.setReparacion(false);

        return "La habitacion se ha reparado";
    }

    public String hacerCheckIn(Reserva reserva){
        reserva.getHabitacion().setEstado(Estado.OCUPADO);

        return "La habitacion ahora se encuentra ocupada";
    }


    public String hacerCheckOut(Reserva reserva){
        reserva.getHabitacion().setEstado(Estado.DISPONIBLE);
        Random rand = new Random();
        reserva.getHabitacion().setReparacion(rand.nextBoolean());
        reserva.getHabitacion().setLimpia(false);

        return "La habitacion ahora se desocupado";
    }

}
