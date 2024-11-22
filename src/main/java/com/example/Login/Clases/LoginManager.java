package com.example.Login.Clases;

import com.example.Hotel.Clases.enumeradores.Tipo;
import com.example.Login.Enums.Rol;
import com.example.Login.Exceptions.UsuarioRepetidoException;
import com.example.Personas.Clases.Pasajero.Pasajero;
import com.example.Personas.Clases.Personal.Personal;
import com.example.Utils.JsonManager;
import org.json.JSONArray;
import org.json.JSONException;
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

    public Tipo agregarTipo(String tipo){
        Tipo t = null;

        try{
            String tipoIngresado = tipo.toUpperCase();
            t = Tipo.valueOf(tipoIngresado);
        }catch (IllegalArgumentException e){
            e.getMessage();
        }
        return t;
    }


    //Metodo para iniciar sesion como Pasajero
    public Pasajero iniciarSesionCliente(String username, String password){
        Pasajero pasajero1 = null;

        TreeSet<Usuario> listaUsuarios = new TreeSet<>();

        //CARGAR TREESET DESDE ARCHIVO
        JSONArray arr = JsonManager.FileAJsonTokener("usuarios.json");
        listaUsuarios = JsonManager.jsonArrayAListaUsuarios(arr);

        password = Usuario.hashPassword(password);

        for(Usuario u : listaUsuarios){
            if(u instanceof Pasajero){
                if(u.getUsername().equals(username) && u.getPasswordHash().equals(password)){
                    pasajero1 = (Pasajero) u;
                }else{
                    throw new NoSuchElementException("Nombre de usuario o contraseña incorrectos.");
                }
            }

        }
        return pasajero1;
    }

    //Metodo para iniciar sesion como Pasajero
    public Personal iniciarSesionPersonal(String username, String password){
        Personal personal1 = null;

        TreeSet<Usuario> listaUsuarios = new TreeSet<>();

        //CARGAR TREESET DESDE ARCHIVO
        JSONArray arr = JsonManager.FileAJsonTokener("usuarios.json");
        listaUsuarios = JsonManager.jsonArrayAListaUsuarios(arr);

        password = Usuario.hashPassword(password);

        for(Usuario u : listaUsuarios){
            if(u instanceof Personal){
                if(u.getUsername().equals(username) && u.getPasswordHash().equals(password)){
                    personal1 = (Personal) u;
                }else{
                    throw new NoSuchElementException("Nombre de usuario o contraseña incorrectos.");
                }
            }
        }
        return personal1;
    }


    public static String agregarUsuario(Usuario usuario, TreeSet<Usuario> listaUsuarios) throws UsuarioRepetidoException{
        JSONArray arr = JsonManager.FileAJsonTokener("usuarios.json");
        listaUsuarios = JsonManager.jsonArrayAListaUsuarios(arr);

        for(Usuario u: listaUsuarios){

            if(u.getUsername().equals(usuario.getUsername())){
                throw new UsuarioRepetidoException("Usuario repetido");
            }
        }
        listaUsuarios.add(usuario);

        arr = JsonManager.listaUsuariosAJsonArray(listaUsuarios);
        JsonManager.JsonArrayAFile(arr,"usuarios.json");

        return "Usuario agregado exitósamente";
    }


    public static String modificarUsuario(Usuario usuario, String newPassword, Rol newRol, String nombreApellidoNuevo, TreeSet<Usuario> listaUsuarios){
        JSONArray arr = JsonManager.FileAJsonTokener("usuarios.json");
        listaUsuarios = JsonManager.jsonArrayAListaUsuarios(arr);


        if(!listaUsuarios.contains(usuario)){
            throw new NoSuchElementException("Usuario no encontrado");
        }

        usuario.setPasswordHash(newPassword);
        usuario.setTipoIngreso(newRol);
        usuario.setNombreApellido(nombreApellidoNuevo);

        arr = JsonManager.listaUsuariosAJsonArray(listaUsuarios);
        JsonManager.JsonArrayAFile(arr,"usuarios.json");

        return "Usuario modificado exitosamente";
    }


    public static String eliminarUsuario(Usuario usuario, TreeSet<Usuario> listaUsuarios){
        JSONArray arr = JsonManager.FileAJsonTokener("usuarios.json");
        listaUsuarios = JsonManager.jsonArrayAListaUsuarios(arr);


        if(!listaUsuarios.contains(usuario)){
            throw new NoSuchElementException("Usuario no encontrado");
        }
        listaUsuarios.remove(usuario);

        arr = JsonManager.listaUsuariosAJsonArray(listaUsuarios);
        JsonManager.JsonArrayAFile(arr,"usuarios.json");

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
}
