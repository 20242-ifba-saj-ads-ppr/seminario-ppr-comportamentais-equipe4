package com;

import java.util.Iterator;

public class IteradorTrabalhadores implements Iterator<Trabalhador> {
    private ListaTrabalhadores lista;
    private int atual = 0;

    public IteradorTrabalhadores(ListaTrabalhadores lista) {
        this.lista = lista;
    }

    @Override
    public boolean hasNext() {
        return atual < lista.contar();
    }

    @Override
    public Trabalhador next() {
        return lista.obter(atual++);
    }
}
