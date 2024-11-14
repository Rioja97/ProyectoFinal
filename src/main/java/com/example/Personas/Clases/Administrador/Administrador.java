package com.example.Personas.Clases.Administrador;

import com.example.Login.Clases.LoginManager;
import com.example.Login.Clases.Usuario;
import com.example.Login.Enums.Rol;

import java.util.TreeSet;

public class Administrador extends Usuario {

    private TreeSet<Usuario> listaUsuarios;

    public Administrador(String username, String passwordHash, Rol tipoIngreso, String nombreApellido, TreeSet<Usuario> listaUsuarios) {
        super(username, passwordHash, tipoIngreso, nombreApellido);
        this.listaUsuarios = listaUsuarios;
    }

    public Administrador() {
        this.listaUsuarios = new TreeSet<>();
    }

    public void crearUsuario(Usuario usuario){
        LoginManager.agregarUsuario(usuario, listaUsuarios);
    }

    public void modificarUsuario(Usuario usuario,  String newPassword, Rol newRol, String nombreApellidoNuevo, char sexo){
        LoginManager.modificarUsuario(usuario, newPassword, newRol, nombreApellidoNuevo, sexo, listaUsuarios);
    }

    public void eliminarUsuario(Usuario usuario){
        LoginManager.eliminarUsuario(usuario, listaUsuarios);
    }

    public TreeSet<Usuario> getListaUsuarios() {return listaUsuarios;}
}
