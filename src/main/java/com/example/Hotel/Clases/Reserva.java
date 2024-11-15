package com.example.Hotel.Clases;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;
import java.util.SplittableRandom;

import com.example.Personas.Clases.Pasajero.Pasajero;

public class Reserva {
    private Pasajero pasajero;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Habitacion habitacion;

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

    public double precioFinal(){
        return getHabitacion().getPrecio()*(calcularDiasEstancia());
    }

    public static LocalDate generarFechaAleatoria() {
        Random random = new Random();

        //Rango de años
        int anioInicio = 2024;
        int anioFin = 2030;

        // Generar año, mes y día aleatorios
        int anioAleatorio = anioInicio + random.nextInt(anioFin - anioInicio + 1);
        int mesAleatorio = 11 + random.nextInt(12);
        int diaAleatorio = 15 + random.nextInt(LocalDate.of(anioAleatorio, mesAleatorio, 1).lengthOfMonth());

        return LocalDate.of(anioAleatorio, mesAleatorio, diaAleatorio);
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
