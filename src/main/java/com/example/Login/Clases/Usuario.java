package com.example.Login.Clases;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import com.example.Login.Enums.Rol;
import com.example.Personas.Clases.Administrador.Administrador;
import com.example.Personas.Clases.Pasajero.Pasajero;
import com.example.Personas.Clases.Personal.Personal;
import org.json.JSONObject;

public class Usuario implements Comparable<Usuario>{

    //Atributos
    private String username;
    private String password;
    private Rol tipoIngreso;
    private String nombreApellido;


    //Constructor con parámetros

    public Usuario(String username, String password, Rol tipoIngreso, String nombreApellido) {
        this.username = username;
        this.password = password;
        this.tipoIngreso = tipoIngreso;
        this.nombreApellido = nombreApellido;
    }

    public Usuario(String nombreApellido){
        this.nombreApellido = nombreApellido;
    }


    //Constructor sin parametros para creacion de pasajero
    public Usuario() {
        this.tipoIngreso = Rol.CLIENTE;
    }


    //GETTERS
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public Rol getTipoIngreso() {return tipoIngreso;}
    public String getNombreApellido() {return nombreApellido;}

    //SETTERS

    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setTipoIngreso(Rol tipoIngreso) {tipoIngreso = tipoIngreso;}
    public void setNombreApellido(String nombreApellido) {this.nombreApellido = nombreApellido;}

    // Metodo equals para username y tipoIngreso

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(username, usuario.username) && Objects.equals(password, usuario.password) && tipoIngreso == usuario.tipoIngreso && Objects.equals(nombreApellido, usuario.nombreApellido);
    }

    // Metodo hashCode para username y tipoIngreso
    @Override
    public int hashCode() {
        return Objects.hash(username, password, tipoIngreso, nombreApellido);
    }


    public int compareTo(Usuario o) {
        return this.username.compareTo(o.username);
    };

    @Override
    public String toString() {
        return "Usuario:" +
                "Username = '" + username + '\n' +
                ", Password = '" + password + '\n' +
                ", Tipo de Ingreso = " + tipoIngreso + '\n' +
                ", Nombre y Apellido = '" + nombreApellido + "\n\n";
    }
}
