package com.example.Utils;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;
import com.example.Excepciones.*;
import com.example.Hotel.Clases.Habitacion;
import com.example.Hotel.Clases.Reserva;
import com.example.Hotel.Enum.Estado;
import com.example.Login.Clases.Usuario;
import com.example.Login.Clases.Administrador;


public class Validaciones{

    public static void validarNombre(String nombre) throws NombreInvalidoException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NombreInvalidoException("El nombre no puede estar vacío.");
        }
        if (nombre.length() < 3 || nombre.length() > 50) {
            throw new NombreInvalidoException("El nombre debe tener entre 3 y 50 caracteres.");
        }
        if(!(nombre.matches("^[a-zA-Z\\s]+$"))){
            throw new NombreInvalidoException("Solo se permiten letras.");
        }
    }
    public static void validarDNI(String dni) throws DNIInvalidoException {
        if (dni == null || dni.trim().isEmpty()) {
            throw new DNIInvalidoException("El DNI no puede estar vacío.");
        }
        String regex = "^[0-9]{8}$";
        if (!Pattern.matches(regex, dni)) {
            throw new DNIInvalidoException("El DNI debe tener exactamente 8 dígitos.");
        }
    }
    public static void validarDomicilio(String domicilio) throws DomicilioInvalidoException {
        if (domicilio == null || domicilio.trim().isEmpty()) {
            throw new DomicilioInvalidoException("El domicilio no puede estar vacío.");
        }
        if (domicilio.length() < 5 || domicilio.length() > 100) {
            throw new DomicilioInvalidoException("El domicilio debe tener entre 5 y 100 caracteres.");
        }
    }
    public static void validarFechaInicio(LocalDate fechaInicio) throws FechaInvalidaException {
        if (fechaInicio == null) {
            throw new FechaInvalidaException("La fecha de inicio no puede ser nula.");
        }
        if (fechaInicio.isBefore(LocalDate.now())) {
            throw new FechaInvalidaException("La fecha de inicio no puede ser anterior a la fecha actual.");
        }
    }
    public static void validarFechaFin(LocalDate fechaInicio, LocalDate fechaFin) throws FechaInvalidaException {
        if (fechaFin == null) {
            throw new FechaInvalidaException("La fecha de fin no puede ser nula.");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new FechaInvalidaException("La fecha de fin debe ser posterior a la fecha de inicio.");
        }
    }
    public static void validarDisponibilidadReserva(List<Reserva> reservas, LocalDate fechaInicio, LocalDate fechaFin) throws ReservaSolapadaException {
        for (Reserva reserva : reservas) {
            if (reserva.getHabitacion().getEstado().equals("Disponible")) {
                if ((fechaInicio.isBefore(reserva.getFechaFin()) && fechaInicio.isAfter(reserva.getFechaInicio())) ||
                        (fechaFin.isAfter(reserva.getFechaInicio()) && fechaFin.isBefore(reserva.getFechaFin())) ||
                        fechaInicio.isEqual(reserva.getFechaInicio()) ||
                        fechaFin.isEqual(reserva.getFechaFin())) {
                    throw new ReservaSolapadaException("La habitación ya está reservada para esas fechas.");
                }
            }
        }
    }
    public static void validarHabitacionDisponible(Habitacion habitacion) throws HabitacionNoDisponibleException {
        if (habitacion == null) {
            throw new HabitacionNoDisponibleException("La habitación no puede ser nula.");
        }
        if (!habitacion.getEstado().equals(Estado.OCUPADO) && !habitacion.getEstado().equals(Estado.RESERVADO)) {
            throw new HabitacionNoDisponibleException("La habitación no está disponible.");
        }
    }

    public static void verificarPermisoUsuario(Usuario usuario, String accion) throws PermisoNoPermitidoException {
        if (usuario == null) {
            throw new PermisoNoPermitidoException("El usuario no está registrado.");
        }
        if (accion == null || accion.trim().isEmpty()) {
            throw new PermisoNoPermitidoException("La acción no puede ser nula o vacía.");
        }
        if (usuario instanceof Administrador && accion.equalsIgnoreCase("realizar check-in")) {
            throw new PermisoNoPermitidoException("El administrador no tiene permiso para hacer check-in.");
        }
    }
}


