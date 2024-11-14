package com.example.Login.Clases;

import com.example.Login.Enums.Rol;
import com.example.Login.Exceptions.UsuarioRepetidoException;
import com.example.Utils.JsonManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class LoginManager {

    public LoginManager(){
    }

    // Metodo para pasar un ENUM a String
    public Rol agregarRol(String rol){
        Rol r = null;

        try{
            String rolIngresado = rol.toUpperCase();
            r = Rol.valueOf(rolIngresado);
        }catch (IllegalArgumentException e){
            e.getMessage();
        }
        return r;
    }


    //Metodo para iniciar sesion
    public Usuario iniciarSesion(String username, String password){
        Usuario usuario1 = null;

        TreeSet<Usuario> listaUsuarios = new TreeSet<>();

        //CARGAR TREESET DESDE ARCHIVO
        listaUsuarios = ;

        password = Usuario.hashPassword(password);

        for(Usuario u: listaUsuarios){
            if(u.getUsername().equals(username) && u.getPasswordHash().equals(password)){
                usuario1 = u;
            }else{
                throw new NoSuchElementException("Nombre de usuario o contraseña incorrectos.");
            }
        }
        return usuario1;
    }


    public static String agregarUsuario(Usuario usuario, TreeSet<Usuario> listaUsuarios){

        for(Usuario u: listaUsuarios){

            if(u.getUsername().equals(usuario.getUsername())){
                throw new UsuarioRepetidoException("Usuario repetido");
            }
        }
        listaUsuarios.add(usuario);

        return "Usuario agregado exitósamente";
    }


    public static String modificarUsuario(Usuario usuario, String newPassword, Rol newRol, String nombreApellidoNuevo, char sexo, TreeSet<Usuario> listaUsuarios){

        if(!listaUsuarios.contains(usuario)){
            throw new NoSuchElementException("Usuario no encontrado");
        }

        usuario.setPasswordHash(newPassword);
        usuario.setTipoIngreso(newRol);
        usuario.setNombreApellido(nombreApellidoNuevo);


        return "Usuario modificado exitosamente";
    }


    public static String eliminarUsuario(Usuario usuario, TreeSet<Usuario> listaUsuarios){

        if(!listaUsuarios.contains(usuario)){
            throw new NoSuchElementException("Usuario no encontrado");
        }
        listaUsuarios.remove(usuario);

        return "Usuario eliminado exitosamente";
    }


    public boolean aniadirArchivo(JSONArray jsonArray) {

        if (jsonArray.isEmpty()) {
            System.out.println("El arreglo está vacio");
            return false;
        }

        try {
            FileWriter file = new FileWriter("arregloUsuarios.json");
            file.write(jsonArray.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo");
        } catch (JSONException e) {
            System.out.println("Error al escribir el archivo");
        }
        
        return true;
    }


    public TreeSet<Usuario> getListaUsuarios() {return listaUsuarios;}

}
