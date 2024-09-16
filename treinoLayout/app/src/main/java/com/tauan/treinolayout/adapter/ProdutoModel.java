package com.tauan.treinolayout.adapter;

public class ProdutoModel {

    private String codigoProduto, nomeProduto, barrasProduto;

    public ProdutoModel(String codigoProduto, String nomeProduto, String barrasProduto) {
        this.codigoProduto = codigoProduto;
        this.nomeProduto = nomeProduto;
        this.barrasProduto = barrasProduto;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getBarrasProduto() {
        return barrasProduto;
    }

}