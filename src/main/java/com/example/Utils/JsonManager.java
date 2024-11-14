package com.example.Utils;

import com.example.Login.Clases.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.TreeSet;


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

    public static JSONArray habitacionesAJsonArray(ArrayList<Usuario> listaUsuarios) {}

    //Convertir JSONArray en TreeSet
    public TreeSet<Usuario> JsonArrayAlistaUsuarios(JSONArray jarray){
        TreeSet<Usuario> listaUsuarios = new TreeSet<>();

        try {
            for(int i = 0; i< jarray.length();i++){
                if()
                Usuario usuario = gestor.deserializar(jarray.getJSONObject(i));

                curso.agregarPersonaACurso(persona);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        return curso;
    }

    }

