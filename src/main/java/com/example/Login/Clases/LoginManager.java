package com.example.Login.Clases;

import com.example.Hotel.Clases.enumeradores.Tipo;
import com.example.Login.Enums.Rol;
import com.example.Login.Exceptions.IngresoIncorrectoException;
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
    public boolean iniciarSesionCliente(String username, String password) throws IngresoIncorrectoException {

        TreeSet<Usuario> listaUsuarios = new TreeSet<>();

        boolean flag = false;

        //CARGAR TREESET DESDE ARCHIVO
        JSONArray arr = JsonManager.FileAJsonArray("usuarios.json");
        listaUsuarios = JsonManager.jsonArrayAListaUsuarios(arr);

        //System.out.println(listaUsuarios.toString());

        for(Usuario u : listaUsuarios){
            if(u instanceof Pasajero){
                if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                    flag = true;
                }
            }
        }
        if(flag == false){
            throw new IngresoIncorrectoException("Nombre de usuario o contraseña incorrectos.");
        }
        return flag;
    }

    //Metodo para iniciar sesion como Personal
    public Personal iniciarSesionPersonal(String username, String password){
        Personal personal1 = new Personal();

        TreeSet<Usuario> listaUsuarios = new TreeSet<>();

        //CARGAR TREESET DESDE ARCHIVO
        JSONArray arr = JsonManager.FileAJsonArray("usuarios.json");
        listaUsuarios = JsonManager.jsonArrayAListaUsuarios(arr);


        for(Usuario u : listaUsuarios){
            if(u instanceof Personal){
                if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                    personal1 = (Personal) u;
                }else{
                    throw new NoSuchElementException("Nombre de usuario o contraseña incorrectos.");
                }
            }
        }
        return personal1;
    }


    public static String agregarUsuario(Usuario usuario, TreeSet<Usuario> listaUsuarios) throws UsuarioRepetidoException{
        JSONArray arr = JsonManager.FileAJsonArray("usuarios.json");
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
        JSONArray arr = JsonManager.FileAJsonArray("usuarios.json");
        listaUsuarios = JsonManager.jsonArrayAListaUsuarios(arr);


        if(!listaUsuarios.contains(usuario)){
            throw new NoSuchElementException("Usuario no encontrado");
        }

        usuario.setPassword(newPassword);
        usuario.setTipoIngreso(newRol);
        usuario.setNombreApellido(nombreApellidoNuevo);

        arr = JsonManager.listaUsuariosAJsonArray(listaUsuarios);
        JsonManager.JsonArrayAFile(arr,"usuarios.json");

        return "Usuario modificado exitosamente";
    }


    public static String eliminarUsuario(Usuario usuario, TreeSet<Usuario> listaUsuarios){
        JSONArray arr = JsonManager.FileAJsonArray("usuarios.json");
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

    public boolean iniciarSesionConReintentos() {
        int intentosRestantes = 3; // Máximo de intentos
        boolean sesionIniciada = false;
        boolean flag = false;

        while (intentosRestantes > 0 && !sesionIniciada) {
            try {
                // Solicitar datos al usuario
                String username = solicitarEntrada("Ingresa tu nombre de usuario:");
                String password = solicitarEntrada("Ingresa tu contraseña:");

                // Intentar iniciar sesión
                sesionIniciada = iniciarSesionCliente(username, password);
                if (sesionIniciada) {
                    System.out.println("¡Inicio de sesión exitoso!");
                    flag = true;
                }
            } catch (IngresoIncorrectoException e) {
                intentosRestantes--;
                System.out.println(e.getMessage());
                if (intentosRestantes > 0) {
                    System.out.println("Te quedan " + intentosRestantes + " intentos.");
                } else {
                    System.out.println("Demasiados intentos fallidos. Intenta más tarde.");
                    flag = false;
                }
            }
        }
        return flag;
    }

    //Metodo para ingresar los datos al usuario
    private String solicitarEntrada(String mensaje) {
        System.out.println(mensaje);

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        return scanner.nextLine();
    }

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
