package com.tauan.treinolayout;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.tauan.treinolayout.dialog.EditDialog;
import com.tauan.treinolayout.dialog.InfoDialog;
import com.tauan.treinolayout.util.Shared;

public class PadariaMainActivity extends AppCompatActivity {

    private Shared shared;
    private TextView headertext, linearTextItens;
    private ListView listProdutos;
    private Button BtnCorL, BtnItens;
    private LinearLayout linearColor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_padaria);

        shared = new Shared(PadariaMainActivity.this);

        linearColor = findViewById(R.id.linearColor);
        linearTextItens = findViewById(R.id.linearTextItens);

        BtnCorL = findViewById(R.id.BtnCorL);
        BtnCorL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Drawable background = linearColor.getBackground();

                if(((ColorDrawable)background).getColor() == getResources().getColor(R.color.design_default_color_error)) {
                    linearColor.setBackgroundColor(getResources().getColor(R.color.black));
                }else {
                    linearColor.setBackgroundColor(getResources().getColor(R.color.design_default_color_error));
                }
                shared.put(Shared.KEY_COR_LINEAR, ((ColorDrawable)background).getColor());

                InfoDialog dialog = new InfoDialog(PadariaMainActivity.this, "Informação", "Cor salva com sucesso!");
                dialog.show();
/*
                AlertDialog.Builder builder = new AlertDialog.Builder(PadariaMainActivity.this);
                builder.setTitle("Reservas");
                builder.setIcon(R.drawable.unesc_logo);
                builder.setMessage("Cor Salva Com Sucesso");
                builder.setCancelable(false);
                builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("NÂO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
 */
                //Toast.makeText(PadariaMainActivity.this, "Cor salva com sucesso ", Toast.LENGTH_SHORT).show();
            }
        });


        BtnItens = findViewById(R.id.BtnItens);
        BtnItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditDialog editDialog = new EditDialog(PadariaMainActivity.this, "Quantidade");
                editDialog.show(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        linearTextItens.setText("Itens: " + editDialog.getItens());
                        editDialog.Cancel();
                    }
                });
            }
        });

        linearColor = findViewById(R.id.linearColor);
        if(shared.getInt(Shared.KEY_COR_LINEAR) != 0) {
            linearColor.setBackgroundColor(shared.getInt(shared.KEY_COR_LINEAR));
        }

        listProdutos = findViewById(R.id.listaProdutos);


        headertext = findViewById(R.id.headerText);
        headertext.setText("User: " + shared.getString(Shared.KEY_EMAIL_USUARIO));

        String dados[] = new String[]{
                "pão frances", "pãe de água", "rosca",
                "bolo", "pastel de carne", "pastel de frango",
                "pastel de pizza", "coxinha de frango", "coxinha de carne",
                "churros", "folhado de frango"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                                            PadariaMainActivity.this,
                                                            android.R.layout.simple_list_item_1,
                                                            dados);
        listProdutos.setAdapter(adapter);
    }
}
