package com.example.Login.Clases;

import com.example.Login.LoginExceptions.IngresoIncorrectoException;
import com.example.Login.LoginExceptions.UsuarioRepetidoException;
import com.example.Utils.JsonManager;
import org.json.JSONArray;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class LoginManager implements com.example.Interfaces.iMetodosHotel {


    public String agregarUsuario(Usuario usuario) throws UsuarioRepetidoException{
        JSONArray arr = JsonManager.FileAJsonArray("usuarios.json");
        TreeSet<Usuario> listaUsuarios = JsonManager.jsonArrayAListaUsuarios(arr);

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


    public String modificarUsuario(Usuario modificado,String nombreUsuario){

        boolean encontrado = false;
        JSONArray arr = JsonManager.FileAJsonArray("usuarios.json");
        TreeSet<Usuario> listaUsuarios = JsonManager.jsonArrayAListaUsuarios(arr);

        Usuario usuario = new Usuario();

        for (Usuario u: listaUsuarios){
            if(u.getUsername().equals(nombreUsuario)){

                usuario.setPassword(modificado.getPassword());
                usuario.setTipoIngreso(modificado.getTipoIngreso());
                usuario.setNombreApellido(modificado.getNombreApellido());
                encontrado = true;
            }
            if(encontrado == true){
                arr = JsonManager.listaUsuariosAJsonArray(listaUsuarios);
                JsonManager.JsonArrayAFile(arr,"usuarios.json");
                return "Usuario modificado exitosamente";
            } else{
                throw new NoSuchElementException("Usuario no encontrado");
            }
        }
        return null;
    }


    public String eliminarUsuario(String nombreUsuario){

        JSONArray arr = JsonManager.FileAJsonArray("usuarios.json");
        TreeSet<Usuario> listaUsuarios = JsonManager.jsonArrayAListaUsuarios(arr);
        boolean eliminado = false;

        for (Usuario u: listaUsuarios){
            if(u.getUsername().equals(nombreUsuario)){
                listaUsuarios.remove(u);
                eliminado = true;
            }
            if(eliminado == true){
                arr = JsonManager.listaUsuariosAJsonArray(listaUsuarios);
                JsonManager.JsonArrayAFile(arr,"usuarios.json");
                return "Usuario eliminado exitosamente";
            } else{
                throw new NoSuchElementException("Usuario no encontrado");
            }
        }
        return null;
    }



    public static Usuario iniciarSesion(Usuario usuario) throws FileNotFoundException {

        TreeSet<Usuario> usuarios = new TreeSet<>();
        JSONArray arr = JsonManager.FileAJsonArray("usuarios.json");
        usuarios = JsonManager.jsonArrayAListaUsuarios(arr);

        if (usuarios == null) {
            throw new FileNotFoundException("No hay ningún usuario cargado, cargue uno por favor.");
        }

        for (Usuario u : usuarios) {
            if (usuario.getUsername().equals(u.getUsername()) && usuario.getPassword().equals(u.getPassword())) {
                return u;  // Si se encuentra el usuario, devuelve el objeto Usuario
            }
        }

        throw new IngresoIncorrectoException("El usuario no se encuentra registrado o las credenciales son incorrectas.");
    }


    public static Usuario iniciarSesionConReintentos() {

        int intentosRestantes = 3; // Máximo de intentos
        Usuario enSesion = new Usuario();

        while (intentosRestantes > 0) {
            try {
                // Solicitar datos al usuario
                String username = solicitarEntrada("Ingresa tu nombre de usuario:");
                enSesion.setUsername(username);
                String password = solicitarEntrada("Ingresa tu contraseña:");
                enSesion.setPassword(password);

                try {
                    // Intentar iniciar sesión
                    enSesion = iniciarSesion(enSesion);  // Intenta iniciar sesión
                    System.out.println("¡Inicio de sesión exitoso!");
                    return enSesion;  // Si el inicio de sesión es exitoso, retorna el usuario

                } catch (IngresoIncorrectoException e) {
                    // Si las credenciales son incorrectas
                    intentosRestantes--;
                    System.out.println(e.getMessage());
                    if (intentosRestantes > 0) {
                        System.out.println("Te quedan " + intentosRestantes + " intentos.");
                    } else {
                        System.out.println("Demasiados intentos fallidos. Intenta más tarde.");
                    }
                } catch (FileNotFoundException e) {
                    // Si no se encuentra el archivo de usuarios
                    System.out.println(e.getMessage());
                    break;  // Termina el ciclo si no se encuentra el archivo
                }
            } catch (Exception e) {
                // Si hay algún error con la entrada
                System.out.println("Hubo un error al leer la entrada: " + e.getMessage());
                break;
            }
        }
        return null;  // Si los intentos se agotaron o hubo un error, retorna null
    }



    //Metodo para ingresar los datos al usuario
    private static String solicitarEntrada(String mensaje) {
        System.out.println(mensaje);

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        return scanner.nextLine();
    }
}
