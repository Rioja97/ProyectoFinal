package com.example.Hotel.Clases;
import com.example.Hotel.Clases.enumeradores.Estado;

import java.util.ArrayList;
import java.util.List;

import static com.example.Hotel.Clases.enumeradores.Estado.DISPONIBLE;

public class Habitacion {
    private int numero;
    private String tipo;
    private Estado estado;
    private double precio;
    private boolean limpia;
    private boolean reparacion;

    public Habitacion(int numero, String tipo, double precio) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
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
