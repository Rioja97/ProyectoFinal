package com.example.Hotel.Clases;
import java.time.LocalDate;
import java.util.Date;

import com.example.Personas.Clases.Pasajero.Pasajero;

public class Reserva {
    private Pasajero pasajero;
    private Date fechaInicio;
    private Date fechaFin;
    private double precio;
    private String estado;
    private Habitacion habitacion;

    public Reserva(Pasajero pasajero, Date fechaInicio, Date fechaFin, double precio, String estado, Habitacion habitacion) {
        this.pasajero = pasajero;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precio = precio;
        this.estado = estado;
        this.habitacion = habitacion;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public double getPrecio() {
        return precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
