package com.example.Hotel.Clases;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.SplittableRandom;

import com.example.Personas.Clases.Pasajero.Pasajero;
import com.example.Utils.Consumo;

public class Reserva {
    private Pasajero pasajero;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Habitacion habitacion;
    private Map<Integer,String> consumos;

    public Reserva(){}

    public Reserva(Pasajero pasajero, LocalDate fechaInicio, LocalDate fechaFin, Habitacion habitacion) {
        this.pasajero = pasajero;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.habitacion = habitacion;
    }

    public long calcularDiasEstancia() {
        return (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }

    public void registrarConsumo(String descripcion,int monto) {
        consumos.put(monto,descripcion);
    }

    public int calcularConsumos(){
        return consumos.keySet().stream().mapToInt(Integer::intValue).sum();
    }

    public double precioFinalConsumo(){
        return getHabitacion().getPrecio()*(calcularDiasEstancia())+calcularConsumos();
    }

    public double precioFinalSinConsumo(){
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

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "pasajero=" + pasajero +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", habitacion=" + habitacion +
                '}';
    }
}
