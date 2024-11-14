package com.example.Excepciones;

public class PermisoNoPermitidoException extends Exception {
    public PermisoNoPermitidoException(String mensaje) {
        super(mensaje);
    }
}