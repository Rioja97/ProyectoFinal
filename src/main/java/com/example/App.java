package com.example;

import com.example.Login.Clases.LoginManager;
import com.example.Login.Clases.Usuario;
import com.example.Login.Enums.Rol;
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
            System.out.println("    1. Iniciar Sesion");
            System.out.println("    2. Registrarse");
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

                    Usuario usuario1 = null;
                    usuario1 = gestorLogin.iniciarSesion(username, password);

                    if (usuario1.getTipoIngreso() == Rol.ADMINISTRADOR) {

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
                    if (usuario1.getTipoIngreso() == Rol.PERSONAL_LIMPIEZA) {
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

                    if (usuario1.getTipoIngreso() == Rol.CLIENTE) {
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
                    JsonManager gestorJSON = new JsonManager();

                    Usuario usuario = new Usuario();

                    System.out.println("Ingrese su nombre de usuario:");
                    usuario.setUsername(leer.nextLine());

                    System.out.println("Ingrese su contraseña:");
                    usuario.setPasswordHash((Usuario.hashPassword(leer.nextLine())));

                    System.out.println("Ingrese el rol del usuario (PERSONAL_LIMPIEZA o CLIENTE):");
                    usuario.setTipoIngreso(gestorLogin.agregarRol(leer.nextLine()));

                    System.out.println("Ingrese su nombre y apellido:");
                    usuario.setNombreApellido(leer.nextLine());

                    System.out.println("Ingrese su DNI:");
                    usuario.setDni(leer.nextInt());
                    leer.nextLine();

                    System.out.println("Ingrese su sexo:");
                    usuario.setSexo(leer.next().charAt(0));

                    //AGREGAR METODO PARA CARGAR TREESET DESDE ARCHIVO
                    gestorLogin = gestorJSON.cargarSetDesdeArchivo(usuarios.json);
                    gestorLogin.agregarUsuario(usuario);

                    //SOBREESCRIBIR ARCHIVO CON EL USUARIO YA AGREGADO
                    JsonManager.cargarArchivoDesdeSet(gestorLogin);

                case 0:
                    break;
            }
        }
    }
}
