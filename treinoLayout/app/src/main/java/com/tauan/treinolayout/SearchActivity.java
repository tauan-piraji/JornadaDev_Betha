package com.tauan.treinolayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    private EditText searchEditProduto;
    private Button searchBtnBuscar;
    private final int CONSULTA_PRODUTO = 99;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pag);

        searchEditProduto = findViewById(R.id.searchEditProduto);
        searchBtnBuscar = findViewById(R.id.searchBtnBuscar);
        searchBtnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Chama a segunda java activity
                startActivityForResult(new Intent(SearchActivity.this, ListActivity.class), CONSULTA_PRODUTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CONSULTA_PRODUTO && resultCode == RESULT_OK) {
            searchEditProduto.setText(data.getStringExtra("produto_escolhido"));
        }
    }
}