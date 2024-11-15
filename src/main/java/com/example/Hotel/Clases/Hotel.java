package com.example.Hotel.Clases;
import java.lang.reflect.Array;
import java.util.*;

import com.example.Hotel.Clases.enumeradores.Estado;
import com.example.Login.Clases.Usuario;


public class Hotel {
    private String nombre;
    private String direccion;
    private ArrayList<Habitacion> habitaciones;
    private HashMap<Integer,Reserva> reservas;
    private TreeSet<Usuario> usuarios;

    public Hotel(){}

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
        reservas.put(reserva.getHabitacion().getNumero(),reserva);
    }

    public void cancerlarReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    public List<Habitacion> obtenerHabitacionesDisponibles() {
        List<Habitacion> habitacionesDisponibles = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getEstado().equals(Estado.DISPONIBLE)) {
                habitacionesDisponibles.add(habitacion);
            }
        }
        return habitacionesDisponibles;
    }

    public List<Habitacion> obtenerHabitacionesNoDisponibles() {
        List<Habitacion> habitacionesNoDisponibles = new ArrayList<>();
        for (Map.Entry<Integer, Reserva> entrada : reservas.entrySet()) {
            Reserva reserva = entrada.getValue();
            if (reserva.getHabitacion().getEstado().equals(Estado.OCUPADO)) {
                habitacionesNoDisponibles.add(reserva.getHabitacion());
            }
        }
        return habitacionesNoDisponibles;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public Map<Integer, Reserva> getReservas() {
        return reservas;
    }

    public TreeSet getUsuarios() {
        return usuarios;
    }
}

