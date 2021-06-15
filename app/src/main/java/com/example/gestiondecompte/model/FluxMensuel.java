package com.example.gestiondecompte.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class FluxMensuel extends Flux{

    private Date jourDuMois;
    private Date dateFin;

    public FluxMensuel(JSONObject jsonFlux) throws JSONException, ParseException {
        super(jsonFlux);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        jourDuMois = (Date) format.parse((String) jsonFlux.get("jourDuMois"));
        dateFin = (Date) format.parse((String) jsonFlux.get("dateFin"));
    }

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject jsonFlux = new JSONObject();

        jsonFlux.put("id", this.getId());
        jsonFlux.put("jourDuMois", this.getJourDuMois());
        jsonFlux.put("dateFin", this.getDateFin());

        return jsonFlux;
    }

    public Date getJourDuMois() {
        return jourDuMois;
    }

    public void setJourDuMois(Date jourDuMois) {
        this.jourDuMois = jourDuMois;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}
