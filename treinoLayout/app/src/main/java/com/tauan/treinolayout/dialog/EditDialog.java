package com.tauan.treinolayout.dialog;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.tauan.treinolayout.R;

public class EditDialog {

    private Activity activity;
    private String titulo;

    private TextView dialogTextTitulo, dialogEditText;
    private Button dialogBtnConfirmar;

    private AlertDialog dialog;

    public EditDialog(final Activity activity, final String titulo) {
        this.activity = activity;
        this.titulo = titulo;
    }

    public void show(View.OnClickListener actionConfirmarItens) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_information_edit, null);

        builder.setView(view);
        dialog.setCancelable(false);

        dialogTextTitulo = view.findViewById(R.id.dialogTextTitulo);
        dialogTextTitulo.setText(titulo);

        dialogEditText = view.findViewById(R.id.dialogEditText);

        dialogBtnConfirmar = view.findViewById(R.id.linearBtnContinuar);
        dialogBtnConfirmar.setOnClickListener(actionConfirmarItens);

        dialog = builder.create();
        dialog.show();
    }

    public int getItens() {
        return Integer.parseInt(dialogEditText.getText().toString());
    }

    public void Cancel() {
        dialog.cancel();
    }
}
