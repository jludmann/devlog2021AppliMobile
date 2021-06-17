package com.example.gestiondecompte.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestiondecompte.R;
import com.example.gestiondecompte.controller.CompteController;
import com.example.gestiondecompte.controller.UtilisateurController;
import com.example.gestiondecompte.model.Compte;
import com.example.gestiondecompte.model.Utilisateur;

import org.json.JSONException;

import java.text.ParseException;

public class InscriptionActivity extends AppCompatActivity {

    EditText editTextNom;
    EditText editTextPrenom;
    EditText editTextLogin;
    EditText editTextPassword;
    Button buttonInscription;
    Utilisateur utilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        editTextNom = findViewById(R.id.editText_nom);
        editTextPrenom = findViewById(R.id.editText_prenom);
        editTextLogin = findViewById(R.id.editText_login);
        editTextPassword = findViewById(R.id.editText_newPassword);
        buttonInscription = findViewById(R.id.button_newUser);

        init();
    }

    public void init() {

        this.utilisateur = new Utilisateur();

        buttonInscription.setOnClickListener(v -> {

            utilisateur.setNom(editTextNom.getText().toString());
            utilisateur.setPrenom(editTextPrenom.getText().toString());
            utilisateur.setLogin(editTextLogin.getText().toString());
            utilisateur.setPassword(editTextPassword.getText().toString());

            UtilisateurController.getInstance().inscription(
                    this,
                    utilisateur,
                    () -> startActivity(new Intent(this, LoginActivity.class)),
                    messageErreur ->
                            Toast.makeText(this, messageErreur, Toast.LENGTH_LONG).show());

        });
    }

}