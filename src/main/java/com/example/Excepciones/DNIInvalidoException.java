package com.example.Excepciones;

public class DNIInvalidoException extends Exception {
    public DNIInvalidoException(String mensaje) {
        super(mensaje);
    }
}
