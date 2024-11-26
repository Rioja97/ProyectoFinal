package com.example.Interfaces;

import com.example.Login.Clases.Usuario;

public interface MetodosUsuarios {
    void agregarUsuario(Usuario usuario);
    void modificarUsuario(Usuario usuario, String username);
    void eliminarUsuario(String idUsuario);
}
