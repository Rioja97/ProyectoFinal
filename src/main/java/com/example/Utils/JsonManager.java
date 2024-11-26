package com.example.Utils;

import com.example.Hotel.Clases.Habitacion;
import com.example.Hotel.Clases.Hotel;
import com.example.Hotel.Clases.Reserva;
import com.example.Hotel.Enum.Estado;
import com.example.Hotel.Enum.Tipo;
import com.example.Login.Clases.Usuario;
import com.example.Login.Enums.Rol;
import com.example.Login.Clases.Administrador;
import com.example.Login.Clases.Pasajero;
import com.example.Login.Clases.Personal;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;


public class JsonManager {


    // Convertir TreeSet a JSONArray
    public static JSONArray listaUsuariosAJsonArray(TreeSet<Usuario> listaUsuarios) {

        JSONArray jsonArray = new JSONArray();

        for (Usuario u: listaUsuarios){
            JSONObject obj = new JSONObject();

            obj.put("username", u.getUsername());
            obj.put("passworHash", u.getPassword());
            obj.put("tipoIngreso", u.getTipoIngreso().toString());
            obj.put("nombreApellido", u.getNombreApellido());

            jsonArray.put(obj);
        }
        return jsonArray;
    }


    public static JSONArray habitacionesAJsonArray(ArrayList<Habitacion> listaHabitaciones) {
        JSONArray jsonArray = new JSONArray();

        for (Habitacion h: listaHabitaciones){
            JSONObject obj = new JSONObject();

            obj.put("numero", h.getNumero());
            obj.put("tipo", h.getTipo().toString());
            obj.put("estado", h.getEstado().toString());
            obj.put("precio", h.getPrecio());
            obj.put("limpia", h.getLimpia());
            obj.put("reparacion", h.getReparacion());

            jsonArray.put(obj);
        }
        return jsonArray;
    }

    public static JSONArray reservasAJsonArray(Map<Integer, Reserva> reservas) {
        JSONArray jsonArray = new JSONArray();

        for (Map.Entry<Integer, Reserva> entry : reservas.entrySet()) {
            JSONObject reservaJson = new JSONObject();
            reservaJson.put("id", entry.getKey());

            Reserva reserva = entry.getValue();

            // Convertir datos de pasajero
            JSONObject pasajeroJson = new JSONObject();
            pasajeroJson.put("dni", reserva.getPasajero().getDni());
            pasajeroJson.put("direccion", reserva.getPasajero().getDireccion());
            pasajeroJson.put("nacionalidad", reserva.getPasajero().getNacionalidad());

            // Convertir datos de habitacion (todos los atributos)
            JSONObject habitacionJson = new JSONObject();
            habitacionJson.put("numero", reserva.getHabitacion().getNumero());
            habitacionJson.put("tipo", reserva.getHabitacion().getTipo().toString());  // Asegúrate de convertir a String
            habitacionJson.put("estado", reserva.getHabitacion().getEstado().toString()); // Agregado estado
            habitacionJson.put("precio", reserva.getHabitacion().getPrecio());  // Agregado precio
            habitacionJson.put("limpia", reserva.getHabitacion().getLimpia()); // Agregado limpia
            habitacionJson.put("reparacion", reserva.getHabitacion().getReparacion());  // Agregado reparacion

            // Agregar atributos a reservaJson
            reservaJson.put("pasajero", pasajeroJson);
            reservaJson.put("fechaInicio", reserva.getFechaInicio().toString());  // Convertir la fecha de inicio
            reservaJson.put("fechaFin", reserva.getFechaFin().toString());  // Convertir la fecha de fin
            reservaJson.put("habitacion", habitacionJson);

            jsonArray.put(reservaJson);
        }
        return jsonArray;
    }




    public static void JsonArrayAFile(JSONArray jsonArray, String nombreArchivo) {
        try (FileWriter fileWriter = new FileWriter(nombreArchivo)) {

            // Escribir el JSONArray en el archivo
            fileWriter.write(jsonArray.toString(4)); // 4 es la indentación para formato legible
            fileWriter.flush();
            System.out.println("Archivo JSON guardado en: " + nombreArchivo);

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }


    public static JSONArray FileAJsonArray(String filePath) {

        try (FileReader fileReader = new FileReader(filePath)) {
            // Crear un JSONTokener con el contenido del archivo
            JSONTokener tokener = new JSONTokener(fileReader);
            // Convertir el JSONTokener a un JSONArray

            return new JSONArray(tokener);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }
    }


    public static TreeSet<Usuario> jsonArrayAListaUsuarios(JSONArray jsonArray) {
        TreeSet<Usuario> listaUsuarios = new TreeSet<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            if(jsonArray == null){
                System.out.println("No se ha encontrado el archivo origen");
                return null;
            }

            // Obtener los valores de cada campo
            String username = obj.getString("username");
            String password = obj.getString("passworHash");
            Rol tipoIngreso = Rol.valueOf(obj.getString("tipoIngreso"));
            String nombreApellido = obj.getString("nombreApellido");

            // Crear una instancia de Usuario y agregarla al TreeSet
            //ADMINISTRADOR, PERSONAL_LIMPIEZA, CLIENTE
            if(tipoIngreso.equals(Rol.ADMINISTRADOR)){
                Administrador admin = new Administrador(username, password, tipoIngreso, nombreApellido);
                listaUsuarios.add(admin);
            } else if (tipoIngreso.equals(Rol.CLIENTE)) {
                Pasajero pasajero = new Pasajero(username, password, tipoIngreso, nombreApellido);
                listaUsuarios.add(pasajero);
            } else {
                Personal personal = new Personal(username, password, tipoIngreso, nombreApellido);
                listaUsuarios.add(personal);
            }
        }
        return listaUsuarios;
    }



    public static ArrayList<Habitacion> jsonArrayAHabitaciones(JSONArray jsonArray) {
        ArrayList<Habitacion> listaHabitaciones = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            // Obtener los valores de cada campo
            int numero = obj.getInt("numero");
            Tipo tipo = Tipo.valueOf(obj.getString("tipo"));
            Estado estado = Estado.valueOf(obj.getString("estado"));
            double precio = obj.getDouble("precio");
            boolean limpia = obj.getBoolean("limpia");  // Corregido, ahora se guarda correctamente
            boolean reparacion = obj.getBoolean("reparacion");  // Corregido, ahora se guarda correctamente

            // Crear una instancia de Habitacion y agregarla al ArrayList
            Habitacion habitacion = new Habitacion(numero, tipo, estado, precio, limpia, reparacion);
            listaHabitaciones.add(habitacion);
        }
        return listaHabitaciones;
    }



    public static HashMap<Integer, Reserva> jsonArrayAResevas(JSONArray jsonArray) {
        HashMap<Integer, Reserva> reservas = new HashMap<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject reservaJson = jsonArray.getJSONObject(i);

            // Obtener la clave "id" para el mapa
            int id = reservaJson.getInt("id");

            // Obtener los datos de pasajero
            JSONObject pasajeroJson = reservaJson.getJSONObject("pasajero");
            int dni = pasajeroJson.getInt("dni");
            String direccion = pasajeroJson.getString("direccion");
            String nacionalidad = pasajeroJson.getString("nacionalidad");
            Pasajero pasajero = new Pasajero(dni, direccion, nacionalidad);

            // Obtener los datos de habitacion (todos los atributos)
            JSONObject habitacionJson = reservaJson.getJSONObject("habitacion");
            int numeroHabitacion = habitacionJson.getInt("numero");
            Tipo tipo = Tipo.valueOf(habitacionJson.getString("tipo"));
            Estado estado = Estado.valueOf(habitacionJson.getString("estado"));  // Convertir el estado de String a Estado
            double precio = habitacionJson.getDouble("precio");  // Obtener el precio
            boolean limpia = habitacionJson.getBoolean("limpia");  // Obtener el estado de limpieza
            boolean reparacion = habitacionJson.getBoolean("reparacion");  // Obtener el estado de reparación
            Habitacion habitacion = new Habitacion(numeroHabitacion, tipo, estado, precio, limpia, reparacion);

            // Obtener las fechas de la reserva (convertir de String a LocalDate)
            LocalDate fechaInicio = LocalDate.parse(reservaJson.getString("fechaInicio"));
            LocalDate fechaFin = LocalDate.parse(reservaJson.getString("fechaFin"));

            // Crear una instancia de Reserva y agregarla al mapa
            Reserva reserva = new Reserva(pasajero, fechaInicio, fechaFin, habitacion);
            reservas.put(id, reserva);
        }
        return reservas;
    }




    public static boolean comprobarExistenciaArchivo(String nombreArchivo) {
        Path path = Paths.get(nombreArchivo);
        boolean exists = Files.exists(path);

        return exists;
    }

    public static void crearArchivo(String nombreArchivo) {
        try {
            File file = new File(nombreArchivo);
            if (file.createNewFile()) {
                System.out.println("Archivo creado exitosamente: " + file.getAbsolutePath());
            } else {
                System.out.println("El archivo ya existe: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    public static void administrarArchivos(String nombreArchivo) {
        if (!comprobarExistenciaArchivo(nombreArchivo)) {

            crearArchivo(nombreArchivo);
        } else {
            System.out.println("El archivo ya existe.");
        }
    }



    public static Pasajero encontrarPasajeroArchivo(String userName) {

        // Leer el arreglo JSON del archivo
        JSONArray array = JsonManager.FileAJsonArray("usuarios.json");

        // Convertir el arreglo JSON a una lista de objetos Usuario
        TreeSet<Usuario> listaUsuarios = new TreeSet<>(JsonManager.jsonArrayAListaUsuarios(array));

        // Iterar sobre la lista de usuarios
        for (Usuario usuario : listaUsuarios) {
            // Comprobar si el usuario es una instancia de Pasajero y si el nombre de usuario coincide
            if (usuario instanceof Pasajero && usuario.getUsername().equals(userName)) {
                // Realizar el casting a Pasajero y devolverlo
                return (Pasajero) usuario;
            }
        }
        throw new NoSuchElementException("No se ha encontrado el usuario especificado.");
    }


    public static Habitacion encontrarHabitacionArchivo(int idHabitacion) {

        JSONArray array = JsonManager.FileAJsonArray("habitaciones.json");

        ArrayList<Habitacion> listaHabitaciones = new ArrayList<>(JsonManager.jsonArrayAHabitaciones(array));

        for (Habitacion habitacion : listaHabitaciones) {
            if (habitacion.getNumero() == idHabitacion) {
                return habitacion;
            }
        }
            throw new NoSuchElementException("No se ha encontrado la habitacion especificada.");
    }


    public static Pasajero encontrarPasajeroHotel(String userName, Hotel hotel) {

        TreeSet<Usuario> listaUsuarios = hotel.getUsuarios();

        // Iterar sobre la lista de usuarios
        for (Usuario usuario: listaUsuarios) {
            // Comprobar si el usuario es una instancia de Pasajero y si el nombre de usuario coincide
            if (usuario instanceof Pasajero && usuario.getUsername().equals(userName)) {
                // Realizar el casting a Pasajero y devolverlo
                return (Pasajero) usuario;
            }
        }
            throw new NoSuchElementException("No se ha encontrado el usuario especificado.");
    }



    public static Habitacion encontrarHabitacionHotel(int idHabitacion, Hotel hotel) {

        for (Habitacion habitacion : hotel.getHabitaciones()) {
            if (habitacion.getNumero() == idHabitacion) {
                return habitacion;
            }
        }
        throw new NoSuchElementException("No se ha encontrado la habitacion especificada.");
    }


    public static Reserva encontrarReservaHotel(int idHabitacion, Hotel hotel) {

        for (Reserva reserva : hotel.getReservas().values()) {
            if (reserva.getHabitacion().getNumero() == idHabitacion) {
                return reserva;
            }
        }
        throw new NoSuchElementException("No se ha encontrado la reserva especificada.");
    }


}

