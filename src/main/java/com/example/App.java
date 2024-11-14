package com.example;

import com.example.Login.Clases.LoginManager;
import com.example.Login.Clases.Usuario;
import com.example.Login.Enums.Rol;
import com.example.Personas.Clases.Pasajero.Pasajero;
import com.example.Personas.Clases.Personal.Personal;
import com.example.Utils.JsonManager;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        TreeSet<Usuario> listaUsuarios;
        LoginManager gestorLogin = new LoginManager();

        Scanner leer = new Scanner(System.in);

        int op = 1;
        int opcionMenu = 1;

        while (op != 0){
            System.out.println("-----------------------------------");
            System.out.println("Bienvenido al HOTEL:");
            System.out.println("    1. Iniciar Sesion como cliente");
            System.out.println("    2. Iniciar Sesion como personal del hotel");
            System.out.println("    3. Registrarse");
            System.out.println("    0. Salir del programa.");
            System.out.println("");
            System.out.println("Ingrese una opcion:");
            op = leer.nextInt();
            leer.nextLine();
            System.out.println("-----------------------------------");

            switch (op) {
                case 1:
                    System.out.println("Ingrese su nombre de usuario:");
                    String username = leer.nextLine();

                    System.out.println("Ingrese su contraseña:");
                    String password = leer.nextLine();

                    Pasajero pasajero = null;
                    pasajero = gestorLogin.iniciarSesionCliente(username, password);

                    if (pasajero.getTipoIngreso() == Rol.ADMINISTRADOR) {

                        while (opcionMenu != 0) {
                            System.out.println("---------------------------------------");
                            System.out.println("1. Agregar usuario");
                            System.out.println("2. Eliminar usuario");
                            System.out.println("3. Modificar Usuario");
                            System.out.println("4. Ver estado de las habitaciones");
                            System.out.println("0. Cerrar sesión");
                            System.out.println("---------------------------------------");

                            opcionMenu = leer.nextInt();
                            leer.nextLine();

                            switch (opcionMenu) {
                                case 1:
                                    ;

                                case 2:
                                    ;

                                case 3:
                                    ;

                                case 4:
                                    ;

                                case 0:
                                    ;

                            }
                        }
                    }
                    if (pasajero.getTipoIngreso() == Rol.CLIENTE) {
                        while (opcionMenu != 0){

                            System.out.println("---------------------------------------");
                            System.out.println("1. Realizar reserva");
                            System.out.println("2. Realizar consumo");
                            System.out.println("0. Cerrar sesión");
                            System.out.println("---------------------------------------");

                            opcionMenu = leer.nextInt();
                            leer.nextLine();

                            switch (opcionMenu) {
                                case 1:
                                    ;

                                case 2:
                                    ;

                                case 0:
                                    ;
                            }
                        }

                    }
                case 2:
                    System.out.println("Ingrese su nombre de usuario:");
                    username = leer.nextLine();

                    System.out.println("Ingrese su contraseña:");
                    password = leer.nextLine();

                    Personal personal = null;
                    personal = gestorLogin.iniciarSesionPersonal(username, password);
                    if (personal.getTipoIngreso() == Rol.PERSONAL_LIMPIEZA) {
                        while (opcionMenu != 0){

                            System.out.println("---------------------------------------");
                            System.out.println("1. Limpiar habitación");
                            System.out.println("2. Reparar habitación");
                            System.out.println("3. Desinfectar habitación");
                            System.out.println("4. Realizar Check-In");
                            System.out.println("5. Realizar Check-Out");
                            System.out.println("6. Ver reservas");
                            System.out.println("0. Cerrar sesión");
                            System.out.println("---------------------------------------");

                            opcionMenu = leer.nextInt();
                            leer.nextLine();

                            switch (opcionMenu) {
                                case 1:
                                    ;

                                case 2:
                                    ;

                                case 3:
                                    ;

                                case 4:
                                    ;

                                case 5:
                                    ;
                                case 6:
                                    ;
                                case 0:;
                            }
                        }
                    }

                case 3:
                    JsonManager gestorJSON = new JsonManager();

                    System.out.println("Ingrese el tipo de usuario a registrar:");
                    System.out.println("1. Cliente");
                    System.out.println("2. Personal de hotel");
                    int rol = leer.nextInt();
                    leer.nextLine();

                    if(rol == 1){
                        Pasajero pasajero1 = new Pasajero();

                        System.out.println("Ingrese su nombre de usuario:");
                        pasajero1.setUsername(leer.nextLine());

                        System.out.println("Ingrese su contraseña:");
                        pasajero1.setPasswordHash((Usuario.hashPassword(leer.nextLine())));

                        System.out.println("Ingrese el rol del usuario (PERSONAL_LIMPIEZA o CLIENTE):");
                        pasajero1.setTipoIngreso(gestorLogin.agregarRol(leer.nextLine()));

                        System.out.println("Ingrese su nombre y apellido:");
                        pasajero1.setNombreApellido(leer.nextLine());

                        System.out.println("Ingrese su DNI:");
                        pasajero1.setDni(leer.nextInt());
                        leer.nextLine();


                        //AGREGAR METODO PARA CARGAR TREESET DESDE ARCHIVO
                        gestorLogin = gestorJSON.cargarSetDesdeArchivo("usuarios.json");
                        gestorLogin.agregarUsuario(pasajero1);

                        //SOBREESCRIBIR ARCHIVO CON EL USUARIO YA AGREGADO
                        JsonManager.cargarArchivoDesdeSet(gestorLogin);
                    }else{
                        Personal personal1 = new Personal();

                        System.out.println("Ingrese su nombre de usuario:");
                        personal1.setUsername(leer.nextLine());

                        System.out.println("Ingrese su contraseña:");
                        personal1.setPasswordHash((Usuario.hashPassword(leer.nextLine())));

                        System.out.println("Ingrese el rol del usuario (PERSONAL_LIMPIEZA o CLIENTE):");
                        personal1.setTipoIngreso(gestorLogin.agregarRol(leer.nextLine()));

                        System.out.println("Ingrese su nombre y apellido:");
                        personal1.setNombreApellido(leer.nextLine());

                        System.out.println("Ingrese su DNI:");
                        personal1.setDni(leer.nextInt());
                        leer.nextLine();

                        //AGREGAR METODO PARA CARGAR TREESET DESDE ARCHIVO
                        gestorLogin = gestorJSON.cargarSetDesdeArchivo("usuarios.json");
                        gestorLogin.agregarUsuario(personal1);

                        //SOBREESCRIBIR ARCHIVO CON EL USUARIO YA AGREGADO
                        JsonManager.cargarArchivoDesdeSet(gestorLogin);
                    }
                case 0:
                    break;
            }
        }
    }
}
