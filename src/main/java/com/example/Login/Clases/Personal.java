package com.example.Login.Clases;

import com.example.Excepciones.HabitacionNoDisponibleException;
import com.example.Hotel.Clases.Habitacion;
import com.example.Hotel.Clases.Hotel;
import com.example.Hotel.Clases.Reserva;
import com.example.Hotel.Enum.Estado;
import com.example.Interfaces.MetodosPersonal;
import com.example.Login.Enums.Rol;

import java.util.HashMap;
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

    public String hacerCheckIn(Reserva reserva,Hotel hotel)throws HabitacionNoDisponibleException {
        HashMap<Integer,Reserva> reservas=new HashMap<>();

        for(Reserva reserva1 : reservas.values()){
            if(reserva1.getHabitacion().getNumero() == reserva.getHabitacion().getNumero()){
                reserva1.getHabitacion().setEstado(Estado.OCUPADO);
                reservas.remove(reserva.getHabitacion().getNumero());
            }else{
                throw new HabitacionNoDisponibleException("Esta habitacion no existe.");
            }

        }
        return "Check In realizado con exito.";
    }


    public void hacerCheckOut(Habitacion habitacion){

        habitacion.setEstado(Estado.DISPONIBLE);
        Random rand = new Random();
        habitacion.setReparacion(rand.nextBoolean());
        habitacion.setLimpia(false);

    }

}
