package com.example.gestiondecompte.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestiondecompte.R;
import com.example.gestiondecompte.controller.UtilisateurController;
import com.example.gestiondecompte.model.Compte;
import com.example.gestiondecompte.view.adapter.ListeCompteAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CompteActivity extends AppCompatActivity {

    private RecyclerView recyclerViewListeCompte;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte_layout);
        init();
    }

    private void init() {

        recyclerViewListeCompte = findViewById(R.id.RecyclerView_listeCompte);
        recyclerViewListeCompte.setLayoutManager(new LinearLayoutManager(this));

        UtilisateurController.getInstance()
                .getInformationUtilisateurConnecte(this, utilisateur -> {
                    recyclerViewListeCompte.setAdapter(
                            new ListeCompteAdapter(
                                    utilisateur.getListeCompte(),
                                    compteClique -> {
                                        Intent intent = new Intent(
                                                this,
                                                CompteActivity.class);

                                        intent.putExtra("compte", compteClique);
                                        startActivity(intent);
                                    }));
                });

        floatingActionButton = findViewById(R.id.fab_ajouterCompte);

        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    this,
                    EditionCompteActivity.class);

            intent.putExtra("compte", new Compte());
            startActivity(intent);
        });


    }
}