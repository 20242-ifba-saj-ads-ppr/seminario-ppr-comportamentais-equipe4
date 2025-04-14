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

        System.out.println("Número total de trabalhadores: " + lista.contar());

        for (int i = 0; i < lista.contar(); i++) {
            Trabalhador t = lista.obter(i);
            System.out.println("Índice do trabalhador " + t.getNome() + ": " + i);
        }

        Iterator<Trabalhador> iterador = lista.criarIterador(); 
        imprimirTrabalhadores(iterador); 
    }
}
