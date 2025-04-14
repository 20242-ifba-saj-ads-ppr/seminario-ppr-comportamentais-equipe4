package com;

import java.util.Iterator;

public class Main {

    public static void imprimirTrabalhadores(Iterator<Trabalhador> iterador) {
        while (iterador.hasNext()) {
            Trabalhador t = iterador.next();
            t.imprimir();
        }
    }

    public static void main(String[] args) {
        ListaTrabalhadores lista = new ListaTrabalhadores();
        lista.adicionar(new Trabalhador("Ana"));
        lista.adicionar(new Trabalhador("Bruna"));
        lista.adicionar(new Trabalhador("Carlos"));
        lista.adicionar(new Trabalhador("Daniel"));

        Iterator<Trabalhador> iterador = lista.criarIterador();
        imprimirTrabalhadores(iterador);
    }
}
