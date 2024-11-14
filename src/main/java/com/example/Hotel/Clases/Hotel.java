package com.example.Hotel.Clases;
import java.lang.reflect.Array;
import java.util.*;

import com.example.Hotel.Clases.enumeradores.Estado;
import com.example.Login.Clases.Usuario;


public class Hotel {
    private String nombre;
    private String direccion;
    private ArrayList<Habitacion> habitaciones;
    private HashMap<Habitacion,Reserva> reservas;
    private TreeSet<Usuario> usuarios;

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
        reservas.put(reserva.getHabitacion(),reserva);
    }

    public void cancerlarReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    public void verHabitacionesDisponibles() {
        for (Habitacion habitacion : habitaciones) {
            if(habitacion.getEstado().equals(Estado.DISPONIBLE)){
                System.out.println(habitacion.toString());
            };
        }
    }

    public void verHabitacionesNoDisponibles() {
        for (Map.Entry<Habitacion, Reserva> entrada : reservas.entrySet()) {
            Reserva reserva = entrada.getValue();
            Habitacion habitacion = entrada.getKey();

            if (habitacion.getEstado().equals(Estado.OCUPADO)) {
                System.out.println(habitacion.toString());
                System.out.println("Información pasajero: ");
                System.out.println(reserva.getPasajero().toString());
            }
        }
    }

    public void cancelarReserva(Reserva x){
        reservas.remove(x.getHabitacion(),x);
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

    public Map<Habitacion, Reserva> getReservas() {
        return reservas;
    }

    public TreeSet getUsuarios() {
        return usuarios;
    }
}

