package com.example.Login.Clases;

import com.example.Login.LoginExceptions.IngresoIncorrectoException;
import com.example.Utils.JsonManager;
import org.json.JSONArray;
import java.io.FileNotFoundException;
import java.util.TreeSet;
public class LoginManager{

    //ESTA CLASE SE PENSÓ PARA LA PARTE DEL LOGUEO Y LOS MÉTODOS QUE ESTÉN RELACIONADOS CON EL MISMO.

    //METODO QUE REALIZA LA PARTE LÓGICA DEL INICIO DE SESIÓN
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


    //METODO QUE REALIZA LOS INGRESOS POR CONSOLA DEL INICIO DE SESIÓN, LLAMA AL METODO ANTERIOR
    //Y AGREGA LOS INTENTOS.
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
