package com.example.Personas.Clases.Personal;

import com.example.Hotel.Clases.Habitacion;
import com.example.Hotel.Clases.Reserva;
import com.example.Login.Clases.Usuario;
import com.example.Login.Enums.Rol;
import com.example.Personas.Clases.Pasajero.Pasajero;

public class Personal extends Usuario {

    public Personal(String username, String passwordHash, Rol tipoIngreso, String nombreApellido) {
        super(username, passwordHash, tipoIngreso, nombreApellido);
    }

    public Personal() {
    }

    public String limpiarHabitacion(Habitacion habitacion){

        

        return "La habitacion se ha limpiado";
    }

    public String repararHabitacion(Habitacion habitacion){
        habitacion.repararHabitacion();

        return "La habitacion se ha reparado";
    }

    public String hacerCheckIn(Reserva reserva){
        reserva.ocuparHabitacion();

        return "La habitacion ahora se encuentra ocupada";
    }


    public String hacerCheckOut(Reserva reserva){
        reserva.desocuparHabitacion();

        return "La habitacion ahora se desocupado";
    }

}
