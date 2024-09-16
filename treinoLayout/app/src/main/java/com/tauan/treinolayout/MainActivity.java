package com.tauan.treinolayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {

    private ImageView imgUnesc;
    private Animation animation;
    //private LinearLayout linearLayout;
    //private EditText linearEditEmail, linearEditSenha;
    //private Button linearBtnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgUnesc = findViewById(R.id.imgUnesc);
        //linearLayout = findViewById(R.id.linearLogin);
        //linearEditEmail = findViewById(R.id.linearEditEmail);
        //linearEditSenha = findViewById(R.id.linearEditSenha);
        //linearBtnContinuar = findViewById(R.id.linearBtnContinuar);

        animation = AnimationUtils.loadAnimation( MainActivity.this, R.anim.splash);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent( MainActivity.this, LoginActivity.class));
                //imgUnesc.setVisibility(View.INVISIBLE);
                //linearLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        /*
        linearBtnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("EMAIL_USUARIO", linearEditEmail.getText().toString());


                if(linearEditEmail.getText().toString().isEmpty()) {
                    linearEditEmail.setError("Campo obrigatório");
                }else if (!linearEditEmail.getText().toString().equalsIgnoreCase("Administrador") ||
                            !linearEditEmail.getText().toString().equalsIgnoreCase("Adm") ||
                            !linearEditEmail.getText().toString().equalsIgnoreCase("Administrator") ||
                            !linearEditEmail.getText().toString().equalsIgnoreCase("Root")) {
                    linearEditEmail.setError("Nome do usuário invalido!");
                }else{
                    linearEditEmail.setError("blz!");
                }


                if (linearEditSenha.getText().toString().isEmpty()) {
                    linearEditSenha.setError("Campo obrigatório");
                }else if (linearEditEmail.getText().toString().equalsIgnoreCase("Administrador") &&
                            linearEditSenha.getText().toString().equalsIgnoreCase("Administrador")||
                        !linearEditEmail.getText().toString().equalsIgnoreCase("Adm") ||
                        !linearEditEmail.getText().toString().equalsIgnoreCase("Administrator") ||
                        !linearEditEmail.getText().toString().equalsIgnoreCase("Root")) {
                    linearEditEmail.setError("Senha invalida!");
                }else{
                    linearEditSenha.setError("Blz!");
                }

            }

        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        linearEditEmail.setText(preferences.getString("EMAIL_USUARIO", ""));
        */
        imgUnesc.startAnimation(animation);
    }
}