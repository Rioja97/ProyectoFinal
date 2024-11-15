package com.example.Hotel.Clases;
import com.example.Hotel.Clases.enumeradores.Estado;
import com.example.Hotel.Clases.enumeradores.Tipo;

import java.util.ArrayList;
import java.util.List;

import static com.example.Hotel.Clases.enumeradores.Estado.DISPONIBLE;

public class Habitacion {
    private int numero;
    private Tipo tipo;
    private Estado estado;
    private double precio;
    private boolean limpia;
    private boolean reparacion;

    public Habitacion(){}

    public Habitacion(int numero,Tipo tipo){
        this.numero = numero;
        this.tipo = tipo;
    }

    public Habitacion(int numero, Tipo tipo, Estado estado, double precio,boolean limpia,boolean reparacion) {
        this.numero = numero;
        this.tipo = tipo;
        this.estado = estado;
        this.precio = precio;
        this.limpia= limpia;
        this.reparacion= reparacion;
    }

    public Habitacion(int numero, Tipo tipo, double precio) {
        this.numero = numero;
        this.tipo = tipo;
        this.estado = DISPONIBLE;
        this.precio = precio;
        this.limpia=true;
        this.reparacion=false;
    }

    public void actualizarEstadoReserva(Reserva reserva, Estado nuevoEstado) {
        reserva.getHabitacion().setEstado(nuevoEstado);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isReparacion() {return reparacion;}

    public void setReparacion(boolean reparacion) {this.reparacion = reparacion;}

    public boolean isLimpia() {return limpia;}

    public void setLimpia(boolean limpia) {this.limpia = limpia;}

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecioPorNoche(double precioPorNoche) {
        this.precio = precioPorNoche;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "numero=" + numero +
                ", tipo='" + tipo + '\'' +
                ", estado='" + estado + '\'' +
                ", precio=" + precio +
                ", limpia=" + limpia +
                ", reparacion=" + reparacion +'}';
    }
}
