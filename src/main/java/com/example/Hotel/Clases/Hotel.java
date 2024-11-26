package com.example.Hotel.Clases;
import java.util.*;

import com.example.Excepciones.HabitacionNoDisponibleException;
import com.example.Hotel.Enum.Estado;
import com.example.Login.Clases.Administrador;
import com.example.Login.Clases.Usuario;
import com.example.Utils.JsonManager;


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
                throw new HabitacionNoDisponibleException("Esta habitacion ya existe");
            }
        }
        habitaciones.add(habitacion);
        return "Se ha agregado la habitacion correctamente";
    }


    public String modificarHabitacion(int numeroHabitacion, Habitacion habitacion) {

        for(Habitacion h: habitaciones){
            if(numeroHabitacion == h.getNumero()){
                habitaciones.remove(h);
                habitaciones.add(habitacion);
                return "Se ha modificado la habitacion correctamente";
            }
        }
        throw new NoSuchElementException("No se encontro la habitación");
    }


    public String eliminarHabitacion(int habitacion){


        for(Habitacion h: habitaciones){
            if(h.getNumero() == habitacion){
                habitaciones.remove(h);
                return "Se ha eliminado la habitacion correctamente";
            }
        }
        throw new NoSuchElementException("No se encontro la habitación");
    }


    //Revisar que no esta agregando la reserva al map
    public String realizarReserva(Reserva reserva) throws HabitacionNoDisponibleException {

        // Verificar si la habitación ya está reservada u ocupada.
        if (reserva.getHabitacion().getEstado() == Estado.RESERVADO ||
                reserva.getHabitacion().getEstado() == Estado.OCUPADO) {
            throw new HabitacionNoDisponibleException("No se puede realizar la reserva. Habitación no disponible.");
        }

        // Cambiar el estado de la habitación a "RESERVADO"
        reserva.getHabitacion().setEstado(Estado.RESERVADO);

        // Comprobar si ya existe una reserva para la misma habitación.
        if (reservas.containsKey(reserva.getHabitacion().getNumero())) {
            throw new HabitacionNoDisponibleException("Ya existe una reserva para esta habitación.");
        }

        // Registrar la reserva en el mapa de reservas.
        reservas.put(reserva.getHabitacion().getNumero(), reserva);

        return "Se ha realizado la reserva correctamente.";
    }

    //Este metodo tiene que quitar la reserva del map, y cambiar el estado de esa habitacion a ocupada.
    public String hacerCheckIn(Reserva reserva)throws HabitacionNoDisponibleException{

        for(Reserva reserva1 : reservas.values()){
            if(reserva1.getHabitacion().getNumero() == reserva.getHabitacion().getNumero()){
                reserva1.getHabitacion().setEstado(Estado.OCUPADO);
                reservas.remove(reserva.getHabitacion().getNumero());
            }else{
                throw new HabitacionNoDisponibleException("Esta habitacion no existe.");
            }

        }
        return "Check In realizado con exito.";
    }

    //Revisar que no esta quitando la reserva al map
    public String cancerlarReserva(int numeroHabitacion, Hotel hotel) {

        JsonManager.encontrarHabitacionHotel(numeroHabitacion, hotel);

        for(Reserva reserva: hotel.getReservas().values()){
            if(reserva.getHabitacion().getEstado() == Estado.RESERVADO && reserva.getHabitacion().getNumero() == numeroHabitacion){
                reserva.getHabitacion().setEstado(Estado.DISPONIBLE);
                reservas.remove(reserva.getHabitacion().getNumero());
            }
        }
        hotel.setReservas(reservas);
        return "Se ha cancelado la reserva correctamente";
    }


    public List<Habitacion> obtenerHabitacionesDisponibles() {
        List<Habitacion> habitacionesDisponibles = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getEstado().name().equals("DISPONIBLE")) {
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

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public HashMap<Integer, Reserva> getReservas() {
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

