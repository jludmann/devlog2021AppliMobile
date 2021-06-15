package com.example.gestiondecompte.controller;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.example.gestiondecompte.R;
import com.example.gestiondecompte.model.Compte;
import com.example.gestiondecompte.utils.RequestManager;
import com.example.gestiondecompte.utils.StringRequestWithToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class CompteController {

    private static CompteController instance = null;

    private CompteController() {
    }

    public static CompteController getInstance() {

        if(instance == null) {
            instance = new CompteController();
        }

        return instance;
    }

    public interface SuccesEcouteur {
        void onSuccesSauvegarde();
    }

    public interface ErreurEcouteur {
        void onErreurSauvegarde(String messageErreur);
    }

    public void sauvegarder(
            Context context,
            Compte compte,
            SuccesEcouteur ecouteurSucces,
            ErreurEcouteur ecouteurErreur) {

        StringRequestWithToken stringRequest = new StringRequestWithToken (
                context,
                Request.Method.POST,  context.getResources().getString(R.string.url_spring) + "user/compte",
                token -> {
                    ecouteurSucces.onSuccesSauvegarde();
                },
                error -> {
                    ecouteurErreur.onErreurSauvegarde("Impossible de sauvegarder");
                }
        ){

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {

                    JSONObject jsonBody;

                        jsonBody = compte.toJson();

                    return jsonBody.toString().getBytes(StandardCharsets.UTF_8);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }
        };

        RequestManager.getInstance(context).addToRequestQueue(stringRequest);

    }

}
