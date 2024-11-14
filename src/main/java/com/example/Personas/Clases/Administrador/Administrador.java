package com.example.Personas.Clases.Administrador;

import com.example.Hotel.Clases.Hotel;
import com.example.Login.Clases.LoginManager;
import com.example.Login.Clases.Usuario;
import com.example.Login.Enums.Rol;

import java.util.TreeSet;

public class Administrador extends Usuario {



    public Administrador(String username, String passwordHash, Rol tipoIngreso, String nombreApellido) {
        super(username, passwordHash, tipoIngreso, nombreApellido);
    }

    public Administrador() {
    }

    public void crearUsuario(Usuario usuario, Hotel hotel){
        LoginManager.agregarUsuario(usuario, hotel.getUsuarios());
    }

    public void modificarUsuario(Usuario usuario,  String newPassword, Rol newRol, String nombreApellidoNuevo, char sexo, Hotel hotel){
        LoginManager.modificarUsuario(usuario, newPassword, newRol, nombreApellidoNuevo, sexo, hotel.getUsuarios());
    }

    public void eliminarUsuario(Usuario usuario, Hotel hotel){
        LoginManager.eliminarUsuario(usuario, hotel.getUsuarios());
    }
}
