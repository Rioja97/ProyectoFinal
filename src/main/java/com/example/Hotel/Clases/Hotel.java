package com.example.Hotel.Clases;
import java.util.*;

import com.example.Excepciones.HabitacionNoDisponibleException;
import com.example.Hotel.Enum.Estado;
import com.example.Interfaces.MetodosUsuarios;
import com.example.Login.Clases.Administrador;
import com.example.Login.Clases.LoginManager;
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


    public String agregarUsuario(Usuario usuario, Administrador admin, Hotel hotel){

        return admin.agregarUsuario(usuario, hotel);
    }

    public String agregarUsuario(Usuario usuario, Hotel hotel){

        Administrador admin = new Administrador();
        return admin.agregarUsuario(usuario, hotel);
    }


    public String modificarUsuario(Usuario usuario,String username, Administrador admin, Hotel hotel){

        return admin.modificarUsuario(usuario,username, hotel);
    }



    public String eliminarUsuario(String idUsuario, Administrador admin, Hotel hotel) {

        return admin.eliminarUsuario(idUsuario, hotel);
    }


    public String agregarHabitacion(Habitacion habitacion) throws HabitacionNoDisponibleException{

        for (Habitacion h : habitaciones) {
            if(h.getNumero() == habitacion.getNumero()) {
                throw new HabitacionNoDisponibleException("La habitacion ya está cargada");
            }
        }
        habitaciones.add(habitacion);
        return "Se ha agregado la habitacion correctamente";
    }


    public String modificarHabitacion(int numeroHabitacion, Habitacion habitacion) {

        boolean encontrado = false;
        for(Habitacion h: habitaciones){
            if(numeroHabitacion == h.getNumero()){
                h = habitacion;
                return "Se ha modificado la habitacion correctamente";
            }
        }
        throw new NoSuchElementException("No se encontro la habitación");
    }


    public String eliminarHabitacion(int habitacion){

        boolean encontrado = false;
        for(Habitacion h: habitaciones){
            if(h.getNumero() == habitacion){
                habitaciones.remove(h);
                encontrado = true;
                return "Se ha eliminado la habitacion correctamente";
            }
        }
        throw new NoSuchElementException("No se encontro la habitación");
    }


    public String realizarReserva(Reserva reserva) {

        if(reserva.getHabitacion().getEstado().name() == "RESERVADO" || reserva.getHabitacion().getEstado().name() == "OCUPADO"){
            throw new NoSuchElementException("No se puede realizar la reserva. Habitación no disponible");
        }
        reserva.getHabitacion().setEstado(Estado.RESERVADO);
        reservas.put(reserva.getHabitacion().getNumero(),reserva);
        return "Se ha realizado la reserva correctamente";
    }


    public String cancerlarReserva(Reserva reserva) {

        reserva.getHabitacion().setEstado(Estado.DISPONIBLE);
        reservas.remove(reserva);
        return "Se ha cancelado la reserva correctamente";
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


    public ArrayList verReservasCliente(String username){
        ArrayList reservasCliente=new ArrayList();
        if(reservas.values().isEmpty()){
            System.out.println("No hay reservas");
            return null;
        }
        for (Reserva reserva:reservas.values()){
            if(reserva.getPasajero().getUsername().equals(username)){
                reservasCliente.add(reserva);
            }
        }
        return reservasCliente;
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

