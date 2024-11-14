package com.example.Hotel.Clases;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.example.Personas.Clases.Pasajero.Pasajero;

public class Reserva {
    private Pasajero pasajero;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Habitacion habitacion;

    public Reserva(Pasajero pasajero, LocalDate fechaInicio, LocalDate fechaFin, double precio, String estado, Habitacion habitacion) {
        this.pasajero = pasajero;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.habitacion = habitacion;
    }

    public long calcularDiasEstancia() {
        return (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }

    public double precioFinal(){
        return getHabitacion().getPrecio()*(calcularDiasEstancia());
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Habitacion getHabitacion(){
        return habitacion;
    }

}
