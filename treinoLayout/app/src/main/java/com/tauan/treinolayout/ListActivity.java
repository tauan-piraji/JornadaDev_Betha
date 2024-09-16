package com.tauan.treinolayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tauan.treinolayout.adapter.ProdutoAdapter;
import com.tauan.treinolayout.adapter.ProdutoModel;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ListView listLvProdutos;
    private Button listBtnAdd;
    private ArrayList<ProdutoModel> list = new ArrayList<ProdutoModel>();
    private ProdutoAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listBtnAdd = findViewById(R.id.listBtnAdd);
        listBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(new ProdutoModel("445588", "risoles", "445214587"));
                adapter.notifyDataSetChanged();
            }
        });

        listLvProdutos = findViewById(R.id.listLvProdutos);
        listLvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent();
                it.putExtra("prduto_escolhido", list.get(position).getNomeProduto());
                setResult(RESULT_OK, it);
                finish();
            }
        });

        list.add(new ProdutoModel("445588", "Pão doce", "445214587"));
        list.add(new ProdutoModel("123654", "Pão frances", "112365478"));
        list.add(new ProdutoModel("521489", "Pastel de frango", "123456789"));
        list.add(new ProdutoModel("569852", "Bolinho de chuva", "012345678"));
        list.add(new ProdutoModel("546858", "Pastel de carne", "987456321"));
        list.add(new ProdutoModel("223311", "Pastel de queijoe e presunto", "987654321"));
        list.add(new ProdutoModel("258963", "Bolo de brigadeiro", "876543210"));
        list.add(new ProdutoModel("654785", "Bolo de morango", "258741369"));
        list.add(new ProdutoModel("222222", "Torta de bolacha", "147258369"));
        list.add(new ProdutoModel("032012", "Coxinha", "147852369"));

        ProdutoAdapter adapter = new ProdutoAdapter(ListActivity.this, list);
        listLvProdutos.setAdapter(adapter);
    }
}
