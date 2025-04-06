package com;

public class ListaNomes implements IterableCollection {
    private final String[] nomes = new String[10];
    private int contador = 0;

    public void adicionar(String nome) {
        nomes[contador++] = nome;
    }

    public String get(int index) {
        return nomes[index];
    }

    public int tamanho() {
        return contador;
    }

    @Override
    public Iterator criarIterator() {
        return new ListaNomesIterator(this);
    }
}