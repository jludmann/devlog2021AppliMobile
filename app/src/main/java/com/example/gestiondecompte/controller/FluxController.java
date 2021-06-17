package com.example.gestiondecompte.controller;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.example.gestiondecompte.R;
import com.example.gestiondecompte.model.Compte;
import com.example.gestiondecompte.model.Flux;
import com.example.gestiondecompte.model.FluxExceptionnel;
import com.example.gestiondecompte.utils.RequestManager;
import com.example.gestiondecompte.utils.StringRequestWithToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;

public class FluxController {

    private static FluxController instance = null;

    private FluxController() {
    }

    public static FluxController getInstance() {

        if(instance == null) {
            instance = new FluxController();
        }

        return instance;
    }

    public interface SuccesEcouteur {
        void onSuccesSauvegarde();
    }

    public interface ErreurEcouteur {
        void onErreurSauvegarde(String messageErreur);
    }

    public void sauvegarderFluxExceptionnel(
            Context context,
            FluxExceptionnel flux,
            SuccesEcouteur ecouteurSucces,
            ErreurEcouteur ecouteurErreur) {

        StringRequestWithToken stringRequest = new StringRequestWithToken (
                context,
                Request.Method.POST,  context.getResources().getString(R.string.url_spring) + "user/fluxExceptionnel",
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

                        jsonBody = flux.toJson();

                    return jsonBody.toString().getBytes(StandardCharsets.UTF_8);

                } catch (JSONException | ParseException e) {
                    e.printStackTrace();
                }

                return null;
            }
        };

        RequestManager.getInstance(context).addToRequestQueue(stringRequest);

    }

}
