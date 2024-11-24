package com.example.Menu;

import com.example.Hotel.Clases.Habitacion;
import com.example.Hotel.Clases.Hotel;
import com.example.Hotel.Clases.Reserva;
import com.example.Hotel.Clases.enumeradores.Estado;
import com.example.Login.Clases.LoginManager;
import com.example.Login.Clases.Usuario;
import com.example.Login.Enums.Rol;
import com.example.Personas.Clases.Pasajero.Pasajero;
import com.example.Personas.Clases.Personal.Personal;
import com.example.Utils.JsonManager;
import org.json.JSONArray;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public final class GestionHotel {

    Hotel lasVegas = new Hotel();

    public GestionHotel() {

        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        while (opcion != 0) {

            System.out.println("-------------------------------------------------------");
            System.out.println("BIENVENIDO AL GESTOR DE HOTELES");
            System.out.println("¿Que desea hacer?: ");
            System.out.println("    1. Iniciar Sesión");
            System.out.println("    2. Registrarse");
            System.out.println("    3. Salir");

            System.out.println("Su opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    Usuario usuario = new Usuario();
                    usuario = LoginManager.iniciarSesionConReintentos();

                    if(usuario.getTipoIngreso().equals("CLIENTE")){
                        System.out.println("Aca va el menu de usuarios");
                        //mostrarMenuReservas();
                    } else if(usuario.getTipoIngreso().equals("ADMINISTRADOR")) {
                        System.out.println("Aca va el menu de adminitradores");
                    //mostrarMenuUsuarios();
                    } else {
                        System.out.println("Aca va el menu de personas");
                    }

                case 2:
                    Pasajero pasajero = crearPasajero();
                    lasVegas.agregarUsuario(pasajero);
                    break;
                default:
                    System.out.println("Opcion inválida");
                    break;
            }
        }
    }

       /* public static void menuPrincipal(){

            Scanner leer = new Scanner(System.in);
            int opcionMenu = -1;

            while (opcionMenu != 0) {
                System.out.println("---------------------------------------");
                System.out.println("1. Gestión de Usuarios");
                System.out.println("2. Gestión de Reservas");
                System.out.println("3. Registro de Usuario");
                System.out.println("0. Cerrar sesión");
                System.out.println("---------------------------------------");

                opcionMenu = leer.nextInt();
                leer.nextLine();  // Para limpiar el buffer

                switch (opcionMenu) {
                    case 1:

                        mostrarMenuUsuarios(leer);
                        break;

                    case 2:
                        mostrarMenuReservas(leer);
                        break;

                    case 3:
                        //registrarUsuario(leer);
                        break;

                    case 0:
                        System.out.println("Sesión cerrada.");
                        break;

                    default:
                        System.out.println("Opción no válida, por favor intente nuevamente.");
                }
            }*/


        private void mostrarMenuUsuarios(Scanner leer) {
            int opcionMenuUsuarios = -1;

            while (opcionMenuUsuarios != 0) {
                System.out.println("---------------------------------------");
                System.out.println("1. Ver Usuarios");
                System.out.println("2. Eliminar Usuario");
                System.out.println("0. Volver al menú principal");
                System.out.println("---------------------------------------");

                opcionMenuUsuarios = leer.nextInt();
                leer.nextLine(); // Limpiar el buffer

                switch (opcionMenuUsuarios) {
                    case 1:
                        verUsuarios();
                        break;

                    case 2:
                        eliminarUsuario(leer);
                        break;

                    case 0:
                        System.out.println("Volviendo al menú principal.");
                        break;

                    default:
                        System.out.println("Opción no válida, por favor intente nuevamente.");
                }
            }
        }

        private  void verUsuarios() {
            JSONArray arrayUsuarios = JsonManager.FileAJsonArray("usuarios.json");
            TreeSet<Usuario> listaUsuarios = JsonManager.jsonArrayAListaUsuarios(arrayUsuarios);

            for (Usuario u : listaUsuarios) {
                System.out.println(u.toString());
            }
        }

        private  void eliminarUsuario(Scanner leer) {
            System.out.println("Ingrese el username del usuario a eliminar:");
            String nomUsuario = leer.nextLine();

            JSONArray arrayUsuarios = JsonManager.FileAJsonArray("usuarios.json");
            TreeSet<Usuario> listaUsuarios = JsonManager.jsonArrayAListaUsuarios(arrayUsuarios);

            Usuario usuarioAEliminar = null;
            for (Usuario usuario : listaUsuarios) {
                if (usuario.getUsername().equals(nomUsuario)) {
                    usuarioAEliminar = usuario;
                    break;
                }
            }

            if (usuarioAEliminar != null) {
                listaUsuarios.remove(usuarioAEliminar);
                JSONArray arr = JsonManager.listaUsuariosAJsonArray(listaUsuarios);
                JsonManager.JsonArrayAFile(arr, "usuarios.json");
                System.out.println("Usuario " + nomUsuario + " eliminado correctamente.");
            } else {
                System.out.println("Usuario no encontrado.");
            }
        }

        private  void mostrarMenuReservas(Scanner leer) {
            int opcionMenuReservas = -1;

            while (opcionMenuReservas != 0) {
                System.out.println("---------------------------------------");
                System.out.println("1. Ver Reservas");
                System.out.println("2. Realizar Check-In");
                System.out.println("3. Realizar Check-Out");
                System.out.println("4. Ver Habitaciones");
                System.out.println("0. Volver al menú principal");
                System.out.println("---------------------------------------");

                opcionMenuReservas = leer.nextInt();
                leer.nextLine(); // Limpiar el buffer

                switch (opcionMenuReservas) {
                    case 1:
                        //verReservas();
                        break;

                    case 2:
                        //realizarCheckIn(leer);
                        break;

                    case 3:
                        //realizarCheckOut(leer);
                        break;

                    case 4:
                        //verHabitaciones();
                        break;

                    case 0:
                        System.out.println("Volviendo al menú principal.");
                        break;

                    default:
                        System.out.println("Opción no válida, por favor intente nuevamente.");
                }
            }
        }




    private  Pasajero crearPasajero(){

        Pasajero pasajero = new Pasajero();
        Scanner leer = new Scanner(System.in);

        System.out.println("Ingrese su nombre de usuario:");
        pasajero.setUsername(leer.nextLine());

        System.out.println("Ingrese la contraseña: ");
        pasajero.setPassword(leer.nextLine());

        System.out.println("Ingrese su nombre y apellido:");
        pasajero.setNombreApellido(leer.nextLine());

        System.out.println("Ingrese su DNI:");
        pasajero.setDni(leer.nextInt());
        leer.nextLine();

        System.out.println("Ingrese su direccion:");
        pasajero.setDireccion(leer.nextLine());

        System.out.println("Ingrese su nacionalidad:");
        pasajero.setNacionalidad(leer.nextLine());

        return pasajero;
    }
}

