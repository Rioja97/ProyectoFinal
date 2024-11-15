package com.example.Menu;

import com.example.Login.Clases.LoginManager;
import com.example.Login.Clases.Usuario;
import com.example.Login.Enums.Rol;
import com.example.Login.Exceptions.UsuarioRepetidoException;
import com.sun.tools.jdeprscan.scan.Scan;
import java.rmi.NoSuchObjectException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeSet;

public final class Menu {

    public Menu(){}

    public static String agregarUsuarioAdmin(Usuario usuario, TreeSet<Usuario>listaUsuarios){
        LoginManager gestorLogin = new LoginManager();
        StringBuilder exito = new StringBuilder();

        try{
            exito.append(gestorLogin.agregarUsuario(usuario,listaUsuarios));
        } catch (UsuarioRepetidoException e) {
            e.printStackTrace();
        }

        return exito.toString();
    }

    public static String modificarUsuarioAdmin(Usuario usuario, String newPassword, Rol newRol, String nombreApellido, TreeSet<Usuario>listaUsuarios){
        LoginManager gestorLogin = new LoginManager();
        StringBuilder exito = new StringBuilder();

        try{
            exito.append(gestorLogin.modificarUsuario(usuario,newPassword,newRol,nombreApellido,listaUsuarios));
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
        return exito.toString();
    }

    public static String eliminarUsuarioAdmin(Usuario usuario,TreeSet<Usuario>listaUsuarios){
        LoginManager gestorLogin = new LoginManager();
        StringBuilder exito = new StringBuilder();

        try{
            exito.append(gestorLogin.eliminarUsuario(usuario,listaUsuarios));
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
        return exito.toString();
    }

}

