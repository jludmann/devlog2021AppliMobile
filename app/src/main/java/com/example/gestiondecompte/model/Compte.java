package com.example.gestiondecompte.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Compte implements Serializable {

    private Integer id;
    private String nom;
    private double solde;
    private List<Flux> listeFlux = new ArrayList<>();
    private double seuilAlerte;

    public Compte() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Flux> getListeFlux() {
        return listeFlux;
    }

    public void setListeFlux(List<Flux> listeFlux) {
        this.listeFlux = listeFlux;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public double getSeuilAlerte() {
        return seuilAlerte;
    }

    public void setSeuilAlerte(double seuilAlerte) {
        this.seuilAlerte = seuilAlerte;
    }

    public Compte(JSONObject jsonCompte) throws JSONException, ParseException {
        id = jsonCompte.getInt("id");
        nom = jsonCompte.getString("nom");
        solde = jsonCompte.getDouble("solde");
        seuilAlerte = jsonCompte.getDouble("seuilAlerte");

        JSONArray jsonListeFlux = jsonCompte.getJSONArray("listeFlux");

        for(int i = 0 ; i < jsonListeFlux.length() ; i++) {
            JSONObject jsonFlux = jsonListeFlux.getJSONObject(i);

            if(jsonFlux.has("dateFlux")) {
                listeFlux.add(new FluxExceptionnel(jsonFlux));
            } else {
                listeFlux.add(new FluxMensuel(jsonFlux));
            }
        }
    }

    public JSONObject toJson() throws JSONException, ParseException {
        JSONObject jsonCompte = new JSONObject();

        jsonCompte.put("id", this.getId());
        jsonCompte.put("nom", this.getNom());
        jsonCompte.put("solde", this.getSolde());
        jsonCompte.put("seuilAlerte", this.getSeuilAlerte());

        JSONArray listeFlux = new JSONArray();
        for (Flux flux : this.listeFlux) {
            listeFlux.put(flux.toJson());
        }

        jsonCompte.put("listeFlux", listeFlux);

        return jsonCompte;
    }
}
