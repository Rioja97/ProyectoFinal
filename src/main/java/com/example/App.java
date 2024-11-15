package com.example;

import com.example.Hotel.Clases.Habitacion;
import com.example.Hotel.Clases.Reserva;
import com.example.Hotel.Clases.enumeradores.Estado;
import com.example.Hotel.Clases.enumeradores.Tipo;
import com.example.Login.Clases.LoginManager;
import com.example.Login.Clases.Usuario;
import com.example.Login.Enums.Rol;
import com.example.Personas.Clases.Pasajero.Pasajero;
import com.example.Personas.Clases.Personal.Personal;
import com.example.Utils.JsonManager;
import org.json.JSONArray;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        TreeSet<Usuario> listaUsuarios = new TreeSet<>();
        ArrayList<Habitacion> listaHabitaciones = new ArrayList<>();
        Map<Integer, Reserva> listaReservas = new HashMap<>();


        LoginManager gestorLogin = new LoginManager();

        Scanner leer = new Scanner(System.in);

        int op = 1;
        int opcionMenu = 1;

        while (op != 0){
            opcionMenu = 1;
            
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
                                    Habitacion habitacion1 = null;

                                    JSONArray array1 = JsonManager.FileAJsonTokener("reservas.json");
                                    listaReservas = JsonManager.jsonArrayAMap(array1);

                                    JSONArray arr1 = JsonManager.FileAJsonTokener("habitaciones.json");
                                    listaHabitaciones = JsonManager.jsonArrayAHabitaciones(arr1);

                                    //ver habitaciones sucias
                                    for(Habitacion h : listaHabitaciones){
                                        if(h.getEstado() == Estado.DISPONIBLE){
                                            System.out.println(h.toString());
                                        }
                                    }


                                    System.out.println("Ingrese el numero de la habitacion a reservar:");
                                    int numeroHab = leer.nextInt();
                                    leer.nextLine();

                                    for(Habitacion h : listaHabitaciones){
                                        if(h.getNumero() == numeroHab){
                                            habitacion1 = h;
                                            habitacion1.setEstado(Estado.RESERVADO);
                                        }
                                    }

                                    Reserva reserva1 = new Reserva(pasajero,Reserva.generarFechaAleatoria(),Reserva.generarFechaAleatoria(),habitacion1);

                                    listaReservas.put(numeroHab,reserva1);

                                    array1 = JsonManager.mapAJsonArray(listaReservas);
                                    JsonManager.JsonArrayAFile(array1,"reservas.json");


                                case 2:
                                    System.out.println("Ingrese el número de habitación donde se realiza el consumo:");
                                    int numHabConsumo = leer.nextInt();
                                    leer.nextLine();

                                    System.out.println("Ingrese la descripción del consumo:");
                                    String descripcionConsumo = leer.nextLine();

                                    System.out.println("Ingrese el monto del consumo:");
                                    double montoConsumo = leer.nextDouble();
                                    leer.nextLine();

                                    Reserva reservaConsumo = listaReservas.get(numHabConsumo);
                                    if (reservaConsumo != null) {
                                        reservaConsumo.registrarConsumo(descripcionConsumo, montoConsumo);
                                        System.out.println("Consumo registrado exitosamente en la habitación número: " + numHabConsumo);
                                    } else {
                                        System.out.println("No existe una reserva activa para la habitación indicada.");
                                    }
                                    ;

                                case 0:
                                    opcionMenu = 0;
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
                            System.out.println("3. Realizar Check-In");
                            System.out.println("4. Realizar Check-Out");
                            System.out.println("5. Ver reservas");
                            System.out.println("0. Cerrar sesión");
                            System.out.println("---------------------------------------");

                            opcionMenu = leer.nextInt();
                            leer.nextLine();

                            switch (opcionMenu) {
                                case 1:
                                    //funciones para pasar archivo de habitaciones a lista de habitaciones
                                    JSONArray arr1 = JsonManager.FileAJsonTokener("habitaciones.json");
                                    listaHabitaciones = JsonManager.jsonArrayAHabitaciones(arr1);

                                    //ver habitaciones sucias
                                    for(Habitacion h : listaHabitaciones){
                                        if(h.isLimpia() == false){
                                            System.out.println(h.toString());
                                        }
                                    }


                                    System.out.println("Ingrese el numero de la habitacion a limpiar:");
                                    int numHab = leer.nextInt();
                                    leer.nextLine();

                                    for(Habitacion h : listaHabitaciones){
                                        if(h.getNumero()== numHab){
                                            h.setLimpia(true);
                                        }
                                    }

                                    //funciones para guardar lista de habitaciones en archivo
                                    arr1 = JsonManager.habitacionesAJsonArray(listaHabitaciones);
                                    JsonManager.JsonArrayAFile(arr1,"habitaciones.json");

                                    ;

                                case 2:
                                    JSONArray arr2 = JsonManager.FileAJsonTokener("habitaciones.json");
                                    listaHabitaciones = JsonManager.jsonArrayAHabitaciones(arr2);


                                    //ver habitaciones que necesitan ser reparadas
                                    for(Habitacion h : listaHabitaciones){
                                        if(h.isReparacion() == false){
                                            System.out.println(h.toString());
                                        }
                                    }


                                    System.out.println("Ingrese el numero de la habitacion a reparar:");
                                    int numHab1 = leer.nextInt();
                                    leer.nextLine();

                                    for(Habitacion h : listaHabitaciones){
                                        if(h.getNumero()== numHab1){
                                            h.setReparacion(true);
                                        }
                                    }


                                    arr2 = JsonManager.habitacionesAJsonArray(listaHabitaciones);
                                    JsonManager.JsonArrayAFile(arr2,"habitaciones.json");


                                case 3:

                                    JSONArray arr3 = JsonManager.FileAJsonTokener("reservas.json");
                                    listaReservas = JsonManager.jsonArrayAMap(arr3);

                                    System.out.println("Ingrese el numero de habitacion para realizar el Check-In:");
                                    int nHabitacion = leer.nextInt();
                                    leer.nextLine();


                                    for (Map.Entry<Integer, Reserva> entry : listaReservas.entrySet()) {
                                        if(entry.getKey() == nHabitacion){
                                            entry.getValue().getHabitacion().setEstado(Estado.OCUPADO);
                                        }
                                    }

                                    arr3 = JsonManager.mapAJsonArray(listaReservas);
                                    JsonManager.JsonArrayAFile(arr3,"reservas.json");

                                case 4:
                                    JSONArray arr4 = JsonManager.FileAJsonTokener("reservas.json");
                                    listaReservas = JsonManager.jsonArrayAMap(arr4);

                                    System.out.println("Ingrese el número de habitación para realizar el Check-Out:");
                                    int numHabitacionCheckout = leer.nextInt();
                                    leer.nextLine();

                                    // Verificar si existe una reserva para la habitación indicada
                                    Reserva reservaCheckout = listaReservas.get(numHabitacionCheckout);
                                    if (reservaCheckout != null) {
                                        Personal personal1 = new Personal();
                                        personal1.hacerCheckOut(reservaCheckout);

                                        // Actualizar los cambios
                                        arr4 = JsonManager.mapAJsonArray(listaReservas);
                                        JsonManager.JsonArrayAFile(arr4, "reservas.json");

                                        System.out.println("Check-Out realizado correctamente para la habitación número: " + numHabitacionCheckout);
                                    } else {
                                        System.out.println("No existe una reserva activa para el número de habitación indicado.");
                                    }
                                    ;

                                case 5:
                                    JSONArray arr5 = JsonManager.FileAJsonTokener("reservas.json");
                                    listaReservas = JsonManager.jsonArrayAMap(arr5);

                                    for (Map.Entry<Integer, Reserva> entry : listaReservas.entrySet()) {
                                        System.out.println("clave =" + entry.getKey() + ", valor =" + entry.getValue());
                                    }
                                    ;

                                case 0:
                                    opcionMenu = 0;
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


                        //AGREGAR METODO PARA CARGAR TREESET DESDE ARCHIVO
                        JSONArray arr = JsonManager.FileAJsonTokener("usuarios.json");
                        listaUsuarios = JsonManager.jsonArrayAListaUsuarios(arr);

                        listaUsuarios.add(pasajero1);

                        //SOBREESCRIBIR ARCHIVO CON EL USUARIO YA AGREGADO
                        arr = JsonManager.listaUsuariosAJsonArray(listaUsuarios);
                        JsonManager.JsonArrayAFile(arr,"usuarios.json");

                    }else{
                        Personal personal1 = new Personal();

                        System.out.println("Ingrese su nombre de usuario:");
                        personal1.setUsername(leer.nextLine());

                        System.out.println("Ingrese su contraseña:");
                        personal1.setPasswordHash((Usuario.hashPassword(leer.nextLine())));

                        personal1.setTipoIngreso(Rol.PERSONAL_LIMPIEZA);

                        System.out.println("Ingrese su nombre y apellido:");
                        personal1.setNombreApellido(leer.nextLine());


                        //AGREGAR METODO PARA CARGAR TREESET DESDE ARCHIVO
                        JSONArray arr = JsonManager.FileAJsonTokener("usuarios.json");
                        listaUsuarios = JsonManager.jsonArrayAListaUsuarios(arr);

                        listaUsuarios.add(personal1);

                        //SOBREESCRIBIR ARCHIVO CON EL USUARIO YA AGREGADO
                        arr = JsonManager.listaUsuariosAJsonArray(listaUsuarios);
                        JsonManager.JsonArrayAFile(arr,"usuarios.json");
                    }
                case 0:
                    break;
            }
        }
    }
}
