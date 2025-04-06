package com;
public class Main {
    public static void main(String[] args) {
        ListaNomes lista = new ListaNomes();
        lista.adicionar("Ana");
        lista.adicionar("Beatriz");
        lista.adicionar("Carlos");
        lista.adicionar("Daniel");

        Iterator iterador = lista.criarIterator();

        while (iterador.hasNext()) {
            System.out.println(iterador.next());
        }
    }
}