package com.example.Hotel.Clases;
import java.util.ArrayList;
import java.util.List;

public class Habitacion {
    private int numero;
    private String tipo;
    private String estado;
    private double precioPorNoche;
    private boolean limpia;
    private boolean reparacion;
    private List<Reserva> historialReservas;

    public Habitacion(int numero, String tipo, double precioPorNoche) {
        this.numero = numero;
        this.tipo = tipo;
        this.estado = "disponible";
        this.precioPorNoche = precioPorNoche;
        this.limpia=true;
        this.reparacion=false;
        this.historialReservas = new ArrayList<>();
    }

    public void agregarReserva(Reserva reserva) {
        historialReservas.add(reserva);
    }

    public List<Reserva> mostrarHistorialReservas() {
        return historialReservas;
    }

    public void actualizarEstadoReserva(Reserva reserva, String nuevoEstado) {
        reserva.setEstado(nuevoEstado);
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(double precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }
}
