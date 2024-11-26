package com.example.Login.Clases;

import com.example.Login.Enums.Rol;

public class Administrador extends Usuario {



    public Administrador(String username, String passwordHash, Rol tipoIngreso, String nombreApellido) {
        super(username, passwordHash, tipoIngreso, nombreApellido);
    }

    public Administrador() {
    }

    public void crearUsuario(Usuario usuario){
        LoginManager gestorLogin = new LoginManager();
        gestorLogin.agregarUsuario(usuario);
    }

    public void modificarUsuario(Usuario usuario,String username){
        LoginManager gestorLogin = new LoginManager();

        gestorLogin.modificarUsuario(usuario,username);
    }

    public void eliminarUsuario(String idUsuario){
        LoginManager gestorLogin = new LoginManager();

        gestorLogin.eliminarUsuario(idUsuario);
    }
}
