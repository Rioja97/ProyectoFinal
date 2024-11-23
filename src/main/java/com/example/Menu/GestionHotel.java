package com.example.Menu;

import com.example.Hotel.Clases.Habitacion;
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

    public GestionHotel(){


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
                        LoginManager
                        mostrarMenuUsuarios(leer);
                        break;

                    case 2:
                        mostrarMenuReservas(leer);
                        break;

                    case 3:
                        registrarUsuario(leer);
                        break;

                    case 0:
                        System.out.println("Sesión cerrada.");
                        break;

                    default:
                        System.out.println("Opción no válida, por favor intente nuevamente.");
                }
            }
        }

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

        private void verUsuarios() {
            JSONArray arrayUsuarios = JsonManager.FileAJsonArray("usuarios.json");
            TreeSet<Usuario> listaUsuarios = JsonManager.jsonArrayAListaUsuarios(arrayUsuarios);

            for (Usuario u : listaUsuarios) {
                System.out.println(u.toString());
            }
        }

        private void eliminarUsuario(Scanner leer) {
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

        private void mostrarMenuReservas(Scanner leer) {
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
                        verReservas();
                        break;

                    case 2:
                        realizarCheckIn(leer);
                        break;

                    case 3:
                        realizarCheckOut(leer);
                        break;

                    case 4:
                        verHabitaciones();
                        break;

                    case 0:
                        System.out.println("Volviendo al menú principal.");
                        break;

                    default:
                        System.out.println("Opción no válida, por favor intente nuevamente.");
                }
            }
        }

        private void verReservas() {
            JSONArray arr = JsonManager.FileAJsonArray("reservas.json");
            Map<Integer, Reserva> listaReservas = JsonManager.jsonArrayAMap(arr);

            for (Map.Entry<Integer, Reserva> entry : listaReservas.entrySet()) {
                System.out.println("Reserva: " + entry.getKey() + " - " + entry.getValue());
            }
        }

        private void realizarCheckIn(Scanner leer) {
            System.out.println("Ingrese el número de habitación para realizar el Check-In:");
            int numHabitacion = leer.nextInt();
            leer.nextLine();

            JSONArray arr = JsonManager.FileAJsonArray("reservas.json");
            Map<Integer, Reserva> listaReservas = JsonManager.jsonArrayAMap(arr);

            if (listaReservas.containsKey(numHabitacion)) {
                Reserva reserva = listaReservas.get(numHabitacion);
                reserva.getHabitacion().setEstado(Estado.OCUPADO);
                arr = JsonManager.mapAJsonArray(listaReservas);
                JsonManager.JsonArrayAFile(arr, "reservas.json");
                System.out.println("Check-In realizado para la habitación " + numHabitacion);
            } else {
                System.out.println("No existe una reserva para la habitación " + numHabitacion);
            }
        }

        private void realizarCheckOut(Scanner leer) {
            System.out.println("Ingrese el número de habitación para realizar el Check-Out:");
            int numHabitacion = leer.nextInt();
            leer.nextLine();

            JSONArray arr = JsonManager.FileAJsonArray("reservas.json");
            Map<Integer, Reserva> listaReservas = JsonManager.jsonArrayAMap(arr);

            if (listaReservas.containsKey(numHabitacion)) {
                Reserva reserva = listaReservas.get(numHabitacion);
                reserva.getHabitacion().setEstado(Estado.DISPONIBLE); // Cambiar el estado a LIBRE
                arr = JsonManager.mapAJsonArray(listaReservas);
                JsonManager.JsonArrayAFile(arr, "reservas.json");
                System.out.println("Check-Out realizado para la habitación " + numHabitacion);
            } else {
                System.out.println("No existe una reserva para la habitación " + numHabitacion);
            }
        }

        private void verHabitaciones() {
            JSONArray arr = JsonManager.FileAJsonArray("habitaciones.json");
            List<Habitacion> listaHabitaciones = JsonManager.jsonArrayAHabitaciones(arr);

            for (Habitacion h : listaHabitaciones) {
                System.out.println(h.toString());
            }
        }

        private void registrarUsuario(Scanner leer) {
            System.out.println("Ingrese el tipo de usuario a registrar:");
            System.out.println("1. Cliente");
            System.out.println("2. Personal de hotel");
            int rol = leer.nextInt();
            leer.nextLine(); // Limpiar el buffer

            if (rol == 1) {
                registrarCliente(leer);
            } else if (rol == 2) {
                registrarPersonal(leer);
            } else {
                System.out.println("Opción no válida.");
            }
        }

        private void registrarCliente(Scanner leer) {
            Pasajero pasajero1 = new Pasajero();

            System.out.println("Ingrese su nombre de usuario:");
            pasajero1.setUsername(leer.nextLine());

            System.out.println("Ingrese su contraseña:");
            pasajero1.setPassword(leer.nextLine());

            pasajero1.setTipoIngreso(Rol.CLIENTE);

            System.out.println("Ingrese su nombre y apellido:");
            pasajero1.setNombreApellido(leer.nextLine());

            System.out.println("Ingrese su DNI:");
            pasajero1.setDni(leer.nextInt());
            leer.nextLine();

            System.out.println("Ingrese su direccion:");
            pasajero1.setDireccion(leer.nextLine());

            System.out.println("Ingrese su nacionalidad:");
            pasajero1.setNacionalidad(leer.nextLine());

            // Cargar usuarios desde archivo
            JSONArray arr = JsonManager.FileAJsonArray("usuarios.json");
            TreeSet<Usuario> listaUsuarios = JsonManager.jsonArrayAListaUsuarios(arr);

            listaUsuarios.add(pasajero1);

            // Guardar usuarios en archivo
            arr = JsonManager.listaUsuariosAJsonArray(listaUsuarios);
            JsonManager.JsonArrayAFile(arr, "usuarios.json");
            System.out.println("Cliente registrado correctamente.");
        }

        private void registrarPersonal(Scanner leer) {
            Personal personal1 = new Personal();

            System.out.println("Ingrese su nombre de usuario:");
            personal1.setUsername(leer.nextLine());

            System.out.println("Ingrese su contraseña:");
            personal1.setPassword(leer.nextLine());

            personal1.setTipoIngreso(Rol.PERSONAL_LIMPIEZA);

            System.out.println("Ingrese su nombre y apellido:");
            personal1.setNombreApellido(leer.nextLine());

            // Cargar usuarios desde archivo
            JSONArray arr = JsonManager.FileAJsonArray("usuarios.json");
            TreeSet<Usuario> listaUsuarios = JsonManager.jsonArrayAListaUsuarios(arr);

            listaUsuarios.add(personal1);

            // Guardar usuarios en archivo
            arr = JsonManager.listaUsuariosAJsonArray(listaUsuarios);
            JsonManager.JsonArrayAFile(arr, "usuarios.json");
            System.out.println("Personal registrado correctamente.");
    }
}

