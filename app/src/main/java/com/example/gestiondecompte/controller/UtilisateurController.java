package com.example.gestiondecompte.controller;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.example.gestiondecompte.R;
import com.example.gestiondecompte.model.Utilisateur;
import com.example.gestiondecompte.utils.JsonObjectRequestWithToken;
import com.example.gestiondecompte.utils.RequestManager;
import com.example.gestiondecompte.utils.StringRequestWithToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;

public class UtilisateurController {
    private static UtilisateurController instance = null;

    private UtilisateurController() {
    }

    public static UtilisateurController getInstance() {

        if(instance == null) {
            instance = new UtilisateurController();
        }

        return instance;
    }


    public interface UtilisateurConnecteListener {
        void onUtilisateurConnecteListener(Utilisateur utilisateur);
    }

    public void getInformationUtilisateurConnecte(Context context, UtilisateurConnecteListener listener) {

        JsonObjectRequestWithToken request = new JsonObjectRequestWithToken(
                context,
                Request.Method.GET,
                context.getResources().getString(R.string.url_spring) + "user/utilisateur-connecte",
                null,
                jsonUtilisateur -> {
                    try {
                        Utilisateur utilisateur = new Utilisateur(jsonUtilisateur);
                        listener.onUtilisateurConnecteListener(utilisateur);
                    } catch (JSONException | ParseException e) {
                        e.printStackTrace();
                    }
                },
                erreur -> System.out.println("erreur")
        );

        RequestManager.getInstance(context).addToRequestQueue(request);
    }

    public interface SuccesEcouteur {
        void onSuccesSauvegarde();
    }

    public interface ErreurEcouteur {
        void onErreurSauvegarde(String messageErreur);
    }

    public void inscription(Context context, Utilisateur utilisateur, SuccesEcouteur succesEcouteur, ErreurEcouteur erreurEcouteur) {

        StringRequestWithToken stringRequestWithToken = new StringRequestWithToken(
                context,
                Request.Method.POST,
                context.getResources().getString(R.string.url_spring) + "inscription",
                succes -> succesEcouteur.onSuccesSauvegarde(),
                error -> erreurEcouteur.onErreurSauvegarde("Echec de l'inscription")
                ){
            @Override
            public byte[] getBody() throws AuthFailureError {

                try {
                    JSONObject jsonBody = utilisateur.toJson();
                    return jsonBody.toString().getBytes(StandardCharsets.UTF_8);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        RequestManager.getInstance(context).addToRequestQueue(stringRequestWithToken);

    }

}
