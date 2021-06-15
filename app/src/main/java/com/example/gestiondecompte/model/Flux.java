package com.example.gestiondecompte.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public abstract class Flux implements Serializable {

    private int id;
    private String nom;
    private double montant;

    public Flux(JSONObject jsonFlux) throws JSONException {
        id = jsonFlux.getInt("id");
        nom = jsonFlux.getString("nom");
        montant = jsonFlux.getDouble("montant");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public abstract JSONObject toJson() throws JSONException;
}
