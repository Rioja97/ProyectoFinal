package com.example.Personas.Clases.Pasajero;

import com.example.Hotel.Clases.Reserva;
import com.example.Login.Clases.Usuario;
import com.example.Login.Enums.Rol;

import java.util.Objects;
import java.util.TreeSet;

public class Pasajero extends Usuario {

    private int dni;
    private String direccion;
    private String nacionalidad;
    private TreeSet<Reserva> historialReservas;

    public Pasajero(String username, String passwordHash, Rol tipoIngreso, String nombreApellido, int dni, String direccion, String nacionalidad, TreeSet<Reserva> historialReservas) {
        super(username, passwordHash, tipoIngreso, nombreApellido);
        this.dni = dni;
        this.direccion = direccion;
        this.nacionalidad = nacionalidad;
        this.historialReservas = new TreeSet<>();
    }

    public Pasajero() {
        this.historialReservas = new TreeSet<>();
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public TreeSet<Reserva> getHistorialReservas() {
        return historialReservas;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pasajero pasajero = (Pasajero) o;
        return dni == pasajero.dni && Objects.equals(direccion, pasajero.direccion) && Objects.equals(nacionalidad, pasajero.nacionalidad) && Objects.equals(historialReservas, pasajero.historialReservas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dni, direccion, nacionalidad, historialReservas);
    }

    @Override
    public String toString() {
        return "Pasajero:" +
                "Dni = " + dni + + '\n' +
                ", Direccion = '" + direccion + '\n' +
                ", Nacionalidad = '" + nacionalidad + '\n' +
                ", Historial de Reservas = " + historialReservas;
    }
}
