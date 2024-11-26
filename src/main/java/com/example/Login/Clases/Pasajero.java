package com.example.Login.Clases;

import com.example.Login.Enums.Rol;

import java.util.Objects;

public class Pasajero extends Usuario {

    private int dni;
    private String direccion;
    private String nacionalidad;

    public Pasajero(String username, String password, Rol tipoIngreso, String nombreApellido) {
        super(username, password, tipoIngreso, nombreApellido);

    }

    public Pasajero(int dni, String direccion, String nacionalidad) {
        this.dni = dni;
        this.direccion = direccion;
        this.nacionalidad = nacionalidad;
    }

    public Pasajero() {
        super();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pasajero pasajero = (Pasajero) o;
        return dni == pasajero.dni && Objects.equals(direccion, pasajero.direccion) && Objects.equals(nacionalidad, pasajero.nacionalidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dni, direccion, nacionalidad);
    }

    @Override
    public String toString() {
        return "Pasajero: " +
                "Dni = " + dni + "\n" +
                ", Direccion = '" + direccion + "\n" +
                ", Nacionalidad='" + nacionalidad + "\n"
                + super.toString();
    }
}

