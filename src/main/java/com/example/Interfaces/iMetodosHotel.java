package com.example.Interfaces;

import com.example.Login.Clases.Usuario;

public interface iMetodosHotel {
    String agregarUsuario(Usuario usuario);
    String modificarUsuario(Usuario usuario, String username);
    String eliminarUsuario(String idUsuario);
}
