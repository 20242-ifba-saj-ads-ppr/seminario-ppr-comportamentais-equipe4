package com;

public class ListaNomesIterator implements Iterator {
    private final ListaNomes lista;
    private int posicao = 0;

    public ListaNomesIterator(ListaNomes lista) {
        this.lista = lista;
    }

    @Override
    public boolean hasNext() {
        return posicao < lista.tamanho();
    }

    @Override
    public Object next() {
        return lista.get(posicao++);
    }
}