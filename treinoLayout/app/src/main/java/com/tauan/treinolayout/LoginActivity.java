package com.tauan.treinolayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tauan.treinolayout.util.Shared;

public class LoginActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private EditText linearEditEmail, linearEditSenha;
    private Button linearBtnSalvar, linearBtnContinuar, linearBtnContinuarList, linearBtnRemover;
    private Shared shared;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acessar);

        shared = new Shared(LoginActivity.this);

        linearLayout = findViewById(R.id.linearLogin);
        linearEditEmail = findViewById(R.id.linearEditEmail);
        linearEditSenha = findViewById(R.id.linearEditSenha);
        linearBtnSalvar = findViewById(R.id.linearBtnSalvar);
        linearBtnContinuar = findViewById(R.id.linearBtnContinuar);
        linearBtnContinuarList = findViewById(R.id.linearBtnContinuarList);
        linearBtnRemover = findViewById((R.id.linearBtnRemover));

        linearBtnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shared.put(Shared.KEY_EMAIL_USUARIO, linearEditEmail.getText().toString());
                shared.put(Shared.KEY_SENHA_USUARIO, linearEditSenha.getText().toString());
            }
        });

        linearBtnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shared.remove(Shared.KEY_EMAIL_USUARIO);
            }
        });

        linearBtnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, PadariaMainActivity.class));
                //troca a tela do app
            }
        });

        linearBtnContinuarList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SearchActivity.class));
            }
        });

        linearEditEmail.setText(shared.getString(Shared.KEY_EMAIL_USUARIO));
        linearEditSenha.setText(shared.getString(Shared.KEY_SENHA_USUARIO));
    }
}