package com.tauan.treinolayout.dialog;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.tauan.treinolayout.R;

public class InfoDialog {

    private Activity activity;
    private String titulo;
    private String message;

    private TextView dialogTextTitulo, dialogTextMessage;
    private Button dialogBtnConfirmar;

    private AlertDialog dialog;

    public InfoDialog(final Activity activity, final String titulo, final String message) {
        this.activity = activity;
        this.titulo = titulo;
        this.message = message;
    }

    public void show() {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_information, null);

        builder.setView(view);
        dialog.setCancelable(false);

        dialogTextTitulo = view.findViewById(R.id.dialogTextTitulo);
        dialogTextTitulo.setText(titulo);

        dialogTextMessage = view.findViewById(R.id.dialogTextMessage);
        dialogTextMessage.setText(message);

        dialogBtnConfirmar = view.findViewById(R.id.linearBtnContinuar);
        dialogBtnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog = builder.create();
        dialog.show();
    }
}
