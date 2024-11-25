package com.example.Menu;

import com.example.Hotel.Clases.Habitacion;
import com.example.Hotel.Clases.Hotel;
import com.example.Hotel.Clases.Reserva;
import com.example.Hotel.Clases.enumeradores.Estado;
import com.example.Hotel.Clases.enumeradores.Tipo;
import com.example.Login.Clases.LoginManager;
import com.example.Login.Clases.Usuario;
import com.example.Login.Enums.Rol;
import com.example.Personas.Clases.Administrador.Administrador;
import com.example.Personas.Clases.Pasajero.Pasajero;
import com.example.Personas.Clases.Personal.Personal;
import com.example.Utils.JsonManager;
import org.json.JSONArray;

import java.util.*;

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

                    if(usuario.getTipoIngreso().name().equals("CLIENTE")){
                        System.out.println("Aca va el menu de usuarios");
                        //mostrarMenuReservas();
                    } else if(usuario.getTipoIngreso().name().equals("ADMINISTRADOR")) {
                        System.out.println("Aca va el menu de adminitradores");
                        mostrarMenuAdministradores();
                    } else {
                        System.out.println("Aca va el menu de personas");
                        mostrarMenuClienteReservas();
                    }

                case 2:
                    Pasajero pasajero = crearPasajero();
                    lasVegas.agregarUsuario(pasajero);
                    break;

                case 3:
                    System.out.println("Cerrando programa...");
                    opcion = 0;

                default:
                    System.out.println("Opcion inválida");
                    break;
            }
        }
    }


    private void mostrarMenuAdministradores() {
        int opcionMenuUsuarios = -1;

        while (opcionMenuUsuarios != 0) {
            System.out.println("---------------------------------------");
            System.out.println("0. Cerrar Sesión");
            System.out.println("1. Gestion de usuarios");
            System.out.println("2. Gestion de hotel");
            System.out.println("---------------------------------------");

            Scanner leer = new Scanner(System.in);
            opcionMenuUsuarios = leer.nextInt();
            leer.nextLine(); // Limpiar el buffer

            switch (opcionMenuUsuarios) {
                case 1:
                    mostrarMenuGestionUsuarios();
                    break;

                case 2:
                    mostrarMenuGestionHotel();
                    break;

                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        }
    }

    private void mostrarMenuGestionUsuarios() {
        int opcionMenuUsuarios = -1;

        while (opcionMenuUsuarios != 0) {
            System.out.println("---------------------------------------");
            System.out.println("1. Ver Usuarios");
            System.out.println("2. Agregar Usuario");
            System.out.println("3. Modificar Usuario");
            System.out.println("3. Eliminar Usuario");
            System.out.println("0. Volver al menú anterior");
            System.out.println("---------------------------------------");

            Scanner leer = new Scanner(System.in);
            opcionMenuUsuarios = leer.nextInt();
            leer.nextLine(); // Limpiar el buffer

            switch (opcionMenuUsuarios) {
                case 1:
                    System.out.println(lasVegas.getUsuarios());
                    break;

                case 2:
                    lasVegas.agregarUsuario(pedirUsuario());
                    break;

                case 3:
                    System.out.println("Ingrese el nombre de usuario a modificar: ");
                    String username = leer.nextLine();
                    lasVegas.modificarUsuario(pedirUsuario(),username);
                    break;

                case 4:
                    lasVegas.eliminarUsuario(String username);

                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        }
    }


    private void mostrarMenuGestionHotel() {
        int opcionMenuUsuarios = -1;

        while (opcionMenuUsuarios != 0) {
            System.out.println("---------------------------------------");
            System.out.println("1. Añadir habitacion");
            System.out.println("2. Quitar habitacion");
            System.out.println("3. Modificar habitacion");
            System.out.println("3. Ver todas las reservas");
            System.out.println("0. Volver al menú anterior");
            System.out.println("---------------------------------------");

            Scanner leer = new Scanner(System.in);
            opcionMenuUsuarios = leer.nextInt();
            leer.nextLine(); // Limpiar el buffer

            switch (opcionMenuUsuarios) {
                case 1:
                    lasVegas.agregarHabitacion(crearHabitacion());
                    break;

                case 2:
                    System.out.println(lasVegas.getHabitaciones());
                    System.out.println("Ingrese el numero de la habitación que desea eliminar: ");
                    int numero = leer.nextInt();
                    leer.nextLine();
                    lasVegas.eliminarHabitacion(numero);
                    break;

                case 3:
                    System.out.println(lasVegas.getHabitaciones());
                    System.out.println("Ingrese el numero de la habitación que desea modificar: ");
                    int numeroH = leer.nextInt();
                    leer.nextLine();
                    //lasVegas.modificarHabitacion(numeroH, crearHabitacion());
                    break;

                case 4:
                    System.out.println(lasVegas.getReservas());
                    break;

                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        }
    }


    private  void mostrarMenuClienteReservas() {
        int opcionMenuReservas = -1;

        while (opcionMenuReservas != 0) {
            System.out.println("---------------------------------------");
            System.out.println("1. Ver Reservas");
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

    public <T extends Usuario> Usuario pedirUsuario(){

        Scanner scan = new Scanner(System.in);

        System.out.println("Creación del usuario...");
        System.out.println("    Ingrese el nombre del usuario: ");
        String username = scan.nextLine();

        System.out.println("    Ingrese la contraseña del usuario: ");
        String password = scan.nextLine();

        System.out.println("    Ingrese el nombre y apellido del usuario: ");
        String nombreApellido = scan.nextLine();

        System.out.println("Ingrese el rol del usuario nuevo: ");
        System.out.println("    1. Personal general del hotel");
        System.out.println("    1. Administrador de sistema");
        System.out.println("    Su opcion: ");

        int opcion = scan.nextInt();
        scan.nextLine();

        if(opcion == 1){
            Personal personal = new Personal(username, password, Rol.PERSONAL_LIMPIEZA, nombreApellido);
            return personal;
        } else{
            Administrador administrador = new Administrador(username, password, Rol.ADMINISTRADOR, nombreApellido);
            return administrador;
        }
    }


    public static Habitacion crearHabitacion(){

        Scanner scan = new Scanner(System.in);

        System.out.println("Creacion de la habitacion...");
        System.out.println("    Ingrese el número de la habitación");
        int numero = scan.nextInt();
        scan.nextLine();

        System.out.println("    Ingrese el precio de la habitación");
        int precio = scan.nextInt();
        scan.nextLine();

        System.out.println("    Ingrese el tipo de la habitación");
        System.out.println("        1. Simple");
        System.out.println("        2. Doble");
        String tipo = scan.nextLine();

        Habitacion habitacion = new Habitacion();

        if (tipo.equals("SIMPLE")){
            habitacion.setNumero(numero);
            habitacion.setTipo(Tipo.SIMPLE);
            habitacion.setPrecioPorNoche(precio);
        } else{
            habitacion.setNumero(numero);
            habitacion.setTipo(Tipo.DOBLE);
            habitacion.setPrecioPorNoche(precio);
        }
        return habitacion;
    }

}
