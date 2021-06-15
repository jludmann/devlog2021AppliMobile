package com.example.gestiondecompte.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestiondecompte.R;
import com.example.gestiondecompte.controller.ConnexionController;


public class LoginActivity extends AppCompatActivity {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        editTextLogin = findViewById(R.id.editText_pseudo);
        editTextPassword = findViewById(R.id.editText_password);
        buttonConnexion = findViewById(R.id.button_connexion);
        init();
    }

    private void init() {

        this.buttonConnexion.setOnClickListener((View v) -> {
            ConnexionController.getInstance().connexion(
                    this,
                    editTextLogin.getText().toString(),
                    editTextPassword.getText().toString(),
                    () -> {
                        startActivity(new Intent(this,CompteActivity.class));
                    }
            );
        });
    }
}