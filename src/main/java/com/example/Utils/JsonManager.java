package com.example.Utils;

import com.example.Hotel.Clases.Reserva;
import com.example.Login.Clases.Usuario;
import com.example.Personas.Clases.Administrador.Administrador;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import org.json.JSONTokener;

public class JsonManager {


    // Convertir TreeSet a JSONArray
    public static JSONArray listaUsuariosAJsonArray(TreeSet<Usuario> listaUsuarios) {

        JSONArray jsonArray = new JSONArray();

        for (Usuario u: listaUsuarios){
            JSONObject obj = new JSONObject();

            obj.put("username", u.getUsername());
            obj.put("passworHash", u.getPasswordHash());
            obj.put("tipoIngreso", u.getTipoIngreso().toString());
            obj.put("nombreApellido", u.getNombreApellido());

            jsonArray.put(obj);
        }
        return jsonArray;
    }

    public static JSONArray listaReservasAJsonArray(ArrayList<Reserva> listaReservas) {

        JSONArray jsonArray = new JSONArray();

        for (Reserva r: listaReservas){
            JSONObject obj = new JSONObject();

            obj.put("", r.getNombreApellido());
            obj.put("fechaIngreso", r.ge());
            obj.put("fechaSalida", r.getFechaSalida());
            obj.put("precio", r.getPrecio);

            jsonArray.put(obj);
        }
        return jsonArray;
    }



}
