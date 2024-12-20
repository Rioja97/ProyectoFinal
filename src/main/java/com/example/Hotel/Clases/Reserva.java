package com.example.Hotel.Clases;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import com.example.Login.Clases.Pasajero;

//CLASE DE RESERVAS
public class Reserva {
    private Pasajero pasajero;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Habitacion habitacion;
    private Map<Integer,String> consumos;

//UNICO Y NECESARIO CONSTRUCTOR.
    public Reserva(Pasajero pasajero, LocalDate fechaInicio, LocalDate fechaFin, Habitacion habitacion) {
        this.pasajero = pasajero;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.habitacion = habitacion;
    }

    //METODO PARA CALCULAR LOS DIAS DE LA RESERVA
    public int calcularDiasEstancia() {
        return (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }
    //METODO PARA REGISTRAR CONSUMO
    public void registrarConsumo(String descripcion,int monto) {
        consumos.put(monto,descripcion);
    }
    //METODO PARA CALCULAR CONSUMOS
    public int calcularConsumos(){
        return consumos.keySet().stream().mapToInt(Integer::intValue).sum();
    }

    public double precioFinal(){
        return getHabitacion().getPrecio()*(calcularDiasEstancia());
    }


    //GETTERS Y SETTERS
    public Pasajero getPasajero() {
        return pasajero;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Habitacion getHabitacion(){
        return habitacion;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Map<Integer, String> getConsumos() {
        return consumos;
    }

    public void setConsumos(Map<Integer, String> consumos) {
        this.consumos = consumos;
    }

    @Override
    public String toString() {
        return "Reserva:" +
                "Pasajero = " + pasajero + "\n" +
                ", Fecha de Inicio = " + fechaInicio + "\n" +
                ", Fecha Fin = " + fechaFin + "\n" +
                ", Habitacion = " + habitacion + "\n";
    }
}
