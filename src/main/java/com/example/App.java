package com.example;

import com.example.Hotel.Clases.Habitacion;
import com.example.Hotel.Clases.Reserva;
import com.example.Hotel.Clases.enumeradores.Estado;
import com.example.Hotel.Clases.enumeradores.Tipo;
import com.example.Login.Clases.LoginManager;
import com.example.Login.Clases.Usuario;
import com.example.Login.Enums.Rol;
import com.example.Menu.Menu;
import com.example.Personas.Clases.Administrador.Administrador;
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
        int opUser = 1;

        while (op != 0){
            opcionMenu = 1;
            
            System.out.println("-----------------------------------");
            System.out.println("Bienvenido al HOTEL:");
            System.out.println("    1. Iniciar Sesion como cliente");
            System.out.println("    2. Iniciar Sesion como personal del hotel");
            System.out.println("    3. Registrarse");
            System.out.println("    0. Salir del programa.");
            System.out.println("");
            System.out.println("-----------------------------------");
            System.out.println("Ingrese una opcion:");
            op = leer.nextInt();
            leer.nextLine();

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
                            System.out.println("2. Modificar Usuario");
                            System.out.println("3. Eliminar Usuario");
                            System.out.println("4. Ver estado de las habitaciones");
                            System.out.println("0. Cerrar sesión");
                            System.out.println("---------------------------------------");

                            opcionMenu = leer.nextInt();
                            leer.nextLine();

                            switch (opcionMenu) {
                                case 1:
                                    while (opUser != 0) {
                                        System.out.println("---------------------------------------");
                                        System.out.println("1. Crear administrador");
                                        System.out.println("2. Crear cliente");
                                        System.out.println("3. Crear Personal de hotel");
                                        System.out.println("0. Volver");
                                        System.out.println("---------------------------------------");
                                        System.out.println("");

                                        opUser = leer.nextInt();
                                        leer.nextLine();

                                        switch (opUser) {
                                            case 1:
                                                Administrador admin0 = new Administrador();

                                                System.out.println("Ingrese su nombre de usuario:");
                                                admin0.setUsername(leer.nextLine());

                                                System.out.println("Ingrese su contraseña:");
                                                admin0.setPasswordHash((Usuario.hashPassword(leer.nextLine())));

                                                admin0.setTipoIngreso(Rol.ADMINISTRADOR);

                                                System.out.println("Ingrese su nombre y apellido:");
                                                admin0.setNombreApellido(leer.nextLine());

                                                String msg = Menu.agregarUsuarioAdmin(admin0, listaUsuarios);

                                                System.out.println(msg);

                                                break;

                                            case 2:
                                                Pasajero pasajero0 = new Pasajero();

                                                System.out.println("Ingrese su nombre de usuario:");
                                                pasajero0.setUsername(leer.nextLine());

                                                System.out.println("Ingrese su contraseña:");
                                                pasajero0.setPasswordHash((Usuario.hashPassword(leer.nextLine())));

                                                pasajero0.setTipoIngreso(Rol.CLIENTE);

                                                System.out.println("Ingrese su nombre y apellido:");
                                                pasajero0.setNombreApellido(leer.nextLine());

                                                System.out.println("Ingrese su DNI:");
                                                pasajero0.setDni(leer.nextInt());
                                                leer.nextLine();

                                                System.out.println("Ingrese su direccion:");
                                                pasajero0.setDireccion(leer.nextLine());

                                                System.out.println("Ingrese su nacionalidad:");
                                                pasajero0.setNacionalidad(leer.nextLine());

                                                String msg1 = Menu.agregarUsuarioAdmin(pasajero0, listaUsuarios);

                                                System.out.println(msg1);
                                                break;


                                            case 3:
                                                Personal personal0 = new Personal();

                                                System.out.println("Ingrese su nombre de usuario:");
                                                personal0.setUsername(leer.nextLine());

                                                System.out.println("Ingrese su contraseña:");
                                                personal0.setPasswordHash((Usuario.hashPassword(leer.nextLine())));

                                                personal0.setTipoIngreso(Rol.PERSONAL_LIMPIEZA);

                                                System.out.println("Ingrese su nombre y apellido:");
                                                personal0.setNombreApellido(leer.nextLine());

                                                String msg2 = Menu.agregarUsuarioAdmin(personal0, listaUsuarios);

                                                System.out.println(msg2);
                                                break;


                                            case 0:
                                                opUser = 0;
                                                break;

                                        }
                                    }
                                    break;

                                case 2:
                                    JSONArray arr = JsonManager.FileAJsonTokener("usuarios.json");
                                    listaUsuarios = JsonManager.jsonArrayAListaUsuarios(arr);
                                    Usuario user1 = null;


                                    for (Usuario u : listaUsuarios) {
                                        System.out.println(u.toString());
                                    }

                                    System.out.println("Ingrese el username del usuario a modificar:");
                                    String nombreUsuario = leer.nextLine();

                                    for (Usuario u : listaUsuarios) {
                                        if (u.getUsername().equals(nombreUsuario)) {
                                            user1 = u;
                                        }
                                    }

                                    arr = JsonManager.listaUsuariosAJsonArray(listaUsuarios);
                                    JsonManager.JsonArrayAFile(arr, "usuarios.json");


                                    System.out.println("Ingrese la nueva contraseña:");
                                    String nuevaContrasenia = leer.nextLine();

                                    System.out.println("Ingrese el nuevo rol:");
                                    Rol nuevoRol = gestorLogin.agregarRol(leer.nextLine());

                                    System.out.println("Ingrese su nuevo nombre y apellido:");
                                    String nuevoNombre = leer.nextLine();

                                    String msg0 = Menu.modificarUsuarioAdmin(user1, nuevaContrasenia, nuevoRol, nuevoNombre, listaUsuarios);

                                    System.out.println(msg0);
                                    break;

                                case 3:
                                    JSONArray array0 = JsonManager.FileAJsonTokener("usuarios.json");
                                    listaUsuarios = JsonManager.jsonArrayAListaUsuarios(array0);
                                    Usuario user2 = null;


                                    for (Usuario u : listaUsuarios) {
                                        System.out.println(u.toString());
                                    }

                                    System.out.println("Ingrese el username del usuario a eliminar:");
                                    String nomUsuario = leer.nextLine();

                                    for (Usuario usuario : listaUsuarios) {
                                        if (usuario.getUsername().equals(nomUsuario)) {
                                            user2 = usuario;
                                        }
                                    }

                                    arr = JsonManager.listaUsuariosAJsonArray(listaUsuarios);
                                    JsonManager.JsonArrayAFile(arr, "usuarios.json");

                                    String msg3 = Menu.eliminarUsuarioAdmin(user2, listaUsuarios);

                                    System.out.println(msg3);
                                    break;

                                case 4:
                                    JSONArray array1 = JsonManager.FileAJsonTokener("reservas.json");
                                    listaReservas = JsonManager.jsonArrayAMap(array1);

                                    JSONArray arr1 = JsonManager.FileAJsonTokener("habitaciones.json");
                                    listaHabitaciones = JsonManager.jsonArrayAHabitaciones(arr1);

                                    //ver habitaciones
                                    for(Habitacion h : listaHabitaciones){
                                        System.out.println(h.toString());
                                        System.out.println("\n");
                                    }

                                    break;

                                case 0:
                                    opcionMenu = 0;
                                    break;
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
                                            System.out.println("\n");
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

                                    break;

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
                                    break;

                                case 0:
                                    opcionMenu = 0;
                                    break;
                            }
                        }
                    }
                    break;

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
                                            System.out.println("\n");
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

                                    break;

                                case 2:
                                    JSONArray arr2 = JsonManager.FileAJsonTokener("habitaciones.json");
                                    listaHabitaciones = JsonManager.jsonArrayAHabitaciones(arr2);


                                    //ver habitaciones que necesitan ser reparadas
                                    for(Habitacion h : listaHabitaciones){
                                        if(h.isReparacion() == false){
                                            System.out.println(h.toString());
                                            System.out.println("\n");
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

                                    break;

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

                                    break;

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
                                    break;

                                case 5:
                                    JSONArray arr5 = JsonManager.FileAJsonTokener("reservas.json");
                                    listaReservas = JsonManager.jsonArrayAMap(arr5);

                                    for (Map.Entry<Integer, Reserva> entry : listaReservas.entrySet()) {
                                        System.out.println("clave =" + entry.getKey() + ", valor =" + entry.getValue());
                                    }
                                    break;

                                case 0:
                                    opcionMenu = 0;
                                    break;
                            }
                        }
                    }
                    break;
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

                    }
                    if(rol == 2){
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
