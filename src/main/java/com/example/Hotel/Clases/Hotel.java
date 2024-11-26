package com.example.Hotel.Clases;
import java.util.*;

import com.example.Hotel.Enum.Estado;
import com.example.Interfaces.MetodosUsuarios;
import com.example.Login.Clases.LoginManager;
import com.example.Login.Clases.Usuario;


public class Hotel implements MetodosUsuarios{
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

    @Override
    public void modificarUsuario(Usuario usuario,String username){

        LoginManager login = new LoginManager();
        login.modificarUsuario(usuario,username);
    }

    @Override
    public void agregarUsuario(Usuario usuario){

        LoginManager login = new LoginManager();
        login.agregarUsuario(usuario);
    }

    @Override
    public void eliminarUsuario(String idUsuario) {

        LoginManager login = new LoginManager();
        login.eliminarUsuario(idUsuario);
    }

    public void agregarHabitacion(Habitacion habitacion) {

        habitaciones.add(habitacion);
    }

    public void eliminarHabitacion(int habitacion){

        for(Habitacion h: habitaciones){
            if(h.getNumero() == habitacion){
                habitaciones.remove(h);
            }
        }
    }

    public void realizarReserva(Reserva reserva) {

        reserva.getHabitacion().setEstado(Estado.RESERVADO);
        reservas.put(reserva.getHabitacion().getNumero(),reserva);
    }

    public void cancerlarReserva(Reserva reserva) {

        reserva.getHabitacion().setEstado(Estado.DISPONIBLE);
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

    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public void setReservas(HashMap<Integer, Reserva> reservas) {
        this.reservas = reservas;
    }

    public void setUsuarios(TreeSet<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}

