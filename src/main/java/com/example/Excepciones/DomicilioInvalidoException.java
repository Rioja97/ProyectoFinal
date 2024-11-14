package com.example.Excepciones;

public class DomicilioInvalidoException extends Exception {
    public DomicilioInvalidoException(String mensaje) {
        super(mensaje);
    }
}