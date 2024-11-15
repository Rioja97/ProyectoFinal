package com.example.Utils;

public class Consumo {
    private String descripcion;
    private double monto;

    public Consumo(String descripcion, double monto) {
        this.descripcion = descripcion;
        this.monto = monto;
    }

    @Override
    public String toString() {
        return descripcion + " - Monto: $" + monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}