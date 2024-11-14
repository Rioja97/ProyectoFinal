package com.example.Hotel.Clases;
import java.time.LocalDate;
import java.util.Date;

import com.example.Personas.Clases.Pasajero.Pasajero;
import com.example.Personas.Clases.Pasajero.PasajeroManager;

public class Reserva {
    private Pasajero pasajero;
    private Date fechaInicio;
    private Date fechaFin;
    private double precio;
    private String estado;

    public Reserva(Pasajero pasajero, Date fechaInicio, Date fechaFin, double precio) {
        this.pasajero = pasajero;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precio = precio;
        this.estado = "pendiente";
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
