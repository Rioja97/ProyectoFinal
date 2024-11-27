package com.example.Login.Clases;

import com.example.Excepciones.HabitacionNoDisponibleException;
import com.example.Hotel.Clases.Habitacion;
import com.example.Hotel.Clases.Hotel;
import com.example.Hotel.Clases.Reserva;
import com.example.Hotel.Enum.Estado;
import com.example.Login.Enums.Rol;
import java.util.HashMap;
import java.util.Random;

//CLASE DE PERSONAL DEL HOTEL
public class Personal extends Usuario{

    public Personal(String username, String passwordHash, Rol tipoIngreso, String nombreApellido) {
        super(username, passwordHash, tipoIngreso, nombreApellido);
        this.setTipoIngreso(Rol.PERSONAL_LIMPIEZA);
    }

    public Personal() {
        this.setTipoIngreso(Rol.PERSONAL_LIMPIEZA);
    }

    //METODO PARA LIMPIAR HABITACION
    public void limpiarHabitacion(Habitacion habitacion){
        habitacion.setLimpia(true);
    }
    //METODO PARA REPARAR HABITACION
    public void repararHabitacion(Habitacion habitacion){
        habitacion.setReparacion(false);
    }
    //METODO PARA HACER EL CHECKIN DE LA RESERVA
    public String hacerCheckIn(Reserva reserva, Hotel hotel) throws HabitacionNoDisponibleException {
        // Obtener las reservas actuales del hotel
        HashMap<Integer, Reserva> reservas = hotel.getReservas();

        // Verificar si existe la reserva para la habitación
        if (reservas.containsKey(reserva.getHabitacion().getNumero())) {
            for (Habitacion habitacion : hotel.getHabitaciones()) {
                if (habitacion.getNumero() == reserva.getHabitacion().getNumero()) {
                    habitacion.setEstado(Estado.OCUPADO);
                    break;
                }
            }

            reservas.remove(reserva.getHabitacion().getNumero());

            hotel.setReservas(new HashMap<>(reservas));

            return "Check In realizado con éxito.";
        } else {
            throw new HabitacionNoDisponibleException("Esta habitación no tiene una reserva activa.");
        }
    }
    //METODO PARA HACER EL CHECKOUT DE LA RESERVA
    public void hacerCheckOut(Habitacion habitacion){

        habitacion.setEstado(Estado.DISPONIBLE);
        //Este metodo implementa un random de boolean, para que sea aleatoria la necesidad de reparacion.
        Random rand = new Random();
        habitacion.setReparacion(rand.nextBoolean());
        habitacion.setLimpia(false);
    }

}
