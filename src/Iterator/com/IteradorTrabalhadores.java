package com;

public class IteradorTrabalhadores implements Iterador<Trabalhador> {
    private ListaTrabalhadores lista;
    private int atual = 0;

    public IteradorTrabalhadores(ListaTrabalhadores lista) {
        this.lista = lista;
    }

    @Override
    public void primeiro() {
        atual = 0;
    }

    @Override
    public void proximo() {
        atual++;
    }

    @Override
    public boolean acabou() {
        return atual >= lista.contar();
    }

    @Override
    public Trabalhador itemAtual() {
        if (acabou()) {
            throw new IllegalStateException("Iterador fora dos limites.");
        }
        return lista.obter(atual);
    }
}
