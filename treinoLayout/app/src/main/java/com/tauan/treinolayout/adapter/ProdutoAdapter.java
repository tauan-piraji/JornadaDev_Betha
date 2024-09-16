package com.tauan.treinolayout.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tauan.treinolayout.R;

import java.util.List;

public class ProdutoAdapter extends BaseAdapter {

    private Activity activity;
    private List<ProdutoModel> listProdutos;

    public ProdutoAdapter(final Activity activity, final List<ProdutoModel> listProdutos) {
        this.activity = activity;
        this.listProdutos = listProdutos;
    }

    @Override
    public int getCount() {
        return listProdutos.size();
    }

    @Override
    public Object getItem(int position) {
        return listProdutos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //se n exite uma view ainda "(convertView == null)" então cria uma
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.activity_list_customizada, parent, false);
        }

        //popula cada um dos ambitos da list_customizada
        TextView listCusCodigo = convertView.findViewById(R.id.listCusCodigo);
        listCusCodigo.setText("Codígo: " + ((ProdutoModel)getItem(position)).getCodigoProduto());

        TextView listCusNome = convertView.findViewById(R.id.listCusNome);
        listCusNome.setText("Nome: " + ((ProdutoModel)getItem(position)).getNomeProduto());

        TextView listCusCodBarras = convertView.findViewById(R.id.listCusCodBarras);
        listCusCodBarras.setText("Codígo de barras: " + ((ProdutoModel)getItem(position)).getBarrasProduto());

        TextView listCusTextEstoque = convertView.findViewById(R.id.listCusTextEstoque);

        LinearLayout listCusBtnEstoque = convertView.findViewById(R.id.listCusBtnEstoque);
        listCusBtnEstoque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] partes = listCusTextEstoque.getText().toString().split(":");
                int estoque = Integer.parseInt(partes[1].trim());
                listCusTextEstoque.setText("Estoque: "+ (estoque + 1));
            }
        });

        return convertView;
    }
}

/*
listCusCodigo.setText("Codígo: " + ((ProdutoModel)getItem(position)).getCodigoProduto());
((ProdutoModel)getItem(position)) cat usado pq getItem retorna um obj
 */