package com.example.Hotel.Clases;

import java.util.*;
import com.example.Excepciones.HabitacionNoDisponibleException;
import com.example.Hotel.Enum.Estado;
import com.example.Login.Clases.Administrador;
import com.example.Login.Clases.Pasajero;
import com.example.Login.Clases.Usuario;

//CLASE HOTEL. CON RESPECTIVOS ATRIBUTOS DE HOTEL Y LAS COLECCIONES CORRESPONDIENTES.

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


    //ACA SE IMPLEMENTÓ LA SOBRECARGA DE MÉTODOS.
    //LLAMA A AGREGAR USUARIO DE ADMINISTRADOR. UTILIZADO PARA AGREGAR ADMIN O PERSONAL
    public String agregarUsuario(Usuario usuario, Administrador admin, Hotel hotel){

        return admin.agregarUsuario(usuario, hotel);
    }

    //LLAMA A AGREGAR USUARIO DE ADMINISTRADOR. UTILIZADO PARA REGISTRO DE PASAJEROS.
    public String agregarUsuario(Usuario usuario, Hotel hotel){

        Administrador admin = new Administrador();
        return admin.agregarUsuario(usuario, hotel);
    }

    //LLAMA A MODIFICAR USUARIO DE ADMINISTRADOR
    public String modificarUsuario(Usuario usuario,String username, Administrador admin, Hotel hotel){

        return admin.modificarUsuario(usuario,username, hotel);
    }


    //LLAMA A ELIMINAR USUARIO DE ADMINISTRADOR
    public String eliminarUsuario(String idUsuario, Administrador admin, Hotel hotel) {

        return admin.eliminarUsuario(idUsuario, hotel);
    }

    //METODO PARA AGREGAR HABITACION
    public String agregarHabitacion(Habitacion habitacion) throws HabitacionNoDisponibleException{

        for (Habitacion h : habitaciones) {
            if(h.getNumero() == habitacion.getNumero()) {
                throw new HabitacionNoDisponibleException("Esta habitacion ya existe");
            }
        }
        habitaciones.add(habitacion);
        return "Se ha agregado la habitacion correctamente";
    }

    //METODO PARA MODIFICAR HABITACION
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

    //METODO PARA ELIMINAR HABITACION
    public String eliminarHabitacion(int habitacion){

        for(Habitacion h: habitaciones){
            if(h.getNumero() == habitacion){
                habitaciones.remove(h);
                return "Se ha eliminado la habitacion correctamente";
            }
        }
        throw new NoSuchElementException("No se encontro la habitación");
    }


    //METODO PARA AGREGAR LA RESERVA AL MAP
    public void realizarReserva(Reserva reserva) throws HabitacionNoDisponibleException {

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

    }

    //METODO PARA CANCELAR LA RESERVA
    public String cancelarReserva(int numeroHabitacion, Hotel hotel) {
        // Obtener el mapa de reservas
        HashMap<Integer, Reserva> reservas = hotel.getReservas();

        // Verificar si la reserva existe
        if (reservas.containsKey(numeroHabitacion)) {
            Reserva reserva = reservas.remove(numeroHabitacion);
            // Cambiar el estado de la habitación a disponible
            reserva.getHabitacion().setEstado(Estado.DISPONIBLE);

            // Actualizar el mapa en el hotel
            hotel.setReservas(reservas);

            return "Se ha cancelado la reserva correctamente para la habitación " + numeroHabitacion;
        } else {
            return "No se encontró una reserva para la habitación " + numeroHabitacion;
        }
    }

    //METODO PARA MOSTRAR LAS RESERVAS QUE TIENE EL PASAJERO
    public ArrayList filtrarReservasPasajero(Pasajero pasajero){

        ArrayList<Reserva> filtradas = new ArrayList<>();

        for (Reserva reserva: reservas.values()){
            if (reserva.getPasajero().getUsername().equals(pasajero.getUsername())){
                filtradas.add(reserva);
            }
        }
        if(filtradas.isEmpty()){
            throw new NoSuchElementException("El pasajero no tiene reservas");
        }
        return filtradas;
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


    //GETTERS Y SETTERS
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

