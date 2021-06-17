package com.example.gestiondecompte.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestiondecompte.R;
import com.example.gestiondecompte.controller.FluxController;
import com.example.gestiondecompte.model.Compte;
import com.example.gestiondecompte.model.FluxExceptionnel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class EditionFluxExceptionnelActivity extends AppCompatActivity {

    private EditText editTextLabel;
    private EditText editTextDate;
    private EditText editTextMontant;
    private CalendarView calendarViewDate;
    private FloatingActionButton fab_ajouterFlux;
    FluxExceptionnel flux;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void init()  {
        setContentView(R.layout.activity_edition_flux_exceptionnel);
        editTextLabel = findViewById(R.id.editText_nomFluxExceptionnel);
        editTextDate = findViewById(R.id.editText_dateFluxExceptionnel);
        editTextMontant = findViewById(R.id.editText_montantFluxExceptionnel);
        calendarViewDate = findViewById(R.id.calendarView_dateFluxExceptionnel);
        fab_ajouterFlux = findViewById(R.id.fab_ajouterFluxExceptionnel);

        editTextDate.setOnFocusChangeListener((v,hasFocus) -> {
            if (hasFocus) {

                new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                    editTextDate.setText(year+"/"+month+"/"+dayOfMonth);
                }, LocalDate.now().getYear(), LocalDate.now().getMonth().getValue()-1,
                        LocalDate.now().getDayOfMonth()).show();
            }
        });

        flux = new FluxExceptionnel();

        fab_ajouterFlux.setOnClickListener(v -> {
            flux.setNom(editTextLabel.getText().toString());
            try {
                flux.setDateFlux(new SimpleDateFormat("dd/MM/yyyy").parse(editTextDate.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            flux.setMontant(Double.parseDouble(editTextMontant.getText().toString()));
            flux.setCompte((Compte) getIntent().getSerializableExtra("compte"));
            FluxController.getInstance().sauvegarderFluxExceptionnel(
                    this,
                    flux,
                    () -> {
                        startActivity(new Intent(this,CompteActivity.class));
                    },
                    erreur -> {
                        Toast.makeText(this, erreur, Toast.LENGTH_SHORT).show();
                    });
        });


    }
}