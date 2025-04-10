package com;

public class CopoComBolinhas implements ColecaoDeBolinhas {
    private final String[] bolinhas = new String[5];
    private int quantidade = 0;

    public void adicionarBolinha(String cor) {
        if (quantidade < bolinhas.length) {
            bolinhas[quantidade++] = cor;
        }
    }

    public String get(int index) {
        return bolinhas[index];
    }

    public int tamanho() {
        return quantidade;
    }

    @Override
    public BolinhaIterator criarIterator() {
        return new IteradorDeBolinhas(this);
    }
}