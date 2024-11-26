package com.example.Login.Clases;

import com.example.Hotel.Clases.Habitacion;
import com.example.Hotel.Clases.Hotel;
import com.example.Hotel.Clases.Reserva;
import com.example.Hotel.Enum.Estado;
import com.example.Interfaces.MetodosPersonal;
import com.example.Login.Enums.Rol;

import java.util.Random;

public class Personal extends Usuario{

    public Personal(String username, String passwordHash, Rol tipoIngreso, String nombreApellido) {
        super(username, passwordHash, tipoIngreso, nombreApellido);
        this.setTipoIngreso(Rol.PERSONAL_LIMPIEZA);
    }

    public Personal() {
        this.setTipoIngreso(Rol.PERSONAL_LIMPIEZA);
    }

    public void limpiarHabitacion(Habitacion habitacion){
        habitacion.setLimpia(true);
    }

    public void repararHabitacion(Habitacion habitacion){
        habitacion.setReparacion(false);
    }

    public void hacerCheckIn(Reserva reserva, Hotel hotel){
        reserva.getHabitacion().setEstado(Estado.OCUPADO);
    }


    public void hacerCheckOut(Habitacion habitacion){

        habitacion.setEstado(Estado.DISPONIBLE);
        Random rand = new Random();
        habitacion.setReparacion(rand.nextBoolean());
        habitacion.setLimpia(false);

    }

}
