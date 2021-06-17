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
    private String password;
    private String nom;
    private String prenom;
    private List<Compte> listeCompte = new ArrayList<>();

    public Utilisateur() {
    }

    public Utilisateur(JSONObject jsonUtilisateur) throws JSONException, ParseException {
        id = jsonUtilisateur.getInt("id");
        login = jsonUtilisateur.getString("login");
        prenom = jsonUtilisateur.getString("prenom");
        nom = jsonUtilisateur.getString("nom");

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jsonUtilisateur = new JSONObject();

        jsonUtilisateur.put("nom", this.getNom());
        jsonUtilisateur.put("prenom", this.getPrenom());
        jsonUtilisateur.put("login", this.getLogin());
        jsonUtilisateur.put("password", this.getPassword());

        return jsonUtilisateur;
    }
}
