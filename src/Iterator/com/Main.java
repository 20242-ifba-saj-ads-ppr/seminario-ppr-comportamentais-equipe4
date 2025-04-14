package com;

public class Main {
    public static void imprimirTrabalhadores(Iterador<Trabalhador> iterador) {
        for (iterador.primeiro(); !iterador.acabou(); iterador.proximo()) {
            iterador.itemAtual().imprimir();
        }
    }

    public static void main(String[] args) {
        ListaTrabalhadores lista = new ListaTrabalhadores();
        lista.adicionar(new Trabalhador("Ana"));
        lista.adicionar(new Trabalhador("Bruna"));
        lista.adicionar(new Trabalhador("Carlos"));
        lista.adicionar(new Trabalhador("Daniel"));

        Iterador<Trabalhador> iterador = lista.criarIterador();
        imprimirTrabalhadores(iterador);
    }
}
