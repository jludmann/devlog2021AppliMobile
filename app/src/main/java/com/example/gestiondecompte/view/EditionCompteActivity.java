package com.example.gestiondecompte.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestiondecompte.R;
import com.example.gestiondecompte.controller.CompteController;
import com.example.gestiondecompte.model.Compte;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditionCompteActivity extends AppCompatActivity {

    EditText editTextNomCompte;
    EditText editTextSolde;
    Compte compte;
    FloatingActionButton fabEditionCompte;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        this.compte = new Compte();
        setContentView(R.layout.activity_edition_compte);
        editTextNomCompte = findViewById(R.id.editText_nomCompte);
        editTextSolde = findViewById(R.id.editText_solde);

        fabEditionCompte = findViewById(R.id.fab_ajouterCompte);

        fabEditionCompte.setOnClickListener(v -> {

            compte.setNom(editTextNomCompte.getText().toString());
            compte.setSolde(Double.parseDouble(editTextSolde.getText().toString()));

            CompteController.getInstance().sauvegarder(
                    this,
                    compte,
                    () -> startActivity(new Intent(this, CompteActivity.class)),
                    messageErreur ->
                        Toast.makeText(this, messageErreur, Toast.LENGTH_LONG).show());

        });
    }
}