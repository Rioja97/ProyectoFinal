package com.example.Login.Clases;

import com.example.Hotel.Clases.Hotel;
import com.example.Login.Enums.Rol;
import com.example.Login.LoginExceptions.UsuarioRepetidoException;
import java.util.NoSuchElementException;
import java.util.TreeSet;
//CLASE DE ADMINISTRADOR
public class Administrador extends Usuario {


    public Administrador(String username, String passwordHash, Rol tipoIngreso, String nombreApellido) {
        super(username, passwordHash, tipoIngreso, nombreApellido);
    }

    public Administrador() {
    }

    //Metodo para agregar usuario a la coleccion del hotel
    public String agregarUsuario(Usuario usuario, Hotel hotel) throws UsuarioRepetidoException {

        TreeSet<Usuario> listaUsuarios = new TreeSet<>(hotel.getUsuarios());

        if(listaUsuarios.add(usuario)){
            hotel.setUsuarios(listaUsuarios);
            return "Se ha agregado el usuario exitosamente";
        };

        throw new UsuarioRepetidoException("Usuario ya existe");

    }

    //Metodo para modificar usuario de la coleccion del hotel
    public String modificarUsuario(Usuario modificado, String nombreUsuario, Hotel hotel) throws UsuarioRepetidoException{

        TreeSet<Usuario> listaUsuarios = new TreeSet<>(hotel.getUsuarios());

        for (Usuario u: listaUsuarios) {

            if (u.getUsername().equals(nombreUsuario)) {
                listaUsuarios.remove(u);
                listaUsuarios.add(modificado);

                hotel.setUsuarios(listaUsuarios);
                return "Se ha modificado el usuario exitosamente";
            }

        }
        throw new NoSuchElementException("Usuario no encontrado");
    }

    //Metodo para eliminar usuario de la coleccion del hotel
    public String eliminarUsuario(String nombreUsuario, Hotel hotel){

        TreeSet<Usuario> listaUsuarios = new TreeSet<>(hotel.getUsuarios());

        for (Usuario u: listaUsuarios){
            if(u.getUsername().equals(nombreUsuario)){
                listaUsuarios.remove(u);
                hotel.setUsuarios(listaUsuarios);

                return "Se ha eliminado el usuario exitosamente";
            }
        }
        throw new NoSuchElementException("Usuario no encontrado");
    }

}
