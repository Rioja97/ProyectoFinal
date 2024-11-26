package com.example.Interfaces;

import com.example.Hotel.Clases.Habitacion;
import com.example.Hotel.Clases.Reserva;

public interface MetodosPersonal {

    void hacerCheckIn(Reserva reserva);
    void hacerCheckOut(Habitacion habitacion);
    void limpiarHabitacion(Habitacion habitacion);
    void repararHabitacion(Habitacion habitacion);
}
