package com;

public class Imagem implements ElementoDocumento {
    public String caminho;

    public Imagem(String caminho) {
        this.caminho = caminho;
    }

    @Override
    public void aceitar(Visitante v) {
        v.visitarImagem(this);
    }
}