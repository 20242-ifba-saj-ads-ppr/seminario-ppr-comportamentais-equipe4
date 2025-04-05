package com;
public class Texto implements ElementoDocumento {
    public String conteudo;

    public Texto(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public void aceitar(Visitante v) {
        v.visitarTexto(this);
    }
}