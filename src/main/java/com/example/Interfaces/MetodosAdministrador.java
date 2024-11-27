package com.example.Interfaces;

import com.example.Hotel.Clases.Hotel;
import com.example.Login.Clases.Administrador;
import com.example.Login.Clases.Usuario;

public interface MetodosAdministrador {
    String agregarUsuario(Usuario usuario, Administrador admin, Hotel hotel);
    String modificarUsuario(Usuario usuario,String username, Administrador admin, Hotel hotel);
    String eliminarUsuario(String idUsuario, Administrador admin, Hotel hotel);
}
