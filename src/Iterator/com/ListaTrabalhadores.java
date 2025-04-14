package com;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListaTrabalhadores implements Agregado<Trabalhador> {
    private List<Trabalhador> trabalhadores = new ArrayList<>();

    public void adicionar(Trabalhador trabalhador) {
        trabalhadores.add(trabalhador);
    }

    public Trabalhador obter(int indice) {
        return trabalhadores.get(indice);
    }

    public int contar() {
        return trabalhadores.size();
    }

    @Override
    public Iterator<Trabalhador> criarIterador() {
        return new IteradorTrabalhadores(this);
    }
}
