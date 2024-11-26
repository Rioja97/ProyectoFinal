package com.example.Login.LoginExceptions;

public class UsuarioRepetidoException extends RuntimeException {
    public UsuarioRepetidoException(String message) {
        super(message);
    }
}
