package com.example.gestiondecompte.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur {

    private int id;
    private String login;
    private double solde;
    private List<Compte> listeCompte = new ArrayList<>();

    public Utilisateur(JSONObject jsonUtilisateur) throws JSONException, ParseException {
        id = jsonUtilisateur.getInt("id");
        login = jsonUtilisateur.getString("login");

        JSONArray jsonListeCompte = jsonUtilisateur.getJSONArray("listeCompte");

        for(int i = 0 ; i < jsonListeCompte.length() ; i++) {
            JSONObject jsonCompte = jsonListeCompte.getJSONObject(i);
                listeCompte.add(new Compte(jsonCompte));

        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Compte> getListeCompte() {
        return listeCompte;
    }

    public void setListeCompte(List<Compte> listeCompte) {
        this.listeCompte = listeCompte;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
}
