package com.example.Hotel.Clases;
import java.util.*;

import com.example.Login.Clases.Usuario;


public class Hotel {
    private String nombre;
    private String direccion;
    private List<Habitacion> habitaciones;
    private Map<String,Reserva> reservas;
    private TreeSet usuarios;

    public Hotel(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.habitaciones = new ArrayList<>();
        this.reservas = new HashMap<>();
        this.usuarios = new TreeSet<>();
    }

    public void agregarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public void eliminarUsuario(String idUsuario) {
        usuarios.remove(idUsuario);
    }

    public void agregarHabitacion(Habitacion habitacion) {
        habitaciones.add(habitacion);
    }

    public void eliminarHabitacion(Habitacion habitacion){
        habitaciones.remove(habitacion);
    }

    public void realizarReserva(Reserva reserva) {
        reservas.put();
    }

    public void cancerlarReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    public Map<String, String> obtenerEstadoHabitaciones() {
        Map<String, String> estados = new HashMap<>();
        for (Habitacion habitacion : habitaciones) {
            estados.put("Habitación " + habitacion.getNumero(), habitacion.getEstado());
        }
        return estados;
    }


}

