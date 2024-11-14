package com.example.Excepciones;

public class HabitacionNoDisponibleException extends Exception {
    public HabitacionNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}