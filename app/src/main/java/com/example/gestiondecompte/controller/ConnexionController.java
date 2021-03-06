package com.example.gestiondecompte.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.gestiondecompte.R;
import com.example.gestiondecompte.utils.RequestManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;



public final class ConnexionController {

    private static ConnexionController instance = null;

    private ConnexionController() {
    }

    public static ConnexionController getInstance() {

        if(instance == null) {
            instance = new ConnexionController();
        }

        return instance;
    }

    public interface SuccessLoginListener {
        void onSuccessLoginListener();
    }

    public void connexion(Context context, String pseudo, String password, SuccessLoginListener listener) {

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                context.getResources().getString(R.string.url_spring) + "authentification",
                token -> {

                    SharedPreferences preferences =
                            context.getSharedPreferences("MesPreferences",Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("token", token);
                    editor.apply();

                    listener.onSuccessLoginListener();

                },
                erreur -> {
                    Toast.makeText(context, "Erreur de connexion", Toast.LENGTH_LONG).show();
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type","application/json; charset=UTF-8");
                return params;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                JSONObject jsonUtilisateur = new JSONObject();
                try {
                    jsonUtilisateur.put("login", pseudo);
                    jsonUtilisateur.put("password", password);
                    return jsonUtilisateur.toString().getBytes(StandardCharsets.UTF_8);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }
        };

        RequestManager.getInstance(context).addToRequestQueue(stringRequest);

    }
}
