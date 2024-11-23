package com.example.Utils;

import com.example.Hotel.Clases.Habitacion;
import com.example.Hotel.Clases.Reserva;
import com.example.Hotel.Clases.enumeradores.Estado;
import com.example.Hotel.Clases.enumeradores.Tipo;
import com.example.Login.Clases.Usuario;
import com.example.Login.Enums.Rol;
import com.example.Personas.Clases.Administrador.Administrador;
import com.example.Personas.Clases.Pasajero.Pasajero;
import com.example.Personas.Clases.Personal.Personal;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;


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
            obj.put("limpia", h.getEstado().toString());
            obj.put("reparacion", h.getEstado().toString());

            jsonArray.put(obj);
        }
        return jsonArray;
    }

    public static JSONArray mapAJsonArray(Map<Integer, Reserva> reservas) {
        JSONArray jsonArray = new JSONArray();

        for (Map.Entry<Integer, Reserva> entry : reservas.entrySet()) {
            JSONObject reservaJson = new JSONObject();
            reservaJson.put("id", entry.getKey());

            Reserva reserva = entry.getValue();

            // Convertir datos de pasajero
            JSONObject pasajeroJson = new JSONObject();
            pasajeroJson.put("nombreApellido", reserva.getPasajero().getNombreApellido());
            pasajeroJson.put("dni", reserva.getPasajero().getDni());
            pasajeroJson.put("direccion", reserva.getPasajero().getDireccion());
            pasajeroJson.put("nacionalidad", reserva.getPasajero().getNacionalidad());

            // Convertir datos de habitacion
            JSONObject habitacionJson = new JSONObject();
            habitacionJson.put("numero", reserva.getHabitacion().getNumero());
            habitacionJson.put("tipo", reserva.getHabitacion().getTipo());

            // Agregar atributos a reservaJson
            reservaJson.put("pasajero", pasajeroJson);
            reservaJson.put("fechaInicio", reserva.getFechaInicio().toString());
            reservaJson.put("fechaFin", reserva.getFechaFin().toString());
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

                // Obtener los valores de cada campo
                String username = obj.getString("username");
                String passwordHash = obj.getString("passworHash");
                Rol tipoIngreso = Rol.valueOf(obj.getString("tipoIngreso"));
                String nombreApellido = obj.getString("nombreApellido");

                // Crear una instancia de Usuario y agregarla al TreeSet
                //ADMINISTRADOR, PERSONAL_LIMPIEZA, CLIENTE
                if(tipoIngreso.toString() == "ADMINISTRADOR"){
                    Administrador admin = new Administrador(username, passwordHash, tipoIngreso, nombreApellido);
                    listaUsuarios.add(admin);
                } else if (tipoIngreso.toString() == "CLIENTE") {
                    Pasajero pasajero = new Pasajero(username, passwordHash, tipoIngreso, nombreApellido);
                    listaUsuarios.add(pasajero);
                } else{
                    Personal personal = new Personal(username, passwordHash, tipoIngreso, nombreApellido);
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
            boolean limpia = obj.getBoolean("limpia");
            boolean reparacion = obj.getBoolean("reparacion");

            // Crear una instancia de Habitacion y agregarla al ArrayList
            Habitacion habitacion = new Habitacion(numero,tipo,estado,precio,limpia,reparacion);
            listaHabitaciones.add(habitacion);
        }
        return listaHabitaciones;
    }


    public static HashMap<Integer, Reserva> jsonArrayAMap(JSONArray jsonArray) {
        HashMap<Integer, Reserva> reservas = new HashMap<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject reservaJson = jsonArray.getJSONObject(i);

            // Obtener la clave "id" para el mapa
            int id = reservaJson.getInt("id");

            // Obtener los datos de pasajero
            JSONObject pasajeroJson = reservaJson.getJSONObject("pasajero");
            String nombreApellido = pasajeroJson.getString("nombreApellido");
            int dni = pasajeroJson.getInt("dni");
            String direccion = pasajeroJson.getString("direccion");
            String nacionalidad = pasajeroJson.getString("nacionalidad");
            Pasajero pasajero = new Pasajero(nombreApellido, dni, direccion, nacionalidad);

            // Obtener los datos de habitacion
            JSONObject habitacionJson = reservaJson.getJSONObject("habitacion");
            int numeroHabitacion = habitacionJson.getInt("numero");
            Tipo tipo = Tipo.valueOf(habitacionJson.getString("tipo"));
            Habitacion habitacion = new Habitacion(numeroHabitacion,tipo);

            // Obtener las fechas de la reserva
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



}

