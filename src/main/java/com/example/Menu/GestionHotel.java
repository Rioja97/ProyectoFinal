package com.example.Menu;

import com.example.Excepciones.DNIInvalidoException;
import com.example.Excepciones.FechaInvalidaException;
import com.example.Excepciones.HabitacionNoDisponibleException;
import com.example.Excepciones.SoloLetrasException;
import com.example.Hotel.Clases.Habitacion;
import com.example.Hotel.Clases.Hotel;
import com.example.Hotel.Clases.Reserva;
import com.example.Hotel.Enum.Estado;
import com.example.Hotel.Enum.Tipo;
import com.example.Login.Clases.LoginManager;
import com.example.Login.Clases.Usuario;
import com.example.Login.Enums.Rol;
import com.example.Login.Clases.Administrador;
import com.example.Login.Clases.Pasajero;
import com.example.Login.Clases.Personal;
import com.example.Login.LoginExceptions.UsuarioRepetidoException;
import com.example.Utils.JsonManager;
import com.example.Utils.Validaciones;
import org.json.JSONArray;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public final class GestionHotel {

    Hotel lasVegas = new Hotel();

    public GestionHotel() {

        JSONArray array = new JSONArray(JsonManager.FileAJsonArray("usuarios.json"));
        TreeSet<Usuario> usuarios = new TreeSet<>(JsonManager.jsonArrayAListaUsuarios(array));
        lasVegas.setUsuarios(usuarios);

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
                    Usuario usuario = LoginManager.iniciarSesionConReintentos();

                    if(usuario.getTipoIngreso().name().equals("CLIENTE")){
                        System.out.println("Aca va el menu de usuarios");
                        mostrarMenuClienteReservas((Pasajero) usuario);

                    } else if(usuario.getTipoIngreso().name().equals("ADMINISTRADOR")) {
                        System.out.println("Aca va el menu de adminitradores");
                        mostrarMenuAdministradores((Administrador) usuario);

                    } else {
                        System.out.println("Aca va el menu de personal");
                        mostrarMenuPersonal((Personal) usuario);
                    }

                case 2:

                    try {
                        Pasajero pasajero = crearPasajero();
                        lasVegas.agregarUsuario(pasajero, lasVegas);
                    } catch (UsuarioRepetidoException e) {
                        System.out.println(e.getMessage());
                    }catch (SoloLetrasException e){
                        System.out.println(e.getMessage());
                    }catch (DNIInvalidoException e){
                        System.out.println(e.getMessage());
                    }

                    usuarios = lasVegas.getUsuarios();
                    array = JsonManager.listaUsuariosAJsonArray(usuarios);
                    JsonManager.JsonArrayAFile(array, "usuarios.json");
                    break;

                case 3:
                    System.out.println("Cerrando programa...");
                    opcion = 0;
                    break;

                default:
                    System.out.println("Opcion inválida");
                    break;
            }
        }
    }


    private void mostrarMenuAdministradores(Administrador administrador) {

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
                    mostrarMenuGestionUsuarios(administrador);
                    break;

                case 2:
                    mostrarMenuGestionHotel();
                    break;

                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        }
    }


    private void mostrarMenuGestionUsuarios(Administrador administrador) {
        int opcionMenuUsuarios = -1;

        JSONArray array = new JSONArray(JsonManager.FileAJsonArray("usuarios.json"));
        TreeSet<Usuario> usuarios = new TreeSet<>(JsonManager.jsonArrayAListaUsuarios(array));
        lasVegas.setUsuarios(usuarios);

        while (opcionMenuUsuarios != 0) {
            System.out.println("---------------------------------------");
            System.out.println("1. Ver Usuarios");
            System.out.println("2. Agregar Usuario");
            System.out.println("3. Modificar Usuario");
            System.out.println("4. Eliminar Usuario");
            System.out.println("\n");
            System.out.println("5. Aplicar cambios");
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
                    try {
                        System.out.println(lasVegas.agregarUsuario(crearPersonaloAdmin(), administrador, lasVegas));
                    } catch (UsuarioRepetidoException e) {
                        System.out.println(e.getMessage());
                    } catch (SoloLetrasException e) {
                        System.out.println(e.getMessage());;
                    }
                    break;

                case 3:
                    try {
                        System.out.println("Ingrese el nombre de usuario a modificar: ");
                        String username = leer.nextLine();
                        System.out.println(lasVegas.modificarUsuario(crearPersonaloAdmin(),username, administrador, lasVegas));
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    } catch (SoloLetrasException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.println("Ingrese el nombre de usuario a modificar: ");
                        String username1 = leer.nextLine();
                        System.out.println(lasVegas.eliminarUsuario(username1, administrador, lasVegas));
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    //System.out.println("Opción no válida, por favor intente nuevamente.");
            }
                usuarios = lasVegas.getUsuarios();
                array = JsonManager.listaUsuariosAJsonArray(usuarios);
                JsonManager.JsonArrayAFile(array, "usuarios.json");
        }
    }


    private void mostrarMenuGestionHotel() {
        int opcionMenuUsuarios = -1;

        JSONArray array = JsonManager.FileAJsonArray("habitaciones.json");
        ArrayList<Habitacion> habitaciones = new ArrayList(JsonManager.jsonArrayAHabitaciones(array));
        lasVegas.setHabitaciones(habitaciones);

        JSONArray array1 = JsonManager.FileAJsonArray("reservas.json");
        HashMap<Integer, Reserva> reservas = new HashMap<>(JsonManager.jsonArrayAResevas(array1));
        lasVegas.setReservas(reservas);

        while (opcionMenuUsuarios != 0) {
            System.out.println("---------------------------------------");
            System.out.println("1. Añadir habitacion");
            System.out.println("2. Quitar habitacion");
            System.out.println("3. Modificar habitacion");
            System.out.println("4. Ver todas las habitaciones");
            System.out.println("5. Ver todas las reservas");
            System.out.println("0. Volver al menú anterior");
            System.out.println("---------------------------------------");

            Scanner leer = new Scanner(System.in);
            opcionMenuUsuarios = leer.nextInt();
            leer.nextLine(); // Limpiar el buffer

            switch (opcionMenuUsuarios) {
                case 1:
                    try {
                        System.out.println(lasVegas.agregarHabitacion(crearHabitacion()));
                    } catch (HabitacionNoDisponibleException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.println(lasVegas.getHabitaciones());
                        System.out.println("Ingrese el numero de la habitación que desea eliminar: ");
                        int numero = leer.nextInt();
                        leer.nextLine();
                        System.out.println(lasVegas.eliminarHabitacion(numero));
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        System.out.println(lasVegas.getHabitaciones().toString());
                        System.out.println("Ingrese el numero de la habitación que desea modificar: ");
                        int numeroH = leer.nextInt();
                        leer.nextLine();
                        lasVegas.modificarHabitacion(numeroH, crearHabitacion());
                    } catch (NoSuchElementException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println(lasVegas.getHabitaciones().toString());
                    break;

                case 5:
                    System.out.println(lasVegas.getReservas());
                    break;

                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;

                default:
                    //System.out.println("Opción no válida, por favor intente nuevamente.");
                    break;
            }

        habitaciones = lasVegas.getHabitaciones();
        array = JsonManager.habitacionesAJsonArray(habitaciones);
        JsonManager.JsonArrayAFile(array, "habitaciones.json");
        }
    }


    private void mostrarMenuPersonal(Personal personal) {

        int opcionMenuPersonal = -1;
        Scanner scan = new Scanner(System.in);

        JSONArray array = JsonManager.FileAJsonArray("habitaciones.json");
        ArrayList<Habitacion> habitaciones = new ArrayList(JsonManager.jsonArrayAHabitaciones(array));
        lasVegas.setHabitaciones(habitaciones);

        JSONArray array1 = JsonManager.FileAJsonArray("reservas.json");
        HashMap<Integer, Reserva> reservas = new HashMap<>(JsonManager.jsonArrayAResevas(array1));
        lasVegas.setReservas(reservas);


        while (opcionMenuPersonal != 0) {
            System.out.println("---------------------------------------");
            System.out.println("1. Realizar check-in");
            System.out.println("2. Realizar check-out");
            System.out.println("3. Realizar limpieza");
            System.out.println("4. Realizar reparacion");
            System.out.println("0. Volver al Menú Principal");
            System.out.println("---------------------------------------");

            Scanner leer = new Scanner(System.in);
            opcionMenuPersonal = leer.nextInt();
            leer.nextLine(); // Limpiar el buffer

            switch (opcionMenuPersonal) {
                case 1:
                    System.out.println("Ingrese el numero de la habitación: ");
                    int numeroHabitacion = scan.nextInt();
                    scan.nextLine();
                    try{
                        personal.hacerCheckIn(JsonManager.encontrarReservaHotel(numeroHabitacion, lasVegas), lasVegas);
                    }catch(HabitacionNoDisponibleException e){
                        System.out.println("Numero de habitacion invalido");
                    }
                    break;

                case 2:
                    System.out.println(lasVegas.obtenerHabitacionesNoDisponibles());
                    System.out.println("Ingrese el numero de la habitación: ");
                    int numeroHabitacion2 = scan.nextInt();
                    scan.nextLine();
                    try{
                        personal.hacerCheckOut(JsonManager.encontrarHabitacionHotel(numeroHabitacion2, lasVegas));
                    }catch(NoSuchElementException e){
                        System.out.println("Numero de habitacion invalido");
                    }
                    break;

                case 3:
                    System.out.println(lasVegas.obtenerHabitacionesNoDisponibles());
                    System.out.println("Ingrese el numero de la habitación: ");
                    int numeroHabitacion3 = scan.nextInt();
                    scan.nextLine();
                    for(Habitacion h : lasVegas.getHabitaciones()){
                        if(h.getLimpia()==false){
                            personal.limpiarHabitacion(JsonManager.encontrarHabitacionHotel(numeroHabitacion3, lasVegas));
                            break;
                        }
                    }
                    System.out.println("La habitacion ya esta limpia");
                    break;
                case 4:
                    System.out.println(lasVegas.obtenerHabitacionesNoDisponibles());
                    System.out.println("Ingrese el numero de la habitación: ");
                    int numeroHabitacion4 = scan.nextInt();
                    scan.nextLine();
                    for(Habitacion h : lasVegas.getHabitaciones()){
                        if(h.getReparacion()==true){
                            personal.repararHabitacion(JsonManager.encontrarHabitacionHotel(numeroHabitacion4, lasVegas));
                            break;
                        }
                    }
                    System.out.println("La habitacion no necesita reparacion");
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;

                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
            habitaciones = lasVegas.getHabitaciones();
            array = JsonManager.habitacionesAJsonArray(habitaciones);
            JsonManager.JsonArrayAFile(array, "habitaciones.json");

            reservas = lasVegas.getReservas();
            array = JsonManager.reservasAJsonArray(reservas);
            JsonManager.JsonArrayAFile(array, "reservas.json");
        }
    }



    private  void mostrarMenuClienteReservas(Pasajero usuario) {

        Scanner leer = new Scanner(System.in);

        int opcionMenuReservas = -1;
        JSONArray array = new JSONArray(JsonManager.FileAJsonArray("reservas.json"));
        HashMap<Integer, Reserva> reservas = new HashMap<>(JsonManager.jsonArrayAResevas(array));
        lasVegas.setReservas(reservas);

        JSONArray arrayHabitaciones = new JSONArray(JsonManager.FileAJsonArray("habitaciones.json"));
        ArrayList<Habitacion> habitaciones = new ArrayList<>(JsonManager.jsonArrayAHabitaciones(arrayHabitaciones));
        lasVegas.setHabitaciones(habitaciones);


        while (opcionMenuReservas != 0) {
            System.out.println("---------------------------------------");
            System.out.println("1. Ver Reservas");
            System.out.println("2. Realizar Reserva");
            System.out.println("3. Cancelar Reserva");
            System.out.println("4. Ver Habitaciones disponibles");
            System.out.println("0. Volver al menú principal");
            System.out.println("---------------------------------------");

            opcionMenuReservas = leer.nextInt();
            leer.nextLine();

            switch (opcionMenuReservas) {
                case 1:
                    if(lasVegas.getReservas().isEmpty()){
                        System.out.println("No hay ninguna reserva, realice una para verlas");
                    } else{
                        System.out.println(lasVegas.getReservas().toString());
                    }
                    break;

                case 2:
                    try {
                        Reserva nuevaReserva = crearReserva(usuario, lasVegas);
                        lasVegas.realizarReserva(nuevaReserva);

                        // Guardar reservas actualizadas en el archivo JSON
                        array = JsonManager.reservasAJsonArray(lasVegas.getReservas());
                        JsonManager.JsonArrayAFile(array, "reservas.json");

                        System.out.println("Reserva realizada y guardada correctamente.");
                    } catch (FechaInvalidaException e) {
                        System.out.println("Fecha invalida");
                    } catch (HabitacionNoDisponibleException e) {
                        System.out.println("Habitacion no disponible");;
                    }
                    break;

                case 3:
                    System.out.println("Ingrese el número de la habitación para cancelar la reserva: ");
                    int numeroHabitacion = leer.nextInt();
                    leer.nextLine();

                    try {
                        String resultado = lasVegas.cancelarReserva(numeroHabitacion, lasVegas);
                        System.out.println(resultado);

                        // Guardar reservas actualizadas en el archivo JSON
                        array = JsonManager.reservasAJsonArray(lasVegas.getReservas());
                        JsonManager.JsonArrayAFile(array, "reservas.json");

                        System.out.println("Reserva cancelada y guardada correctamente.");
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println(lasVegas.obtenerHabitacionesDisponibles());
                    break;

                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;

                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        array = JsonManager.reservasAJsonArray(reservas);
        JsonManager.JsonArrayAFile(array, "reservas.json");

        arrayHabitaciones = JsonManager.habitacionesAJsonArray(habitaciones);
        JsonManager.JsonArrayAFile(arrayHabitaciones, "habitaciones.json");
        }


    }


     private  Pasajero crearPasajero() throws SoloLetrasException, DNIInvalidoException {

        Pasajero pasajero = new Pasajero();
        Scanner leer = new Scanner(System.in);

        System.out.println("Ingrese su nombre de usuario:");
        pasajero.setUsername(leer.nextLine());

        System.out.println("Ingrese la contraseña: ");
        pasajero.setPassword(leer.nextLine());

        System.out.println("Ingrese su nombre y apellido:");
        pasajero.setNombreApellido(leer.nextLine());
        Validaciones.validarNombre(pasajero.getNombreApellido());


        System.out.println("Ingrese su DNI:");
         String dni = leer.nextLine();
         Validaciones.validarDNI(dni);
         pasajero.setDni(Integer.parseInt(dni));

        System.out.println("Ingrese su direccion:");
        pasajero.setDireccion(leer.nextLine());

        System.out.println("Ingrese su nacionalidad:");
        pasajero.setNacionalidad(leer.nextLine());
         if(!(pasajero.getNacionalidad().matches("^[a-zA-Z]+"))){
             throw new SoloLetrasException("Solo se permiten letras.");
         }


        return pasajero;
    }

    public <T extends Usuario> Usuario crearPersonaloAdmin()throws SoloLetrasException{

        Scanner scan = new Scanner(System.in);

        System.out.println("Creación del usuario...");
        System.out.println("    Ingrese el nombre del usuario: ");
        String username = scan.nextLine();

        System.out.println("    Ingrese la contraseña del usuario: ");
        String password = scan.nextLine();

        System.out.println("    Ingrese el nombre y apellido del usuario: ");
        String nombreApellido = scan.nextLine();
        Validaciones.validarNombre(nombreApellido);


        System.out.println("Ingrese el rol del usuario nuevo: ");
        System.out.println("    1. Personal general del hotel");
        System.out.println("    2. Administrador de sistema");
        System.out.println("    Su opcion: ");

        int opcion = scan.nextInt();
        scan.nextLine();

        if(opcion == 1){
            Personal personal = new Personal(username, password, Rol.PERSONAL_LIMPIEZA, nombreApellido);
            return (Personal)personal;
        } else{
            Administrador administrador = new Administrador(username, password, Rol.ADMINISTRADOR, nombreApellido);
            return (Administrador)administrador;
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

    private static Reserva crearReserva(Pasajero pasajero, Hotel hotel) throws FechaInvalidaException {
        Scanner sc = new Scanner(System.in);

        // Solicitar número de habitación
        System.out.print("Ingrese el número de la habitación: ");
        int numeroHabitacion = sc.nextInt();

        Habitacion habitacion1 = null;
        try {
            // Buscar la habitación en el hotel
            habitacion1 = JsonManager.encontrarHabitacionHotel(numeroHabitacion, hotel);
            // Si no se encuentra la habitación, lanzamos una excepción personalizada o mostramos un mensaje
            if (habitacion1 == null) {
                throw new NoSuchElementException("La habitación con el número " + numeroHabitacion + " no fue encontrada.");
            }
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
            return null; // O puedes lanzar la excepción si lo prefieres
        }

        // Solicitamos las fechas al usuario
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaInicio = null;
        LocalDate fechaFin = null;

        // Limpiar el buffer
        sc.nextLine(); // Limpiar el salto de línea residual

        try {
            // Solicitar fecha de inicio
            System.out.print("\nIntroduce la fecha de inicio (yyyy-MM-dd): ");
            fechaInicio = LocalDate.parse(sc.nextLine(), formatter);

            // Solicitar fecha de fin
            System.out.print("Introduce la fecha de fin (yyyy-MM-dd): ");
            fechaFin = LocalDate.parse(sc.nextLine(), formatter);

            // Validar que la fecha de fin no sea anterior a la fecha de inicio
            if (fechaFin.isBefore(fechaInicio)) {
                throw new FechaInvalidaException("La fecha de fin no puede ser menor a la fecha de comienzo");
            }

        } catch (FechaInvalidaException e) {
            // Manejo de excepciones si la fecha es inválida
            System.err.println("Error al ingresar la fecha: " + e.getMessage());
            return null; // O puedes lanzar una excepción personalizada si lo prefieres
        }

        // Crear y devolver la nueva reserva
        return new Reserva(pasajero, fechaInicio, fechaFin, habitacion1);
    }

}

