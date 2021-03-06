package com.example.gestiondecompte.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class FluxExceptionnel extends Flux {

    private Date dateFlux;

    public FluxExceptionnel(JSONObject jsonFlux) throws JSONException, ParseException {
        super(jsonFlux);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        dateFlux = (Date) format.parse((String) jsonFlux.get("dateFlux"));
    }

    public FluxExceptionnel() {
        super();
    }

    @Override
    public JSONObject toJson() throws JSONException, ParseException {
        JSONObject jsonFlux = new JSONObject();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        jsonFlux.put("id", this.getId());
        jsonFlux.put("nom", super.getNom());
        jsonFlux.put("montant", super.getMontant());
        jsonFlux.put("dateFlux", format.format(this.getDateFlux()));

        for(Flux flux : getCompte().getListeFlux()){
            flux.setCompte(getCompte());
        }

        jsonFlux.put("compte", getCompte().toJson());

        return jsonFlux;
    }

    public Date getDateFlux() {
        return dateFlux;
    }

    public void setDateFlux(Date dateFlux) {
        this.dateFlux = dateFlux;
    }
}
